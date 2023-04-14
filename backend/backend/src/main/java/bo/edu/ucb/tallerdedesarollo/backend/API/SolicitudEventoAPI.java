package bo.edu.ucb.tallerdedesarollo.backend.API;

import bo.edu.ucb.tallerdedesarollo.backend.BL.Evento_PublicacionBL;
import bo.edu.ucb.tallerdedesarollo.backend.BL.SolicitudEventoBL;
import bo.edu.ucb.tallerdedesarollo.backend.DTO.EventoRecepcionDTO;
import bo.edu.ucb.tallerdedesarollo.backend.DTO.SolicitudEventoDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.springframework.http.MediaType.*;

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


    @PostMapping(path = "/new", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE )
    public EventoRecepcionDTO create(@RequestBody EventoRecepcionDTO eventoRecepcionDTO) throws IOException {
        Integer id = evento_publicacionBL.newEvento(eventoRecepcionDTO);

        SolicitudEventoDTO se=new SolicitudEventoDTO(0,1,"test",1,id);
        solicitudEventoBL.nuevaSol(se);
        //System.out.println(eventoRecepcionDTO.toString());
        return eventoRecepcionDTO;
    }

    @PostMapping(path = "/image", consumes = MULTIPART_FORM_DATA_VALUE)
    public Map<String,String> imagen(@RequestParam("file")MultipartFile file) throws IOException {
        System.out.println(file.getName()+file.getContentType()+"  --  "+file.getOriginalFilename());
        String fileName = file.getOriginalFilename();
        String name2= UUID.randomUUID().toString();
        String path= new File("images\\").getAbsolutePath();
        System.out.println(path);

        try {
            //file.transferTo( new File("C:\\Users\\RENE\\Documents\\General\\TallerSoft\\ProyTallerDeSoftware\\backend\\backend\\images\\" + fileName));
            file.transferTo( new File(path+ "\\" + name2)); //no lo guarda con tipo pero se puede habrir desde el navegador
        } catch (Exception e) {
            //return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        //return ResponseEntity.ok("File uploaded successfully.");
        HashMap<String, String> map = new HashMap<>();
        map.put("id", name2);
       return map;
    }

}
