package bo.edu.ucb.tallerdedesarollo.backend.DAO;

import bo.edu.ucb.tallerdedesarollo.backend.DTO.SolicitudEventoDTO;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public interface SolicitudEventoDAO {

    @Select(" SELECT solicitudid, usuarios_userid, descripcion, estado, evento_publicacion_ep_id ,fecha_solicitud, fecha_revisado " +
            " FROM solicitudes ORDER BY fecha_solicitud DESC, fecha_revisado DESC  ")
    public List<SolicitudEventoDTO> findAll();

    //EndPoint de filtrado de solicitudes aceptadas y rechazadas
    @Select(" SELECT solicitudid, usuarios_userid, descripcion, estado, evento_publicacion_ep_id ,fecha_solicitud, fecha_revisado " +
            " FROM solicitudes WHERE estado = #{status};")
    public List<SolicitudEventoDTO> findAllAccepted(@Param("status") Integer status);

    //Quitar solicitud id en la actualizacion a BD cambio de int a serial
    @Insert("INSERT INTO solicitudes (usuarios_userid, descripcion, estado, evento_publicacion_ep_id)" +
            "VALUES ( #{userid},#{descripcion},#{estado},#{epid})")
    public void newSol(@Param("userid") Integer userId, @Param("descripcion") String descripcion, @Param("estado") Integer estado, @Param("epid") Integer epid);

    //Prueba
    @Insert(" INSERT INTO solicitudes (usuarios_userid, descripcion, estado, evento_publicacion_ep_id, fecha_solicitud, fecha_revisado) " +
            " VALUES (#{usuarios_userid},#{descripcion},#{estado},#{evento_publicacion_ep_id},#{fecha_solicitud},#{fecha_revisado}) ")
    public void insertSoli(SolicitudEventoDTO solicitudEventoDTO);

    @Update(" UPDATE solicitudes SET estado = #{estado}, fecha_revisado = #{fecha_revisado} " +
            " WHERE solicitudid = #{id}; ")
    public void estadoSoli(@Param("estado")long estado, @Param("fecha_revisado") Timestamp fecha, @Param("id") Integer id);
}
