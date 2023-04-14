package bo.edu.ucb.tallerdedesarollo.backend.DAO;

import bo.edu.ucb.tallerdedesarollo.backend.DTO.Evento_publicacionDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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

    @Select("SELECT ep_id " +
            "FROM evento_publicacion WHERE titulo= #{titulo} AND descripcion=#{descripcion} AND id_imagen= #{id_imagen} AND lugar= #{lugar} AND link=#{link} LIMIT 1"  ) // Validacion de usuario necesaria
    public Integer findIdByContend(@Param("titulo") String titulo, @Param("descripcion") String descripcion, @Param("id_imagen") String id_imagen,@Param("lugar") String lugar,@Param("link") String link,@Param("id_tipo") Integer id_tipo);
    @Insert("INSERT INTO interesesEventos (Intereses_interesId, evento_publicacion_ep_id)" +
            " VALUES (#{interesID}, #{eventoID})")
    public void save_interes(@Param("interesID") Integer idInteres, @Param("eventoID") Integer idEvento);

    @Insert("INSERT INTO publico_destino_ep (evento_publicacion_ep_id, tipopublico,rangos_edad_id_rangos_edad,publico_tipo_id_publico)" +
            " VALUES (#{eventoID}, #{tipo},#{rangoID},#{tipoPublico})")
    public void save_publico(@Param("eventoID") Integer idEvento,@Param("tipo") Integer tipo,@Param("rangoID") Integer rangoID,@Param("tipoPublico") Integer tipoPublico);

    @Select("SELECT * " +
            "FROM evento_publicacion WHERE titulo= #{titulo} AND descripcion=#{descripcion} AND id_imagen= #{id_imagen} AND lugar= #{lugar} AND link=#{link} LIMIT 1"  ) // Validacion de usuario necesaria
    public Evento_publicacionDTO findByContend(@Param("titulo") String titulo, @Param("descripcion") String descripcion, @Param("id_imagen") String id_imagen,@Param("lugar") String lugar,@Param("link") String link,@Param("id_tipo") Integer id_tipo);
    @Update("UPDATE evento_publicacion SET id_imagen= #{img} WHERE ep_id = #{id}")
    public void updateimagen(@Param("ID") Integer id,@Param("img") String img);
}
