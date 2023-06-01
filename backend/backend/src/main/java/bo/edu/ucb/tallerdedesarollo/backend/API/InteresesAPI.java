package bo.edu.ucb.tallerdedesarollo.backend.API;

import java.util.List;

import bo.edu.ucb.tallerdedesarollo.backend.DTO.SubInteresesDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bo.edu.ucb.tallerdedesarollo.backend.BL.InteresesBL;
import bo.edu.ucb.tallerdedesarollo.backend.DAO.InteresesDAO;
import bo.edu.ucb.tallerdedesarollo.backend.DTO.InteresesDTO;
import bo.edu.ucb.tallerdedesarollo.backend.DTO.InteresesSub;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController()
@CrossOrigin(origins = "*")
@RequestMapping("/v1/intereses")
public class InteresesAPI {
    Logger LOGGER = LoggerFactory.getLogger(InteresesAPI.class);
    private InteresesBL interesesBL;

    @Autowired
    public InteresesAPI(InteresesBL interesesBL) {
        this.interesesBL = interesesBL;
    }

    @GetMapping(path = "/", produces = APPLICATION_JSON_VALUE)
    public List<InteresesDTO> getIntereses() {
        return interesesBL.intereses();
    }

    @PostMapping(path = "/", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE )
    public InteresesDTO create(@RequestBody InteresesDTO interes) {
       interesesBL.saveInteres(interes);
       return interes;
         
    }

    @GetMapping(path ="/{id}")
    public InteresesDTO getById(@PathVariable Integer id) {
        return interesesBL.getInteres(id);
    }

    @GetMapping(path = "/mostrar", produces = APPLICATION_JSON_VALUE)
    public List<InteresesSub> getInteresesMostrar() {
        return interesesBL.getIntereseWithSub();
    }

    @GetMapping(path = "/mostrar/{id}", produces = APPLICATION_JSON_VALUE)
    public InteresesSub getInteresesSubMostrar(@PathVariable Integer id) {
        return interesesBL.getInteresSub(id);
    }

    @GetMapping(path = "/mostrar2", produces = APPLICATION_JSON_VALUE)
    public List<SubInteresesDTO> getInteresesMostrar2() {
        return interesesBL.getInteresSub2();
    }
}
