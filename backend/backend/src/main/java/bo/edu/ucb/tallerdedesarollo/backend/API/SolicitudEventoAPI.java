package bo.edu.ucb.tallerdedesarollo.backend.API;

import bo.edu.ucb.tallerdedesarollo.backend.BL.Evento_PublicacionBL;
import bo.edu.ucb.tallerdedesarollo.backend.BL.SolicitudEventoBL;
import bo.edu.ucb.tallerdedesarollo.backend.DTO.ComentarioDTO;
import bo.edu.ucb.tallerdedesarollo.backend.DTO.EventoRecepcionDTO;
import bo.edu.ucb.tallerdedesarollo.backend.DTO.SolicitudEventoDTO;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.*;
import java.util.List;

import static org.springframework.http.MediaType.*;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/solicitud")
public class SolicitudEventoAPI {
    private SolicitudEventoBL solicitudEventoBL;
    private Evento_PublicacionBL evento_publicacionBL;

    public SolicitudEventoAPI(SolicitudEventoBL solicitudEventoBL, Evento_PublicacionBL evento_publicacionBL) {
        this.solicitudEventoBL = solicitudEventoBL;
        this.evento_publicacionBL = evento_publicacionBL;
    }

    @GetMapping(path="/test", produces = APPLICATION_JSON_VALUE)
    public List<SolicitudEventoDTO> findAllSol() {
        return solicitudEventoBL.getAll();
    }

    @GetMapping(path="/{tipoSolicitud}", produces = APPLICATION_JSON_VALUE)
    public List<SolicitudEventoDTO> findAllAccepted(@PathVariable("tipoSolicitud") String solicitud) {
        return solicitudEventoBL.getAllAccepted(solicitud);
    }

    @PostMapping(path = "/new", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE )
    public EventoRecepcionDTO create(@RequestBody EventoRecepcionDTO eventoRecepcionDTO) throws IOException {
        Integer id = evento_publicacionBL.newEvento(eventoRecepcionDTO);
        System.out.println("el id es "+ id);
       //System.out.println(eventoRecepcionDTO.toString());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SolicitudEventoDTO se=new SolicitudEventoDTO(0,1,eventoRecepcionDTO.getTitulo(),0,id,timestamp,null);
       //solicitudEventoBL.nuevaSol(se);
        solicitudEventoBL.insertSoli(se);
       //System.out.println(eventoRecepcionDTO.toString());
        return eventoRecepcionDTO;
    }

    @PostMapping(path = "/", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public SolicitudEventoDTO insertSoli(@RequestBody SolicitudEventoDTO solicitudEventoDTO){
        SolicitudEventoDTO solicitud = solicitudEventoBL.insertSoli(solicitudEventoDTO);
        return solicitud;
    }
    @PutMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public SolicitudEventoDTO estadoSoli(@PathVariable("id") Integer id, @RequestBody SolicitudEventoDTO solicitudEventoDTO){
        SolicitudEventoDTO solicitud = solicitudEventoBL.estadoSoli(solicitudEventoDTO, id);
        return solicitud;
    }

    // @PutMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    // public SolicitudEventoDTO reachzarSolicitud(@PathVariable("id") Integer id, @RequestBody ComentarioDTO comentario){
    //     SolicitudEventoDTO solicitud = solicitudEventoBL.rechazarSolicitud(id, comentario);
    //     return solicitud;
    // }

    @PostMapping(path = "/image", consumes = MULTIPART_FORM_DATA_VALUE)
    public Map<String,String> imagen(@RequestParam("file")MultipartFile file) throws IOException {
        System.out.println(file.getName()+file.getContentType()+"  --  "+file.getOriginalFilename());
        String fileName = file.getOriginalFilename();
        String name2= UUID.randomUUID().toString();
        name2=name2+file.getOriginalFilename();
        String path= new File("images\\").getAbsolutePath();
        System.out.println(path);

        try {
            //file.transferTo( new File("C:\\Users\\RENE\\Documents\\General\\TallerSoft\\ProyTallerDeSoftware\\backend\\backend\\images\\" + fileName));
            file.transferTo( new File(path+ "\\" + name2)); //se puede habrir desde el navegador
        } catch (Exception e) {
            //return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        //return ResponseEntity.ok("File uploaded successfully.");
        HashMap<String, String> map = new HashMap<>();
        map.put("id", name2);
       return map;
    }

    @GetMapping(path="/image/{imagename}")
    public  ResponseEntity<Resource> findImage(@PathVariable("imagename") String id) throws IOException {
        String path= new File("images\\").getAbsolutePath();
        //File archivo = new File(path+"\\"+id);
        //System.out.println(archivo.getPath());
        //System.out.println(archivo.getName());
        Path root = Paths.get("./images");
        Path file = root.resolve(id);
        Resource resource = new UrlResource(file.toUri());

        return  ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
    }

    @GetMapping(path="/image2/{imagename}")
    public  Map<String,String> findImage2(@PathVariable("imagename") String id) throws IOException {
        String path= new File("images\\").getAbsolutePath();
        HashMap<String, String> map = new HashMap<>();
        map.put("path", path+"\\"+id);
        return map;
    }

    @GetMapping(path = "/{id}")
    public SolicitudEventoDTO getSoliById(@PathVariable Integer id) {
        return solicitudEventoBL.getSolicitudById(id);
    }
    
    @GetMapping(path="/image3/{imagename}")
    public  String findImage3(@PathVariable("imagename") String id) throws IOException {
        String path= new File("images\\").getAbsolutePath();
        System.out.println(path+"\\"+id);
        return path+"\\"+id;
    }

    @GetMapping(path="/publico/{solicitud_id}")
    public  Map<String,String> findpublico(@PathVariable("solicitud_id") Integer id){
        String data= solicitudEventoBL.getPublico(id);
        HashMap<String, String> map = new HashMap<>();
        map.put("public", data);
        return map;
    }

}
