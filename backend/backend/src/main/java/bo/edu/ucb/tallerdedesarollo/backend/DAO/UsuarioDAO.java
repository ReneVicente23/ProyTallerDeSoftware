package bo.edu.ucb.tallerdedesarollo.backend.DAO;

import java.sql.Timestamp;
import java.util.List;

import bo.edu.ucb.tallerdedesarollo.backend.DTO.UserProfileDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import bo.edu.ucb.tallerdedesarollo.backend.DTO.InteresesUsuarioDTO;
import bo.edu.ucb.tallerdedesarollo.backend.DTO.UsuariosDTO;
import bo.edu.ucb.tallerdedesarollo.backend.DTO.UsuariosSignInDTO;

@Service
public interface UsuarioDAO {

    @Select("SELECT * FROM usuarios")
    public List<UsuariosDTO> findAll();

    @Select("SELECT email FROM usuarios")
    public List<String> findAlllEmails();

    @Insert("INSERT INTO interesesUsuario(usuarios_userId, sub_intereses_id_subinteres) VALUES (#{usuario_id}, #{sub_interes_id})")
    public InteresesUsuarioDTO saveInteresesUsuario(@Param("usuario_id") Integer usuario_id, @Param("sub_interes_id") Integer sub_interes_id);
    
    @Insert("INSERT INTO usuarios(googleid, usertype, family_name, given_name, hd, email, picture) VALUES (#{googleid}, #{usertype}, #{family_name}, #{given_name}, #{hd}, #{email}, #{picture})")
    public void saveUsuario(@Param("googleid") Integer googleid, @Param("usertype") Integer usertype,@Param("family_name") String family_name, @Param("given_name") String given_name, @Param("hd") String hd, @Param("email") String email, @Param("picture") String picture);


}
