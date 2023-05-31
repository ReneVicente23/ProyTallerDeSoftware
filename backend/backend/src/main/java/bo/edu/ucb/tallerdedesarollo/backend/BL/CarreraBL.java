package bo.edu.ucb.tallerdedesarollo.backend.BL;

import java.util.List;

import org.springframework.stereotype.Service;

import bo.edu.ucb.tallerdedesarollo.backend.DAO.CarreraDao;
import bo.edu.ucb.tallerdedesarollo.backend.DTO.CarreraDTO;

@Service
public class CarreraBL {
    
    private CarreraDao carreraDao;

    public CarreraBL(CarreraDao carreraDao) {
        this.carreraDao = carreraDao;
    }

    public List<CarreraDTO> obtenerCarreras() {
        return carreraDao.findAllCarrers();
    }
    
    
}
