package bo.edu.ucb.tallerdedesarollo.backend.API;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bo.edu.ucb.tallerdedesarollo.backend.BL.InteresesBL;
import bo.edu.ucb.tallerdedesarollo.backend.DTO.InteresesDTO;

import org.springframework.web.bind.annotation.GetMapping;

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
    

    
}
