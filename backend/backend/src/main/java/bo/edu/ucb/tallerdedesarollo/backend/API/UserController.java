package bo.edu.ucb.tallerdedesarollo.backend.API;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasRole('user')")
public class UserController {

    @GetMapping("/api/users")
    public String getUsers() {
        return "Lista de usuarios";
    }
}