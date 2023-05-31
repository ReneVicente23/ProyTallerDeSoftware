package bo.edu.ucb.tallerdedesarollo.backend.DAO;

import bo.edu.ucb.tallerdedesarollo.backend.DTO.ComentarioDTO;
import bo.edu.ucb.tallerdedesarollo.backend.DTO.ModificacionesDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ComentariosDao {
    
    @Insert("INSERT INTO comentario(comentario, solicitudes_solicitudid) VALUES (#{comentario}, #{solicitudes_solicitudid})")
    public void insertComentarioSolicitud(@Param("comentario") String comentario, @Param("solicitudes_solicitudid") Integer solicitudId);

    @Select("SELECT * " +
            "FROM comentario WHERE solicitudes_solicitudId= #{id}")
    public List<ComentarioDTO> findcomhistory(@Param("id") Integer id);
}
