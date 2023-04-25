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
@CrossOrigin(origins = "*")
@RequestMapping("/eventosol")
public class Evento_publicacionAPI {
    SolicitudEventoBL solicitudEventoBL;
    Evento_PublicacionBL evento_publicacionBL;

    public Evento_publicacionAPI(SolicitudEventoBL solicitudEventoBL, Evento_PublicacionBL evento_publicacionBL) {
        this.solicitudEventoBL = solicitudEventoBL;
        this.evento_publicacionBL = evento_publicacionBL;
    }
    //obtiene todos los eventos_publicacion que se tienen

    @GetMapping(path="/", produces = APPLICATION_JSON_VALUE) // necesitamos auth de front
    public List<Evento_publicacionDTO> findAllSol() {
        return evento_publicacionBL.getAll();
    }

    @PostMapping(path = "/new", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE )
    public Evento_publicacionDTO create(@RequestBody Evento_publicacionDTO evento_publicacionDTO) {
        evento_publicacionBL.newEvento_publicacion(evento_publicacionDTO);
        return evento_publicacionDTO;
    }

    //Prueba
    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public Evento_publicacionDTO getEvento(@PathVariable("id") Integer id){
        Evento_publicacionDTO evento = evento_publicacionBL.getEventoById(id);
        return evento;
    }

    @GetMapping(path="/rec/{userid}", produces = APPLICATION_JSON_VALUE) // necesitamos auth de front
    public List<Evento_publicacionDTO> findRecomendaciones(@PathVariable("userid") String googleid) {
        return evento_publicacionBL.getRecomendaciones_v1(googleid);
    }

    @GetMapping(path="/rec2/{userid}", produces = APPLICATION_JSON_VALUE) // necesitamos auth de front
    public List<Evento_publicacionDTO> findRecomendaciones2(@PathVariable("userid") String googleid) {
        return evento_publicacionBL.getRecomendaciones_v2(googleid);
    }

    @GetMapping(path="/rec3/{userid}", produces = APPLICATION_JSON_VALUE) // necesitamos auth de front
    public List<Evento_publicacionDTO> findRecomendaciones3(@PathVariable("userid") String googleid) {
        return evento_publicacionBL.getRecomendaciones_v3(googleid,2);
    }


}
