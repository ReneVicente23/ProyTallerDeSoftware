package bo.edu.ucb.tallerdedesarollo.backend.API;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import bo.edu.ucb.tallerdedesarollo.backend.BL.UsuariosBL;
import bo.edu.ucb.tallerdedesarollo.backend.DTO.UsuariosDTO;
import bo.edu.ucb.tallerdedesarollo.backend.DTO.UsuariosSignInDTO;

@RestController()
@CrossOrigin(origins = "*")
@RequestMapping("/v1/usuarios")
public class UsuariosAPI {

    Logger LOGGER = LoggerFactory.getLogger(InteresesAPI.class);
    private UsuariosBL usuariosBL;

    @Autowired
    public UsuariosAPI(UsuariosBL usuariosBL) {
        this.usuariosBL = usuariosBL;
    }

    @GetMapping(path = "", produces = APPLICATION_JSON_VALUE)
    public List<UsuariosDTO> getUsuarios() {
        return usuariosBL.getUsuarios();
    }

    @PostMapping(path = "/create", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE )
    public UsuariosSignInDTO create(@RequestBody UsuariosSignInDTO usuario) {
       usuariosBL.saveUsuario(usuario);
       return usuario;
         
    }

    

    
}