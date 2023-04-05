package bo.edu.ucb.tallerdedesarollo.BL;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.edu.ucb.tallerdedesarollo.backend.DAO.InteresesDAO;
import bo.edu.ucb.tallerdedesarollo.backend.DTO.InteresesDTO;



@Service
public class InteresesBL {
    Logger LOOGER = LoggerFactory.getLogger(InteresesBL.class);
    private InteresesDAO interesesDAO;

    @Autowired
    public InteresesBL(InteresesDAO interesesDAO) {
        this.interesesDAO = interesesDAO;
    }

    public List<InteresesDTO> intereses (){
       return this.interesesDAO.findAll();
    }

    


    
}
