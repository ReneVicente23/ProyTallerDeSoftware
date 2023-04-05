package bo.edu.ucb.tallerdedesarollo.backend.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import bo.edu.ucb.tallerdedesarollo.backend.DTO.InteresesDTO;


@Service
public interface InteresesDAO {

    @Select("SELECT interesid, nombre_interes, imagen FROM intereses")
    public List<InteresesDTO> findAll();
    
}
