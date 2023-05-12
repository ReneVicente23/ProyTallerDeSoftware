package bo.edu.ucb.tallerdedesarollo.backend.API;

import bo.edu.ucb.tallerdedesarollo.backend.BL.Evento_PublicacionBL;
import bo.edu.ucb.tallerdedesarollo.backend.BL.SolicitudEventoBL;
import bo.edu.ucb.tallerdedesarollo.backend.DTO.Evento_publicacionDTO;
import bo.edu.ucb.tallerdedesarollo.backend.DTO.InteresesDTO;
import bo.edu.ucb.tallerdedesarollo.backend.DTO.SolicitudEventoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/eventosol")
public class Evento_publicacionAPI {
    SolicitudEventoBL solicitudEventoBL;
    Evento_PublicacionBL evento_publicacionBL;

    @Autowired
    private JavaMailSender mailtw;



    public Evento_publicacionAPI(SolicitudEventoBL solicitudEventoBL, Evento_PublicacionBL evento_publicacionBL) {
        this.solicitudEventoBL = solicitudEventoBL;
        this.evento_publicacionBL = evento_publicacionBL;
    }
    //obtiene todos los eventos_publicacion que se tienen

    @GetMapping(path="/", produces = APPLICATION_JSON_VALUE) // necesitamos auth de front
    public List<Evento_publicacionDTO> findAllSol() {
        return evento_publicacionBL.getAll();
    }

//    @PostMapping(path = "/new", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE )
//    public Evento_publicacionDTO create(@RequestBody Evento_publicacionDTO evento_publicacionDTO) {
//        evento_publicacionBL.newEvento_publicacion(evento_publicacionDTO);
//        return evento_publicacionDTO;
//    }
    @PostMapping(path = "/new", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE )
    public Evento_publicacionDTO create(@RequestBody Evento_publicacionDTO evento_publicacionDTO) {
        evento_publicacionBL.newEvento_publicacion(evento_publicacionDTO);

    // Envía el correo
        enviar_correo(evento_publicacionDTO);

        return evento_publicacionDTO;
    }
//    public void enviar_correo(Evento_publicacionDTO evento){
//        SimpleMailMessage email = new SimpleMailMessage();
//        email.setTo("carlos.peralta@ucb.edu.bo");
//        email.setCc("rene.vicente@ucb.edu.bo");
//        email.setFrom("tallerdesarrollo@outlook.com");
//        email.setSubject("Nuevo Evento: " + evento.getTitulo());
//
//        String texto = "Se ha creado un nuevo evento:\n"
//                + "Titulo: " + evento.getTitulo() + "\n"
//                + "Descripcion: " + evento.getDescripcion() + "\n"
//                + "Lugar: " + evento.getLugar() + "\n"
//                + "Link: " + evento.getLink() + "\n";
//        // Agrega más campos aquí si los necesitas
//
//        email.setText(texto);
//        mailtw.send(email);
//    }

    public void enviar_correo(Evento_publicacionDTO evento){
        MimeMessage mail = mailtw.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail);

        try {
            helper.setTo("carlos.peralta@ucb.edu.bo");
            helper.setCc("jpalabral@ucb.edu.bo");
            helper.setFrom("tallerdesarrollo@outlook.com");
            helper.setSubject("Nuevo Evento: " + evento.getTitulo());

            String texto = "<div style='font-family: Arial, sans-serif;'>"
                    + "<h1 style='color: #4a4a4a;'>¡Nuevo Evento!</h1>"
                    + "<p>¡Hola! un Evento con tus intereses en comun acaba de generarse Te invitamos al nuevo evento <strong>" + evento.getTitulo() + "</strong>.</p>"
                    + "<p>Descripción: " + evento.getDescripcion() + "</p>"
                    + "<p>Lugar: " + evento.getLugar() + "</p>"
                    + "<p>Puedes encontrar más detalles en nuestro sitio web: <a href='" + evento.getLink() + "' style='color: #3c78d8;'>Haz clic aquí</a></p>"
                    + "<p>¡Esperamos verte allí!</p>"
                    + "<hr>"
                    + "<p style='font-size: 0.8em; color: #999999;'>Este mensaje es una notificación. Para acceder a la versión web y responder el mensaje, <a href='#' style='color: #3c78d8;'>haz clic aquí</a>.</p>"
                    + "<p style='font-size: 0.8em; color: #999999;'>Haz clic <a href='#' style='color: #3c78d8;'>aquí</a> para configurar qué tipo de notificaciones deseas recibir.</p>"
                    + "</div>";

            helper.setText(texto, true); // El segundo parámetro a true indica que el correo es HTML

            mailtw.send(mail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
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
