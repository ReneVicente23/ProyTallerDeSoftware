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

    @Select("SELECT * FROM usuarios WHERE userid = #{usuario_id}")
    public UsuariosDTO findUsuario(@Param("usuario_id") Integer id);

    @Select("SELECT email FROM usuarios")
    public List<String> findAlllEmails();

    @Insert("INSERT INTO interesesUsuario(usuarios_userId, sub_intereses_id_subinteres) VALUES (#{usuario_id}, #{sub_interes_id})")
    public InteresesUsuarioDTO saveInteresesUsuario(@Param("usuario_id") Integer usuario_id, @Param("sub_interes_id") Integer sub_interes_id);
    
    @Insert("INSERT INTO usuarios(googleid, usertype, family_name, given_name, hd, email, picture) VALUES (#{sub}, #{usertype}, #{family_name}, #{given_name}, #{hd}, #{email}, #{picture})")
    public void saveUsuario(@Param("sub") String sub, @Param("usertype") Integer usertype,@Param("family_name") String family_name, @Param("given_name") String given_name, @Param("hd") String hd, @Param("email") String email, @Param("picture") String picture);

    //User Profile
    @Update("UPDATE usuarios SET nickname = #{nickname}, birthday = #{date}, career = #{career}, usertype = #{usertype}  WHERE userid = #{userid}")
    public void updateUserProfile(@Param("nickname")String nick, @Param("date")Timestamp date, @Param("career")String career, @Param("usertype") Integer usertype, @Param("userid")Integer id);

    @Select("SELECT * FROM  usuarios WHERE googleid = #{googleid}")
    public UserProfileDTO getUserProfile(@Param("googleid")String googleid);
    //-----------------------------------------------------------------------------------------
    @Select("SELECT userid FROM  usuarios WHERE googleid = #{googleid}")
    public Integer getUserid(@Param("googleid")String googleid);

    //Lista de carreras
    @Select("SELECT carrera FROM carrera")
    public List<String> getCarreras();

    @Select("SELECT * FROM usuarios WHERE usertype = 3")
    public List<UsuariosDTO> findByuserType();
}
