package bo.edu.ucb.tallerdedesarollo.backend.DAO;

import bo.edu.ucb.tallerdedesarollo.backend.DTO.InteresesDTO;
import bo.edu.ucb.tallerdedesarollo.backend.DTO.UsuariosDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UsuarioService {

    private  UsuarioRepository usuariosRepository;
    private InteresesRepository interesesRepository;

    @Autowired
    public void UsuariosService(UsuarioRepository usuariosRepository, InteresesRepository interesesRepository) {
        this.usuariosRepository = usuariosRepository;
        this.interesesRepository = interesesRepository;
    }



    public UsuariosDTO agregarInteres(Integer idUsuario, Integer interes) {
        Optional<UsuariosDTO> usuarioOptional = usuariosRepository.findById(idUsuario);

        if (usuarioOptional.isPresent()) {
            UsuariosDTO usuario = usuarioOptional.get();
            List<InteresesDTO> intereses = usuario.getIntereses();
            intereses.add((InteresesDTO) intereses);
            usuario.setIntereses(intereses);
            usuariosRepository.save(usuario);
        } else {
            throw new NoSuchElementException("El usuario con id " + idUsuario + " no existe.");
        }
        return null;
    }

    public List<InteresesDTO> obtenerIntereses(Integer idUsuario) {
        Optional<UsuariosDTO> usuarioOptional = usuariosRepository.findById(idUsuario);
        if (usuarioOptional.isPresent()) {
            UsuariosDTO usuario = usuarioOptional.get();
            return usuario.getIntereses();
        } else {
            throw new NoSuchElementException("El usuario con id " + idUsuario + " no existe.");
        }
    }
}
