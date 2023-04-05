package bo.edu.ucb.tallerdedesarollo.backend.DAO;

import bo.edu.ucb.tallerdedesarollo.backend.DTO.SolicitudEventoDTO;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SolicitudEventoDAO {

    @Select("SELECT * " +
            "FROM solicitudes")
    public List<SolicitudEventoDTO> findAll();

}
