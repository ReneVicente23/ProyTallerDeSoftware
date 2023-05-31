package bo.edu.ucb.tallerdedesarollo.backend.API;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bo.edu.ucb.tallerdedesarollo.backend.BL.CarreraBL;
import bo.edu.ucb.tallerdedesarollo.backend.DTO.CarreraDTO;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/carrera")
public class CarreraAPI {


    private CarreraBL carreraBL;

    @Autowired
    public CarreraAPI(CarreraBL carreraBL) {
        this.carreraBL = carreraBL;
    }

    @GetMapping(path="/")
    public List<CarreraDTO> findAllSol() {
        return carreraBL.obtenerCarreras();
    }



    
    

}
