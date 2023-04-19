package bo.edu.ucb.tallerdedesarollo.backend.API;

import bo.edu.ucb.tallerdedesarollo.backend.DAO.InteresesRepository;
import bo.edu.ucb.tallerdedesarollo.backend.DAO.UsuarioRepository;
import bo.edu.ucb.tallerdedesarollo.backend.DAO.UsuarioService;
import bo.edu.ucb.tallerdedesarollo.backend.DTO.InteresesDTO;
import bo.edu.ucb.tallerdedesarollo.backend.DTO.UsuariosDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private UsuarioService usuariosService;

    @GetMapping("/{id}")
    public ResponseEntity<UsuariosDTO> obtenerUsuario(@PathVariable Integer id) {
        UsuariosDTO usuarioDTO = (UsuariosDTO) usuariosService.obtenerIntereses(id);
        if (usuarioDTO != null) {
            return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/intereses/{idInteres}")
    public ResponseEntity<UsuariosDTO> agregarInteres(@PathVariable Integer id, @PathVariable Integer idInteres) {
        UsuariosDTO usuarioDTO = usuariosService.agregarInteres(id, idInteres);
        if (usuarioDTO != null) {
            return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
