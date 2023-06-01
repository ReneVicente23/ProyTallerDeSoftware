package bo.edu.ucb.tallerdedesarollo.backend.BL;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import bo.edu.ucb.tallerdedesarollo.backend.DAO.InteresesUsuarioDAO;
import bo.edu.ucb.tallerdedesarollo.backend.DTO.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.edu.ucb.tallerdedesarollo.backend.DAO.UsuarioDAO;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class UsuariosBL {
    
    Logger LOOGER = LoggerFactory.getLogger(InteresesBL.class);
    private UsuarioDAO usuarioDAO;
    private InteresesUsuarioDAO interesesUsuarioDAO;

    @Autowired
    public UsuariosBL(UsuarioDAO usuarioDAO,InteresesUsuarioDAO interesesUsuarioDAO) {
        this.usuarioDAO = usuarioDAO;
        this.interesesUsuarioDAO = interesesUsuarioDAO;
    }

    public List<UsuariosDTO> getUsuarios() {
        return this.usuarioDAO.findAll();
    }

    public UsuariosDTO getUsuario(Integer id) {
        return this.usuarioDAO.findUsuario(id);
    }
    
    public InteresesUsuarioDTO saveInteresesUsuario(Integer usuario_id, Integer subInteres_id) {
        return this.usuarioDAO.saveInteresesUsuario(usuario_id, subInteres_id);
    }

    public UsuariosSignInDTO saveUsuario(UsuariosSignInDTO usuario) {
        Boolean registrado = false;
        List<String> emailsGet = this.usuarioDAO.findAlllEmails();
        for (String email : emailsGet) {
            if(usuario.getEmail().equals(email)){
                registrado = true;
            }
        }
        if (registrado == false) {
            this.usuarioDAO.saveUsuario(usuario.getSub(), 1, usuario.getFamily_name(), usuario.getGiven_name(), usuario.getHd(), usuario.getEmail(), usuario.getPicture());
        }
        return usuario;
    }

    public void asignarInteresUsuario(AsignarInteresUsuarioDTO asignarInteresUsuarioDTO) {
        Integer usuarioId = usuarioDAO.getUserid(asignarInteresUsuarioDTO.getUsuarioId());
        interesesUsuarioDAO.delSubInteres(usuarioId);
        List<Integer> subInteresIds = asignarInteresUsuarioDTO.getSubInteresId();
        for (Integer subInteresId : subInteresIds) {

            InteresesUsuarioDTO interesesUsuarioDTO = new InteresesUsuarioDTO();
            interesesUsuarioDTO.setUsuarios_userId(usuarioId);
            interesesUsuarioDTO.setSub_intereses_id_subinteres(subInteresId);
            this.interesesUsuarioDAO.asignarInteresUsuario(interesesUsuarioDTO.getUsuarios_userId(), interesesUsuarioDTO.getSub_intereses_id_subinteres());
        }
    }

    public List<SubInteres> obtenerSubInteresesPorUsuarioId(String usuarioId) {
        Integer id=usuarioDAO.getUserid(usuarioId);
        return interesesUsuarioDAO.obtenerSubInteresesPorUsuarioId(id);
    }

    public void modUserProfile(UserProfileDTO userProfileDTO, String userid){
        Timestamp ts;
        try {
            ts= new Timestamp(userProfileDTO.getBirthday().getTime()+90000000);
        }catch (NullPointerException e){
            ts= null;
        }
        //System.out.println(ts.getTime()+" -- " +ts.getNanos()+ " -- "+ts.toString());
        Integer id=usuarioDAO.getUserid(userid);
        usuarioDAO.updateUserProfile(userProfileDTO.getNickname(),ts,userProfileDTO.getCareer(), userProfileDTO.getUsertype(), id);
    }

    public UserProfileDTO getUserProfile(String userid){
        return usuarioDAO.getUserProfile(userid);
    }

    public List<String> getCarrera(){
        return usuarioDAO.getCarreras();
    }

    public List<UsuariosDTO> getUsuariosByuserType() {
        return this.usuarioDAO.findByuserType();
    }
}
