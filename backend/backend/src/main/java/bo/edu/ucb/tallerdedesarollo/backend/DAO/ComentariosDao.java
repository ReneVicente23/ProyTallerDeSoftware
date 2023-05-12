package bo.edu.ucb.tallerdedesarollo.backend.DAO;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public interface ComentariosDao {
    
    @Insert("INSERT INTO comentario(comentario, solicitudes_solicitudid) VALUES (null, #{comentario}, #{solicitudes_solicitudid})")
    public void insertComentarioSolicitud(@Param("comentario") String comentario, @Param("solicitudes_solicitudid") Integer solicitudId);
}
