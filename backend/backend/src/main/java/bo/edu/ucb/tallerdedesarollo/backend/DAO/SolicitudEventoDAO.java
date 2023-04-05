package bo.edu.ucb.tallerdedesarollo.backend.DAO;

import bo.edu.ucb.tallerdedesarollo.backend.DTO.SolicitudEventoDTO;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SolicitudEventoDAO {

    @Select("SELECT * " +
            "FROM solicitudes")
    public List<SolicitudEventoDTO> findAll();

    //Quitar solicitud id en la actualizacion a BD cambio de int a serial
    @Insert("INSERT INTO solicitudes (usuarios_userid, descripcion, estado, evento_publicacion_ep_id)" +
            "VALUES ( #{userid},#{descripcion},#{estado},#{epid})")
    public void newSol(@Param("userid") Integer userId, @Param("descripcion") String descripcion, @Param("estado") Integer estado, @Param("epid") Integer epid);
}
