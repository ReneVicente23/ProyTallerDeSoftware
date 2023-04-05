package bo.edu.ucb.tallerdedesarollo.backend.DAO;

import bo.edu.ucb.tallerdedesarollo.backend.DTO.Evento_publicacionDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Evento_publicacionDAO {
    @Select("SELECT ep_id,titulo,descripcion,id_imagen,lugar,link,evento_publicacion_tipo_id_eptipo " +
            "FROM evento_publicacion "  ) // Validacion de usuario necesaria
    public List<Evento_publicacionDTO> findAll();

    @Insert("INSERT INTO evento_publicacion (titulo, descripcion, id_imagen, lugar, link, evento_publicacion_tipo_id_eptipo)" +
            " VALUES (#{titulo}, #{descripcion}, #{id_imagen}, #{lugar}, #{link}, #{id_tipo})")
    public void saveEvento_publicacion(@Param("titulo") String titulo, @Param("descripcion") String descripcion, @Param("id_imagen") String id_imagen,@Param("lugar") String lugar,@Param("link") String link,@Param("id_tipo") Integer id_tipo);
}
