package bo.edu.ucb.tallerdedesarollo.backend.BL;

import bo.edu.ucb.tallerdedesarollo.backend.DAO.Evento_publicacionDAO;
import bo.edu.ucb.tallerdedesarollo.backend.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class Evento_PublicacionBL {
    Evento_publicacionDAO eventoPublicacionDAO;
    UsuariosBL usuariosBL;

    @Autowired
    public Evento_PublicacionBL(Evento_publicacionDAO eventoPublicacionDAO, UsuariosBL usuariosBL) {
        this.eventoPublicacionDAO = eventoPublicacionDAO;
        this.usuariosBL = usuariosBL;
    }
    //Crea un nuevo eventoPublicacion
    public void newEvento_publicacion (Evento_publicacionDTO evento_publicacionDTO){
        eventoPublicacionDAO.saveEvento_publicacion(evento_publicacionDTO.getTitulo(),evento_publicacionDTO.getDescripcion(),evento_publicacionDTO.getId_imagen(),evento_publicacionDTO.getLugar(),evento_publicacionDTO.getLink(),evento_publicacionDTO.getEvento_publicacion_tipo_id_eptipo());
    }


    public Integer newEvento (EventoRecepcionDTO eventoRecepcionDTO){
        eventoPublicacionDAO.saveEvento_publicacion(eventoRecepcionDTO.getTitulo(),eventoRecepcionDTO.getDescripcion(),eventoRecepcionDTO.getId_imagen(),eventoRecepcionDTO.getLugar(),eventoRecepcionDTO.getLink(),1);
        Integer idEvento=eventoPublicacionDAO.findIdByContend(eventoRecepcionDTO.getTitulo(),eventoRecepcionDTO.getDescripcion(),eventoRecepcionDTO.getId_imagen(),eventoRecepcionDTO.getLugar(),eventoRecepcionDTO.getLink(),1);
        for (SubInteresesDTO cts: eventoRecepcionDTO.getInteresesDTOS()) {
            //System.out.println(cts.toString());
            eventoPublicacionDAO.save_interes((int) cts.getId_subinteres(),idEvento);
        }
        if(!eventoRecepcionDTO.getPublico().contentEquals("")){
            String[] publicoaux= eventoRecepcionDTO.getPublico().split("/");
            System.out.println("Estas Aqui----------------");
            Integer auxi=3;
            if(!publicoaux[0].contentEquals("")){
                //auxi=1;
                String[] pb2=publicoaux[0].split("-");
                for(int i=0;i<pb2.length-1;i++){
                    eventoPublicacionDAO.save_publico(idEvento,auxi,Integer.parseInt(pb2[i+1]),1);
                    System.out.println("Edad "+pb2[0]);
                }
            }
            if(!publicoaux[1].contentEquals("")){
                String[] pb2=publicoaux[1].split("-");
                for(int i=0;i<pb2.length-1;i++){
                    eventoPublicacionDAO.save_publico(idEvento,auxi,1,Integer.parseInt(pb2[i+1]));
                    System.out.println("Tipo "+pb2[i+1]);
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
        UserProfileDTO user= usuariosBL.getUserProfile(googleid);
        Long edad;
        if(user.getBirthday()!=null){
            Long datetime = System.currentTimeMillis();
            edad=(datetime)-(user.getBirthday().getTime());
            System.out.println("Edad: "+edad);
            return eventoPublicacionDAO.getRecomendaciones_V1(googleid,2);
        }
        return eventoPublicacionDAO.getRecomendaciones_V2(googleid);
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

    public Integer editEvento (EventoRecepcionDTO eventoRecepcionDTO){
        String log="";
        Integer idEvento= (int) eventoRecepcionDTO.getEp_id();
        Evento_publicacionDTO evento = eventoPublicacionDAO.getEvento(idEvento);
        // Modulo de log
        if(!evento.getTitulo().contentEquals(eventoRecepcionDTO.getTitulo())){
            log = log+ "Modificacion de titulo, de: "+evento.getTitulo()+" a: "+eventoRecepcionDTO.getTitulo()+" / ";
        }
        if(!evento.getDescripcion().contentEquals(eventoRecepcionDTO.getDescripcion())){
            log = log+ "Modificacion de descripcion, de: "+evento.getDescripcion()+" a: "+eventoRecepcionDTO.getDescripcion()+" / ";
        }
        if(!evento.getDescripcion().contentEquals(eventoRecepcionDTO.getDescripcion())){
            log = log+ "Modificacion de descripcion, de: "+evento.getDescripcion()+" a: "+eventoRecepcionDTO.getDescripcion()+" / ";
        }
        if(!evento.getId_imagen().contentEquals(eventoRecepcionDTO.getId_imagen())){
            log = log+ "Modificacion de imagen, de: "+evento.getId_imagen()+" a: "+eventoRecepcionDTO.getId_imagen()+" / ";
        }
        if(!evento.getId_imagen().contentEquals(eventoRecepcionDTO.getId_imagen())){
            log = log+ "Modificacion de imagen, de: "+evento.getId_imagen()+" a: "+eventoRecepcionDTO.getId_imagen()+" / ";
        }
        if(!evento.getLink().contentEquals(eventoRecepcionDTO.getLink())||!evento.getLugar().contentEquals(eventoRecepcionDTO.getLugar())){
            log = log+ "Modificacion de Link/Lugar, de: "+evento.getLugar()+evento.getLink()+" a: "+eventoRecepcionDTO.getLink()+eventoRecepcionDTO.getLugar()+" / ";
        }
        //------------------ fin modulo log
        eventoPublicacionDAO.updateEvento_publicacion(eventoRecepcionDTO.getTitulo(),eventoRecepcionDTO.getDescripcion(),eventoRecepcionDTO.getId_imagen(),eventoRecepcionDTO.getLugar(),eventoRecepcionDTO.getLink(), (int) eventoRecepcionDTO.getEp_id());
        if(eventoRecepcionDTO.getInteresesDTOS().length>0){
            eventoPublicacionDAO.deleteallIntereses(idEvento);
            log=log+" Modificacion de subcategorias: ";
            for (SubInteresesDTO cts: eventoRecepcionDTO.getInteresesDTOS()) {
                eventoPublicacionDAO.save_interes((int) cts.getId_subinteres(),idEvento);
                log=log+ cts.getNombre()+ " ";
            }
            log=log+" / ";
        }
        if(!eventoRecepcionDTO.getPublico().contentEquals("")){
            eventoPublicacionDAO.deleteallPublic(idEvento);
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
        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
        eventoPublicacionDAO.insertmod(log,timestamp,idEvento);
        //System.out.println("publico: "+eventoRecepcionDTO.getPublico());
        //eventoPublicacionDAO.save_publico(idEvento,1,2,1); //llenar con datos de front
        return idEvento;
    }

    public List<ModificacionesDTO> getModificaciones (Integer id){
        return eventoPublicacionDAO.findmodhistory(id);

    }

}
