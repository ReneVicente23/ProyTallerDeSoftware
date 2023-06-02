package bo.edu.ucb.tallerdedesarollo.backend.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import bo.edu.ucb.tallerdedesarollo.backend.DTO.InteresesDTO;
import bo.edu.ucb.tallerdedesarollo.backend.DTO.InteresesSub;
import bo.edu.ucb.tallerdedesarollo.backend.DTO.SubInteresesDTO;


@Service
public interface InteresesDAO {

    @Select("SELECT interesid, nombre_interes, imagen FROM intereses")
    public List<InteresesDTO> findAll();

    @Insert("INSERT INTO intereses(nombre_interes, imagen) VALUES (#{nombre_interes}, #{imagen})")
    public void saveInteres(@Param("nombre_interes") String nombre_interes, @Param("imagen") String imagen);

    @Select("SELECT interesid, nombre_interes, imagen FROM intereses  WHERE interesid = #{idinteres}")
    public InteresesSub findInteresSubId(@Param("idinteres") Integer id);

    @Select("SELECT interesid, nombre_interes, imagen FROM intereses  WHERE interesid = #{idinteres}")
    public InteresesDTO findInteresId(@Param("idinteres") Integer id);

    @Select("SELECT * From sub_intereses WHERE Intereses_interesId = (#{idInteres})")
    public List<SubInteresesDTO> findAllSubIntereses(@Param("idInteres") Integer id);

    @Select("SELECT * From sub_intereses")
    public List<SubInteresesDTO> findAllSubIntereses2();

    @Select("SELECT * From sub_intereses j JOIN interesesEventos i ON i.sub_intereses_id_subinteres=j.id_subinteres WHERE i.evento_publicacion_ep_id= #{id} ")
    public List<SubInteresesDTO> findAllSubInteresesFromEvent(@Param("id")Integer id);


}
