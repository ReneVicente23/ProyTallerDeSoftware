package bo.edu.ucb.tallerdedesarollo.backend.BL;

import bo.edu.ucb.tallerdedesarollo.backend.DAO.Evento_publicacionDAO;
import bo.edu.ucb.tallerdedesarollo.backend.DTO.Evento_publicacionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Evento_PublicacionBL {
    Evento_publicacionDAO eventoPublicacionDAO;

    @Autowired
    public Evento_PublicacionBL(Evento_publicacionDAO eventoPublicacionDAO) {
        this.eventoPublicacionDAO = eventoPublicacionDAO;
    }
    //Crea un nuevo eventoPublicacion
    public void newEvento_publicacion (Evento_publicacionDTO evento_publicacionDTO){
        eventoPublicacionDAO.saveEvento_publicacion(evento_publicacionDTO.getTitulo(),evento_publicacionDTO.getDescripcion(),evento_publicacionDTO.getId_imagen(),evento_publicacionDTO.getLugar(),evento_publicacionDTO.getLink(),evento_publicacionDTO.getEvento_publicacion_tipo_id_eptipo());
    }

    public List<Evento_publicacionDTO> getAll(){
        return eventoPublicacionDAO.findAll(); // proporcionar userID Cambiar en union
    }
}
