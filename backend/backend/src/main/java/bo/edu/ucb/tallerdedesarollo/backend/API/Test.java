package bo.edu.ucb.tallerdedesarollo.backend.API;

import bo.edu.ucb.tallerdedesarollo.backend.DTO.Evento_publicacionDTO;
import bo.edu.ucb.tallerdedesarollo.backend.DTO.SolicitudEventoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api")
public class Test {

    @Autowired
    private JavaMailSender mailtw;

    public Test() {
    }

    @GetMapping(path="/", produces = APPLICATION_JSON_VALUE)
    public String test() {
        return "Hola Mundo";
    }

    @PostMapping("/envioCorreo")
    public ResponseEntity<?> enviar_correo(){
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo("carlos.peralta@ucb.edu.bo");
        email.setCc("rene.vicente@ucb.edu.bo");
        email.setFrom("tallerdesarrollo@outlook.com");
        email.setSubject("Servidor de Correo");
        email.setText("Enviando Correos con Copia");
        mailtw.send(email);

        return new ResponseEntity<>(true, HttpStatus.OK);
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




}
