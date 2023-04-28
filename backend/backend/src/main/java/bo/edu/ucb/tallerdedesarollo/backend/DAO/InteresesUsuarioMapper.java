package bo.edu.ucb.tallerdedesarollo.backend.DAO;

import bo.edu.ucb.tallerdedesarollo.backend.DTO.SubInteres;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface InteresesUsuarioMapper {
    // otros métodos aquí

    @Select("SELECT si.* FROM sub_intereses si " +
            "JOIN interesesusuario iu ON si.id_subinteres = iu.sub_intereses_id_subinteres " +
            "WHERE iu.usuarios_userid = #{idUsuario}")
    List<SubInteres> findSubInteresesByUsuarioId(Integer idUsuario);
}
