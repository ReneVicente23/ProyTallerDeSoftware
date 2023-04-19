package bo.edu.ucb.tallerdedesarollo.backend.BL;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.edu.ucb.tallerdedesarollo.backend.DAO.UsuarioDAO;
import bo.edu.ucb.tallerdedesarollo.backend.DTO.InteresesUsuarioDTO;
import bo.edu.ucb.tallerdedesarollo.backend.DTO.UsuariosDTO;
import bo.edu.ucb.tallerdedesarollo.backend.DTO.UsuariosSignInDTO;

@Service
public class UsuariosBL {
    
    Logger LOOGER = LoggerFactory.getLogger(InteresesBL.class);
    private UsuarioDAO usuarioDAO;

    @Autowired
    public UsuariosBL(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public List<UsuariosDTO> getUsuarios() {
        return this.usuarioDAO.findAll();
    }

    public InteresesUsuarioDTO saveInteresesUsuario(Integer usuario_id, Integer subInteres_id) {
        return this.usuarioDAO.saveInteresesUsuario(usuario_id, subInteres_id);
    }

    public UsuariosSignInDTO saveUsuario(UsuariosSignInDTO usuario) {
        this.usuarioDAO.saveUsuario(usuario.getGoogleid(), 1, usuario.getFamily_name(), usuario.getGiven_name(), usuario.getHd(), usuario.getEmail());
        return usuario;
    }
}
