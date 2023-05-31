package bo.edu.ucb.tallerdedesarollo.backend.API;

import bo.edu.ucb.tallerdedesarollo.backend.BL.EmailServiceImpl;
import bo.edu.ucb.tallerdedesarollo.backend.DTO.SolicitudEventoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api")
public class Test {
    private EmailServiceImpl emailService;
    @Autowired
    public Test(EmailServiceImpl emailService) {
        this.emailService=emailService;
    }

    @GetMapping(path="/", produces = APPLICATION_JSON_VALUE)
    public String test() {
        emailService.sendSimpleMessage("reneruben1@gmail.com","test", "Hola");
        return "Hola Mundo";
    }
}
