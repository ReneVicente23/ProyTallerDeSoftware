package bo.edu.ucb.tallerdedesarollo.backend.BL;

import bo.edu.ucb.tallerdedesarollo.backend.DAO.Evento_publicacionDAO;
import bo.edu.ucb.tallerdedesarollo.backend.DTO.CategoriaDTO;
import bo.edu.ucb.tallerdedesarollo.backend.DTO.EventoRecepcionDTO;
import bo.edu.ucb.tallerdedesarollo.backend.DTO.Evento_publicacionDTO;
import bo.edu.ucb.tallerdedesarollo.backend.DTO.InteresesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Evento_PublicacionBL {
    Evento_publicacionDAO eventoPublicacionDAO;

    @Autowired
    public Evento_PublicacionBL(Evento_publicacionDAO eventoPublicacionDAO) {
        this.eventoPublicacionDAO = eventoPublicacionDAO;
    }
    //Crea un nuevo eventoPublicacion
    public void newEvento_publicacion (Evento_publicacionDTO evento_publicacionDTO){
        eventoPublicacionDAO.saveEvento_publicacion(evento_publicacionDTO.getTitulo(),evento_publicacionDTO.getDescripcion(),evento_publicacionDTO.getId_imagen(),evento_publicacionDTO.getLugar(),evento_publicacionDTO.getLink(),evento_publicacionDTO.getEvento_publicacion_tipo_id_eptipo());
    }


    public Integer newEvento (EventoRecepcionDTO eventoRecepcionDTO){
        eventoPublicacionDAO.saveEvento_publicacion(eventoRecepcionDTO.getTitulo(),eventoRecepcionDTO.getDescripcion(),eventoRecepcionDTO.getId_imagen(),eventoRecepcionDTO.getLugar(),eventoRecepcionDTO.getLink(),1);
        Integer idEvento=eventoPublicacionDAO.findIdByContend(eventoRecepcionDTO.getTitulo(),eventoRecepcionDTO.getDescripcion(),eventoRecepcionDTO.getId_imagen(),eventoRecepcionDTO.getLugar(),eventoRecepcionDTO.getLink(),1);
        for (InteresesDTO cts: eventoRecepcionDTO.getInteresesDTOS()) {
            //System.out.println(cts.toString());
            eventoPublicacionDAO.save_interes((int) cts.getInteresid(),idEvento);
        }
        if(!eventoRecepcionDTO.getPublico().contentEquals("")){
            String[] publicoaux= eventoRecepcionDTO.getPublico().split("-");
            System.out.println("Estas Aqui----------------");
            Integer auxi=2;
            if(publicoaux[0].contentEquals("Edad")){
                auxi=1;

                for(int i=0;i<publicoaux.length-1;i++){
                    eventoPublicacionDAO.save_publico(idEvento,auxi,Integer.parseInt(publicoaux[i+1]),1);
                    System.out.println("Edad "+publicoaux[i+1]);
                }
            }else{
                for(int i=0;i<publicoaux.length-1;i++){
                    eventoPublicacionDAO.save_publico(idEvento,auxi,1,Integer.parseInt(publicoaux[i+1]));
                }
            }

        }
        //System.out.println("publico: "+eventoRecepcionDTO.getPublico());
        //eventoPublicacionDAO.save_publico(idEvento,1,2,1); //llenar con datos de front
        return idEvento;
    }

    public List<Evento_publicacionDTO> getAll(){
        return eventoPublicacionDAO.findAll(); // proporcionar userID Cambiar en union
    }

    //Prueba
    public Evento_publicacionDTO getEventoById(Integer id){
        Evento_publicacionDTO evento = eventoPublicacionDAO.getEvento(id);
        return evento;
    }

    public List<Evento_publicacionDTO> getRecomendaciones_v1 (String googleid){
        return eventoPublicacionDAO.getRecomendaciones_V1(googleid);
    }
    public List<Evento_publicacionDTO> getRecomendaciones_v2 (String googleid){
        return eventoPublicacionDAO.getRecomendaciones_V2(googleid);
    }

    public List<Evento_publicacionDTO> getRecomendaciones_v3 (String googleid, Integer edadID){
        return eventoPublicacionDAO.getRecomendaciones_V3(googleid,edadID);
    }

    public void updateEventoPublicacion(Evento_publicacionDTO evento, Integer id) {
        eventoPublicacionDAO.updatePublicacion(evento.getTitulo(), evento.getDescripcion(), evento.getId_imagen(), evento.getLugar(), evento.getLink(), id);
    }

}
