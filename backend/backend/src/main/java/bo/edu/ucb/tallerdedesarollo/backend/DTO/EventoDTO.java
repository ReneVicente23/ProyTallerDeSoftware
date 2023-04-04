package bo.edu.ucb.tallerdedesarollo.backend.DTO;

import java.sql.Date;
import java.util.List;


public class EventoDTO {
    private long idEvento;
    private String titulo;
    private String descripcion;
    private String imagen;
    private String publicoDestino; // A modificar
    private List<CategoriaDTO> intereses;
    private Integer minEdadpublico;
    private Integer maxEdadpublico;
    private Date fecha;
    private String tipo;
    private String lugar;
    private String link;

}
