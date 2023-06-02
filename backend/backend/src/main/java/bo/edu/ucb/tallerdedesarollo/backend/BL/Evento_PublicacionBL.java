package bo.edu.ucb.tallerdedesarollo.backend.BL;

import bo.edu.ucb.tallerdedesarollo.backend.DAO.Evento_publicacionDAO;
import bo.edu.ucb.tallerdedesarollo.backend.DAO.SolicitudEventoDAO;
import bo.edu.ucb.tallerdedesarollo.backend.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class Evento_PublicacionBL {
    Evento_publicacionDAO eventoPublicacionDAO;
    UsuariosBL usuariosBL;
    private EmailServiceImpl emailService;
    SolicitudEventoBL solicitudEventoBL;
    InteresesBL interesesBL;


    @Autowired
    public Evento_PublicacionBL(Evento_publicacionDAO eventoPublicacionDAO, UsuariosBL usuariosBL, EmailServiceImpl emailService, SolicitudEventoBL solicitudEventoBL, InteresesBL interesesBL) {
        this.eventoPublicacionDAO = eventoPublicacionDAO;
        this.usuariosBL = usuariosBL;
        this.emailService = emailService;
        this.solicitudEventoBL=solicitudEventoBL;
        this.interesesBL=interesesBL;
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
        // --------------------------------- Mail ----------------------------------------------
        /*
        List<UsuariosDTO> des= usuariosBL.getUsuariosByuserType();
        if(!des.isEmpty()){
            for (UsuariosDTO us: des) {
                emailService.sendSimpleMessage(us.getEmail(), "La solicitud ''"+eventoRecepcionDTO.getTitulo()+"'' fue CREADA","" +
                        "La Solicitud ''" +eventoRecepcionDTO.getTitulo()+"'' Fue creada y espera revision, con los datos: "+" TITULO: "+eventoRecepcionDTO.getTitulo()+ " -- DESCRIPCION: " + eventoRecepcionDTO.getDescripcion() +" -- LINK/LUGAR: "+ eventoRecepcionDTO.getLink()+ " "+ eventoRecepcionDTO.getLugar());
            }
        }
        emailService.sendSimpleMessage("rene.vicente@ucb.edu.bo", "La solicitud ''"+eventoRecepcionDTO.getTitulo()+"'' fue CREADA","" +
                " (reenvio de prueba) La Solicitud ''" +eventoRecepcionDTO.getTitulo()+"'' Fue creada y espera revision, con los datos: "+" TITULO: "+eventoRecepcionDTO.getTitulo()+ " -- DESCRIPCION: " + eventoRecepcionDTO.getDescripcion() +" -- LINK/LUGAR: "+ eventoRecepcionDTO.getLink()+ " "+ eventoRecepcionDTO.getLugar());
        */
        //---------------------------------- Fin Mail -------------------------------------------

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

    public List<Evento_publicacion_recomendacionDTO> getRecomendaciones_v1 (String googleid){
        UserProfileDTO user= usuariosBL.getUserProfile(googleid);
        List<Evento_publicacionDTO> listaux;
        List<Evento_publicacion_recomendacionDTO> listaux2=new ArrayList<Evento_publicacion_recomendacionDTO>();
        Long edad;
        Integer aux=2;
        List<SubInteres> in= usuariosBL.obtenerSubInteresesPorUsuarioId(googleid);
        if(in.isEmpty()){
            if(user.getBirthday()==null){
                listaux= eventoPublicacionDAO.getRecomendaciones_V3(googleid);
                System.out.println("Usuario sin edad ni subintereses ");
            }else{
                Long datetime = System.currentTimeMillis();
                edad=((datetime)-(user.getBirthday().getTime()))/31557600000L;
                if(edad<=30){
                    aux=2;
                }else{
                    if(edad<=60){
                        aux=3;
                    }else{
                        aux=4;
                    }
                }
                System.out.println("Edad: "+edad+ " group: "+ aux);
                listaux= eventoPublicacionDAO.getRecomendaciones_V4(googleid,aux);
                System.out.println("Usuario sin subintereses ");

            }
        }else{
            if(user.getBirthday()!=null){
                try {
                    Long datetime = System.currentTimeMillis();
                    edad=((datetime)-(user.getBirthday().getTime()))/31557600000L;
                    if(edad<=30){
                        aux=2;
                    }else{
                        if(edad<=60){
                            aux=3;
                        }else{
                            aux=4;
                        }
                    }
                    System.out.println("Edad: "+edad+ " group: "+ aux);
                    listaux= eventoPublicacionDAO.getRecomendaciones_V1(googleid,aux);
                    System.out.println("Usuario completo ");
                }catch (NullPointerException e){
                    listaux=eventoPublicacionDAO.getRecomendaciones_V2(googleid);
                    System.out.println("Usuario sin edad ");
                }
            }else{
                listaux=eventoPublicacionDAO.getRecomendaciones_V2(googleid);
                System.out.println("Usuario sin edad ");
            }
        }
        String mesage = "Por tus intereses te recomendamos: ";
        for (Evento_publicacionDTO et: listaux) {
            mesage = mesage +" *****  Titulo: " +et.getTitulo() + " - Descripcion: "+et.getDescripcion() + " - Lugar/Link "+ et.getLugar()+et.getLink();
            SolicitudEventoDTO st= solicitudEventoBL.getSolicitudByEventId(et.getEp_id());
            List<SubInteresesDTO> gt= interesesBL.getInteresSubByEvent(et.getEp_id());
            listaux2.add(new Evento_publicacion_recomendacionDTO(et.getEp_id(),et.getTitulo(),et.getDescripcion(),et.getId_imagen(),et.getLugar(),et.getLink(),et.getEvento_publicacion_tipo_id_eptipo(),st.getFecha_revisado(),gt));
        }
        //-------------------- Enviador de solicitudes ---------------------------
        /* para cuando se envia userid correcto
        emailService.sendSimpleMessage(user.getEmail(), "Eventos recomendados","" +
                mesage);*/
        //------------------------------------------------------------------------
        //-------------------- Enviador de solicitudes ---------------------------
        /*
        emailService.sendSimpleMessage("rene.vicente@ucb.edu.bo", "Eventos recomendados","" +
                mesage);*/
        //------------------------------------------------------------------------
        return listaux2;
    }
    public List<Evento_publicacionDTO> getRecomendaciones_v2 (String googleid){
        return eventoPublicacionDAO.getRecomendaciones_V2(googleid);
    }

    public List<Evento_publicacionDTO> getRecomendaciones_v3 (String googleid, Integer edadID){
        return eventoPublicacionDAO.getRecomendaciones_V3(googleid);
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

    public PublicoDestDTO getpublicDest(Integer id){
        return new PublicoDestDTO(eventoPublicacionDAO.findEdadDes(id),eventoPublicacionDAO.findpublucoDes(id));
    }

}
