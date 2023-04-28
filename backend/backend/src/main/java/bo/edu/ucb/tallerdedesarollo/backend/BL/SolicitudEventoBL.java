package bo.edu.ucb.tallerdedesarollo.backend.BL;

import bo.edu.ucb.tallerdedesarollo.backend.DAO.SolicitudEventoDAO;
import bo.edu.ucb.tallerdedesarollo.backend.DTO.SolicitudEventoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class SolicitudEventoBL {
    private SolicitudEventoDAO solicitudEventoDAO;

    @Autowired

    public SolicitudEventoBL(SolicitudEventoDAO solicitudEventoDAO) {
        this.solicitudEventoDAO = solicitudEventoDAO;
    }

    public List<SolicitudEventoDTO> getAll(){
        return solicitudEventoDAO.findAll();
    }

    public void nuevaSol(SolicitudEventoDTO solicitudEventoDTO){
        solicitudEventoDAO.newSol((int)solicitudEventoDTO.getUsuarios_userid(),solicitudEventoDTO.getDescripcion(),(int)solicitudEventoDTO.getEstado(),(int)solicitudEventoDTO.getEvento_publicacion_ep_id());
    }

    //prueba

    public SolicitudEventoDTO insertSoli(SolicitudEventoDTO solicitudEventoDTO){
        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
        solicitudEventoDTO.setFecha_solicitud(timestamp);
        solicitudEventoDAO.insertSoli(solicitudEventoDTO);
        return solicitudEventoDTO;
    }

    public SolicitudEventoDTO estadoSoli(SolicitudEventoDTO solicitudEventoDTO, Integer id){
        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
        solicitudEventoDTO.setFecha_revisado(timestamp);
        solicitudEventoDAO.estadoSoli(solicitudEventoDTO.getEstado(),solicitudEventoDTO.getFecha_revisado(), id);
        return solicitudEventoDTO;
    }

}
