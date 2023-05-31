package bo.edu.ucb.tallerdedesarollo.backend.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import bo.edu.ucb.tallerdedesarollo.backend.DTO.CarreraDTO;

@Service
public interface CarreraDao {
    
    @Insert("INSERT INTO carrera(carrera) VALUES (#{carrera})")
    public void insertCarera(@Param("carrera") String carrera);

    @Select("SELECT id_carrera, carrera FROM carrera")
    public List<CarreraDTO> findAllCarrers();
}
