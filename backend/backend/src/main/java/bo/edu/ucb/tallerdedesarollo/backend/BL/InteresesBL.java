package bo.edu.ucb.tallerdedesarollo.backend.BL;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.edu.ucb.tallerdedesarollo.backend.DAO.InteresesDAO;
import bo.edu.ucb.tallerdedesarollo.backend.DTO.InteresesDTO;
import bo.edu.ucb.tallerdedesarollo.backend.DTO.InteresesSub;
import bo.edu.ucb.tallerdedesarollo.backend.DTO.SubInteresesDTO;



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

    public void saveInteres(InteresesDTO interesesDTO){
        String nombre  = interesesDTO.getNombre_interes();
        String imagen = interesesDTO.getImagen();
        this.interesesDAO.saveInteres(nombre,imagen);
    }
    
    public InteresesDTO getInteres(Integer Id) {
        Integer id= Math.toIntExact(Id);
        return this.interesesDAO.findInteresId(id);
    }

    public List<InteresesSub> getIntereseWithSub() {
        List<InteresesDTO> intereses = this.interesesDAO.findAll();
        List<InteresesSub> interesesSubs = new ArrayList<InteresesSub>();

        for (InteresesDTO interes : intereses) {
            Integer idInteres =  interes.getInteresid();
            List<SubInteresesDTO> subInter = interesesDAO.findAllSubIntereses(idInteres);
            InteresesSub interesesSub = new InteresesSub(interes.getInteresid(),interes.getNombre_interes(), interes.getImagen(),subInter);
            interesesSubs.add(interesesSub);
        }
        return interesesSubs;
    }

    public InteresesSub getInteresSub(Integer id) {
        InteresesSub interes = this.interesesDAO.findInteresSubId(id);
        List<SubInteresesDTO> subInter = this.interesesDAO.findAllSubIntereses(id);
        InteresesSub newInteres = new InteresesSub();
        newInteres.setInteresid(interes.getInteresid());
        newInteres.setNombre_interes(interes.getNombre_interes());
        newInteres.setImagen(interes.getImagen());
        newInteres.setSubIntereses(subInter);
        return newInteres;
    }


    public List<SubInteresesDTO> getInteresSub2() {
        List<SubInteresesDTO> subInter = this.interesesDAO.findAllSubIntereses2();
//        SubInteresesDTO newInteres = new SubInteresesDTO();
//        newInteres.setIntereses_interesId(newInteres.getIntereses_interesId());
//        newInteres.setNombre(newInteres.getNombre());
//        newInteres.setId_subinteres(newInteres.getId_subinteres());
        return subInter;
    }

    public List<SubInteresesDTO> getInteresSubByEvent(Integer id) {
        List<SubInteresesDTO> subInter = this.interesesDAO.findAllSubInteresesFromEvent(id);
        return subInter;
    }
}
