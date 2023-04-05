package bo.edu.ucb.tallerdedesarollo.backend.API;

import bo.edu.ucb.tallerdedesarollo.backend.BL.Evento_PublicacionBL;
import bo.edu.ucb.tallerdedesarollo.backend.BL.SolicitudEventoBL;
import bo.edu.ucb.tallerdedesarollo.backend.DTO.Evento_publicacionDTO;
import bo.edu.ucb.tallerdedesarollo.backend.DTO.InteresesDTO;
import bo.edu.ucb.tallerdedesarollo.backend.DTO.SolicitudEventoDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/eventosol")
public class Evento_publicacionAPI {
    SolicitudEventoBL solicitudEventoBL;
    Evento_PublicacionBL evento_publicacionBL;

    public Evento_publicacionAPI(SolicitudEventoBL solicitudEventoBL, Evento_PublicacionBL evento_publicacionBL) {
        this.solicitudEventoBL = solicitudEventoBL;
        this.evento_publicacionBL = evento_publicacionBL;
    }
    //obtiene todos los eventos_publicacion que se tienen

}
