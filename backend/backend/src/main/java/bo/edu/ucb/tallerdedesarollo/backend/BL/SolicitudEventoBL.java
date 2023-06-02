package bo.edu.ucb.tallerdedesarollo.backend.BL;

import bo.edu.ucb.tallerdedesarollo.backend.DAO.ComentariosDao;
import bo.edu.ucb.tallerdedesarollo.backend.DAO.Evento_publicacionDAO;
import bo.edu.ucb.tallerdedesarollo.backend.DAO.SolicitudEventoDAO;
import bo.edu.ucb.tallerdedesarollo.backend.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class SolicitudEventoBL {
    private SolicitudEventoDAO solicitudEventoDAO;
    private ComentariosDao comentariosDao;
    private EmailServiceImpl emailService;
    private Evento_publicacionDAO eventoPublicacionDAO;

    
    @Autowired
    public SolicitudEventoBL(SolicitudEventoDAO solicitudEventoDAO, ComentariosDao comentariosDao, EmailServiceImpl emailService, Evento_publicacionDAO eventoPublicacionDAO) {
        this.solicitudEventoDAO = solicitudEventoDAO;
        this.comentariosDao = comentariosDao;
        this.emailService = emailService;
        this.eventoPublicacionDAO=eventoPublicacionDAO;
    }

    public List<SolicitudEventoDTO> getAll(){
        return solicitudEventoDAO.findAll();
    }

    public List<SolicitudEventoDTO> getAllAccepted(String tipo){
        if (tipo.equals("aceptadas")) {
            return solicitudEventoDAO.findAllAccepted(1);
        }else{
            if (tipo.equals("rechazadas")) {
                return solicitudEventoDAO.findAllAccepted(2);
            }else{
                if(tipo.equals("pendientes")){
                    return solicitudEventoDAO.findAllAccepted(0);
                }else{
                    if (tipo.equals("modificacion")) {
                        return solicitudEventoDAO.findAllAccepted(3);
                    }else{
                        return null;
                    }
                }
            }
        }

    }

    public void nuevaSol(SolicitudEventoDTO solicitudEventoDTO){
        solicitudEventoDAO.newSol((int)solicitudEventoDTO.getUsuarios_userid(),solicitudEventoDTO.getDescripcion(),(int)solicitudEventoDTO.getEstado(),(int)solicitudEventoDTO.getEvento_publicacion_ep_id());
    }

    //prueba

    public SolicitudEventoDTO insertSoli(SolicitudEventoDTO solicitudEventoDTO){
        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
        solicitudEventoDTO.setFecha_solicitud(timestamp);
        solicitudEventoDAO.insertSoli(solicitudEventoDTO);
        return solicitudEventoDTO;
    }

    public SolicitudEventoDTO estadoSoli(SolicitudEventoDTO solicitudEventoDTO, Integer id){
        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
        solicitudEventoDTO.setFecha_revisado(timestamp);
        solicitudEventoDAO.estadoSoli(solicitudEventoDTO.getEstado(),solicitudEventoDTO.getFecha_revisado(), id);
        // para el mail-----------------------------
        if(solicitudEventoDTO.getEstado()==1){
            Evento_publicacionDTO ep= eventoPublicacionDAO.getEvento(id);
            emailService.sendSimpleMessage("rene.vicente@ucb.edu.bo", "La solicitud ''"+ep.getTitulo()+"'' fue ACEPTADA","" +
                    "La Solicitud ''" +ep.getTitulo()+"'' fue revisada y ACEPTADA con los datos: "+" TITULO: "+ep.getTitulo()+ " DESCRIPCION: " + ep.getDescripcion() +" LINK/LUGAR: "+ ep.getLink()+ " "+ ep.getLugar());
        }
        if(solicitudEventoDTO.getEstado()==2){
            Evento_publicacionDTO ep= eventoPublicacionDAO.getEvento(id);
            emailService.sendSimpleMessage("rene.vicente@ucb.edu.bo", "La solicitud ''"+ep.getTitulo()+"'' fue RECHAZADA","" +
                    "La Solicitud ''" +ep.getTitulo()+"'' fue revisada y RECHAZADA ");
        }

        //-----------------------------------------------
        return solicitudEventoDTO;
    }

    // public SolicitudEventoDTO rechazarSolicitud(Integer id, ComentarioDTO comentario) {
    //     SolicitudEventoDTO solicitud = solicitudEventoDAO.findAllById(id);
    //     Long datetime = System.currentTimeMillis();
    //     Timestamp timestamp = new Timestamp(datetime);
    //     solicitudEventoDAO.estadoSoli(2, timestamp, id);
    //     comentariosDao.insertComentarioSolicitud(comentario.getComentario(), id);
    //     return solicitud;
    // }

    public SolicitudEventoDTO getSolicitudById(Integer id) {
        return solicitudEventoDAO.findAllById(id);
    }

    public String getPublico(Integer solicitud_id){
        List<Publico_edadDTO> edad;
        List<Publico_tipoDTO> tipo;
        String res="";
        try {
            edad= solicitudEventoDAO.findpublicedad(solicitud_id);
            if(!edad.isEmpty()){
                res="Edades: ";
                for (Publico_edadDTO edades: edad) {
                    res=res+edades.getEdad_inicio()+" - "+edades.getEdad_fin() + " ";
                }
            }
            tipo= solicitudEventoDAO.findpublic(solicitud_id);
            if(!tipo.isEmpty()){
                res=res+" Tipos: ";
                for (Publico_tipoDTO tipos: tipo) {
                    res=res+tipos.getTipo()+"  ";
                }
            }
        }catch (Exception e){

        }
        return res;
    }

    public ComentarioDTO insetComentario (Integer user_id, ComentarioDTO comentarioDTO){
        comentariosDao.insertComentarioSolicitud(comentarioDTO.getComentario(),user_id);
        SolicitudEventoDTO st= solicitudEventoDAO.findAllById(user_id);
        Evento_publicacionDTO ep= eventoPublicacionDAO.getEvento((int)st.getEvento_publicacion_ep_id());
        emailService.sendSimpleMessage("rene.vicente@ucb.edu.bo", "La solicitud ''"+ep.getTitulo()+"'' fue puesta en revision","" +
                "La Solicitud ''" +ep.getTitulo()+"'' fue revisada y debe ser modificada, el motivo es: "+comentarioDTO.getComentario());
      return comentarioDTO;
    };

    public List<ComentarioDTO> getcomentarios (Integer id){
        return comentariosDao.findcomhistory(id);
    }

    public SolicitudEventoDTO getSolicitudByEventId(Integer id) {
        return solicitudEventoDAO.findAllByEvent(id);
    }

}
