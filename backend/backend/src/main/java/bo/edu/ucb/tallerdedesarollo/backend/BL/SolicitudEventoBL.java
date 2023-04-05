package bo.edu.ucb.tallerdedesarollo.backend.BL;

import bo.edu.ucb.tallerdedesarollo.backend.DAO.SolicitudEventoDAO;
import bo.edu.ucb.tallerdedesarollo.backend.DTO.SolicitudEventoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
