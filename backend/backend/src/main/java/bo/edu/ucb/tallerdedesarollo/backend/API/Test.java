package bo.edu.ucb.tallerdedesarollo.backend.API;

import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api")
public class Test {

    public Test() {
    }

    @GetMapping(path="/", produces = APPLICATION_JSON_VALUE)
    public String test() {
        return "Hola Mundo";
    }
}
