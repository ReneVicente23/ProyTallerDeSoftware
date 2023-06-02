package bo.edu.ucb.tallerdedesarollo.backend.API;

import bo.edu.ucb.tallerdedesarollo.backend.BL.Evento_PublicacionBL;
import bo.edu.ucb.tallerdedesarollo.backend.BL.SolicitudEventoBL;
import bo.edu.ucb.tallerdedesarollo.backend.DTO.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


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

    @PutMapping(path="/{id}", produces = APPLICATION_JSON_VALUE)
    public Evento_publicacionDTO EditarEventoPublicacion(@PathVariable("id") Integer id, @RequestBody Evento_publicacionDTO evento) {
         evento_publicacionBL.updateEventoPublicacion(evento, id);
         return evento;
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
    public List<Evento_publicacion_recomendacionDTO> findRecomendaciones(@PathVariable("userid") String googleid) {
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
    // Edita un evento
    @PutMapping(path = "/editmodevento", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE )
    public EventoRecepcionDTO editeventoPublicacion(@RequestBody EventoRecepcionDTO eventoRecepcionDTO) {
        evento_publicacionBL.editEvento(eventoRecepcionDTO);
        return eventoRecepcionDTO;
    }
    //Lista de modificaciones
    @GetMapping(path="/evento/mod/{id}", produces = APPLICATION_JSON_VALUE)
    public List<ModificacionesDTO> findModificaciones(@PathVariable("id") Integer eventID) {
        return evento_publicacionBL.getModificaciones(eventID);
    }

    @GetMapping(path="/evento/publico/{id}", produces = APPLICATION_JSON_VALUE)
    public PublicoDestDTO findEventPublic(@PathVariable("id")Integer eventoID){
        return evento_publicacionBL.getpublicDest(eventoID);
    }
}
