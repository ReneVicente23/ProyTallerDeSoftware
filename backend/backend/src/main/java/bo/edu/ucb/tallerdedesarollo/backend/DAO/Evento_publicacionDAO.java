package bo.edu.ucb.tallerdedesarollo.backend.DAO;

import bo.edu.ucb.tallerdedesarollo.backend.DTO.Evento_publicacionDTO;
import bo.edu.ucb.tallerdedesarollo.backend.DTO.ModificacionesDTO;
import bo.edu.ucb.tallerdedesarollo.backend.DTO.Publico_edadDTO;
import bo.edu.ucb.tallerdedesarollo.backend.DTO.Publico_tipoDTO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public interface Evento_publicacionDAO {
    @Select("SELECT ep_id,titulo,descripcion,id_imagen,lugar,link,evento_publicacion_tipo_id_eptipo " +
            "FROM evento_publicacion "  ) // Validacion de usuario necesaria
    public List<Evento_publicacionDTO> findAll();

    @Insert("INSERT INTO evento_publicacion (titulo, descripcion, id_imagen, lugar, link, evento_publicacion_tipo_id_eptipo)" +
            " VALUES (#{titulo}, #{descripcion}, #{id_imagen}, #{lugar}, #{link}, #{id_tipo})")
    public void saveEvento_publicacion(@Param("titulo") String titulo, @Param("descripcion") String descripcion, @Param("id_imagen") String id_imagen,@Param("lugar") String lugar,@Param("link") String link,@Param("id_tipo") Integer id_tipo);

    @Select("SELECT ep_id " +
            "FROM evento_publicacion WHERE titulo= #{titulo} AND descripcion=#{descripcion} AND id_imagen= #{id_imagen} AND lugar= #{lugar} AND link=#{link} LIMIT 1"  ) // Validacion de usuario necesaria
    public Integer findIdByContend(@Param("titulo") String titulo, @Param("descripcion") String descripcion, @Param("id_imagen") String id_imagen,@Param("lugar") String lugar,@Param("link") String link,@Param("id_tipo") Integer id_tipo);
    @Insert("INSERT INTO interesesEventos ("/*Intereses_interesId*/+"sub_intereses_id_subinteres , evento_publicacion_ep_id)" +
            " VALUES (#{interesID}, #{eventoID})")
    public void save_interes(@Param("interesID") Integer idInteres, @Param("eventoID") Integer idEvento);

    @Insert("INSERT INTO publico_destino_ep (evento_publicacion_ep_id, tipopublico,rangos_edad_id_rangos_edad,publico_tipo_id_publico)" +
            " VALUES (#{eventoID}, #{tipo},#{rangoID},#{tipoPublico})")
    public void save_publico(@Param("eventoID") Integer idEvento,@Param("tipo") Integer tipo,@Param("rangoID") Integer rangoID,@Param("tipoPublico") Integer tipoPublico);

    @Select("SELECT * " +
            "FROM evento_publicacion WHERE titulo= #{titulo} AND descripcion=#{descripcion} AND id_imagen= #{id_imagen} AND lugar= #{lugar} AND link=#{link} LIMIT 1"  ) // Validacion de usuario necesaria
    public Evento_publicacionDTO findByContend(@Param("titulo") String titulo, @Param("descripcion") String descripcion, @Param("id_imagen") String id_imagen,@Param("lugar") String lugar,@Param("link") String link,@Param("id_tipo") Integer id_tipo);
    @Update("UPDATE evento_publicacion SET id_imagen= #{img} WHERE ep_id = #{id}")
    public void updateimagen(@Param("ID") Integer id,@Param("img") String img);
    //prueba

    @Select(" SELECT titulo, descripcion, id_imagen, lugar, link FROM evento_publicacion " +
            " WHERE ep_id = #{id} ")
    public Evento_publicacionDTO getEvento(@Param("id") Integer id);

    @Select(" SELECT distinct on(p.ep_id) p.ep_id, p.titulo, p.descripcion, p.id_imagen, p.lugar, p.link FROM evento_publicacion p " +
            " JOIN interesesEventos i ON p.ep_id = i.evento_publicacion_ep_id JOIN sub_intereses o ON o.id_subinteres = i.sub_intereses_id_subinteres " +
            //" JOIN sub_intereses s ON o.interesId = s.Intereses_interesId " + // Validar para posibles modificaciones a BD
            " JOIN interesesUsuario u ON u.sub_intereses_id_subinteres = o.id_subinteres JOIN usuarios k ON k.userId = u.usuarios_userId " +
            " JOIN publico_destino_ep d ON p.ep_id = d.evento_publicacion_ep_id JOIN publico_tipo l ON l.id_publico = d.publico_tipo_id_publico" + // publico destino
            " JOIN solicitudes j ON j.evento_publicacion_ep_id = p.ep_id " +
            " WHERE k.googleId = #{user_id} AND (d.publico_tipo_id_publico = (k.usertype + 1) OR d.rangos_edad_id_rangos_edad = #{edad})" +  // si no funciona cambiar # por $ o biceversa
            " AND j.fecha_revisado is not null AND j.estado = 1 ORDER BY p.ep_id desc,j.fecha_revisado desc") // anadir desc
    public List<Evento_publicacionDTO> getRecomendaciones_V1(@Param("user_id")String user_id, @Param("edad")Integer edad); //Retorna las recomendaciones solo por Intereses

    @Select(" SELECT distinct on(p.ep_id) p.ep_id, p.titulo, p.descripcion, p.id_imagen, p.lugar, p.link FROM evento_publicacion p " +
            " JOIN interesesEventos i ON p.ep_id = i.evento_publicacion_ep_id JOIN sub_intereses o ON o.id_subinteres = i.sub_intereses_id_subinteres " +
            //" JOIN sub_intereses s ON o.interesId = s.Intereses_interesId " + // Validar para posibles modificaciones a BD
            " JOIN interesesUsuario u ON u.sub_intereses_id_subinteres = o.id_subinteres JOIN usuarios k ON k.userId = u.usuarios_userId " +
            " JOIN publico_destino_ep d ON p.ep_id = d.evento_publicacion_ep_id JOIN publico_tipo l ON l.id_publico = d.publico_tipo_id_publico" +
            " JOIN solicitudes j ON j.evento_publicacion_ep_id = p.ep_id " +
            " WHERE k.googleId = #{user_id} AND d.publico_tipo_id_publico = (k.usertype + 1)" +
            " AND j.fecha_revisado is not null AND j.estado = 1 ORDER BY p.ep_id desc,j.fecha_revisado desc")
    public List<Evento_publicacionDTO> getRecomendaciones_V2(@Param("user_id")String user_id); //Retorna las recomendaciones por Intereses y tipo

    @Select(" SELECT distinct on(p.ep_id) p.ep_id, p.titulo, p.descripcion, p.id_imagen, p.lugar, p.link FROM evento_publicacion p " +
            " JOIN interesesEventos i ON p.ep_id = i.evento_publicacion_ep_id " +
            //" JOIN sub_intereses s ON o.interesId = s.Intereses_interesId " + // Validar para posibles modificaciones a BD
           // " JOIN usuarios k ON k.userId = u.usuarios_userId " +
            " JOIN publico_destino_ep d ON p.ep_id = d.evento_publicacion_ep_id JOIN publico_tipo l ON l.id_publico = d.publico_tipo_id_publico" +
            " JOIN solicitudes j ON j.evento_publicacion_ep_id = p.ep_id JOIN usuarios k ON (k.usertype+1)= d.publico_tipo_id_publico " +
            " WHERE k.googleId = #{user_id}" +
            " AND j.fecha_revisado is not null AND j.estado = 1 ORDER BY p.ep_id desc,j.fecha_revisado desc") // verificar como se ingresa la edad
    public List<Evento_publicacionDTO> getRecomendaciones_V3(@Param("user_id")String user_id); //Retorna las recomendaciones por Intereses, tipo y edad

    @Select(" SELECT distinct on(p.ep_id) p.ep_id, p.titulo, p.descripcion, p.id_imagen, p.lugar, p.link FROM evento_publicacion p " +
            " JOIN interesesEventos i ON p.ep_id = i.evento_publicacion_ep_id " +
            //" JOIN sub_intereses s ON o.interesId = s.Intereses_interesId " + // Validar para posibles modificaciones a BD
            // " JOIN usuarios k ON k.userId = u.usuarios_userId " +
            " JOIN publico_destino_ep d ON p.ep_id = d.evento_publicacion_ep_id JOIN publico_tipo l ON l.id_publico = d.publico_tipo_id_publico" +
            " JOIN solicitudes j ON j.evento_publicacion_ep_id = p.ep_id JOIN usuarios k ON ((k.usertype+1)= d.publico_tipo_id_publico OR d.rangos_edad_id_rangos_edad = #{edad} )" +
            " WHERE k.googleId = #{user_id}" +
            "  AND j.fecha_revisado is not null AND j.estado = 1 ORDER BY p.ep_id desc,j.fecha_revisado desc") // verificar como se ingresa la edad
    public List<Evento_publicacionDTO> getRecomendaciones_V4(@Param("user_id")String user_id,@Param("edad")Integer edad);
    
    @Update("UPDATE evento_publicacion SET titulo=#{titulo}, descripcion=#{descripcion}, id_imagen=#{id_imagen}, lugar=#{lugar}, link=#{link} WHERE ep_id = #{id}")
    public void updatePublicacion(@Param("titulo") String titulo, @Param("descripcion") String descripcion, @Param("id_imagen") String id_imagen, @Param("lugar") String lugar, @Param("link") String link, @Param("id") Integer id);

    @Update("UPDATE evento_publicacion SET titulo = #{titulo}, descripcion = #{descripcion}, id_imagen = #{id_imagen}, lugar = #{lugar}, link = #{link} " +
            " WHERE ep_id = #{id}")
    public void updateEvento_publicacion(@Param("titulo") String titulo, @Param("descripcion") String descripcion, @Param("id_imagen") String id_imagen,@Param("lugar") String lugar,@Param("link") String link,@Param("id") Integer id_evento);

    @Delete("DELETE FROM interesesEventos WHERE evento_publicacion_ep_id = #{id}")
    public void deleteallIntereses(@Param("id") Integer publicacion_id);

    @Delete("DELETE FROM publico_destino_ep WHERE evento_publicacion_ep_id = #{id}")
    public void deleteallPublic(@Param("id") Integer publicacion_id);

    @Insert("INSERT INTO modificaciones (modificacion,fechamod,evento_publicacion_ep_id)" +
            " VALUES (#{mod},#{date},#{id}) ")
    public void insertmod(@Param("mod") String mdoificacion, @Param("date")Timestamp fecha, @Param("id") Integer publicacion_id);

    @Select("SELECT * " +
            "FROM modificaciones WHERE evento_publicacion_ep_id= #{id}"  )
    public List<ModificacionesDTO> findmodhistory(@Param("id") Integer id);

    @Select("SELECT distinct on(j.id_publico) * " +
            "FROM publico_tipo j JOIN publico_destino_ep i ON j.id_publico=i.publico_tipo_id_publico WHERE i.evento_publicacion_ep_id= #{id} ORDER BY j.id_publico desc")
    public List<Publico_tipoDTO> findpublucoDes(@Param("id") Integer id);

    @Select("SELECT distinct on(j.id_rangos_edad) * " +
            "FROM rangos_edad j JOIN publico_destino_ep i ON j.id_rangos_edad=i.rangos_edad_id_rangos_edad WHERE i.evento_publicacion_ep_id= #{id} ORDER BY j.id_rangos_edad desc"  )
    public List<Publico_edadDTO> findEdadDes(@Param("id") Integer id);
}

