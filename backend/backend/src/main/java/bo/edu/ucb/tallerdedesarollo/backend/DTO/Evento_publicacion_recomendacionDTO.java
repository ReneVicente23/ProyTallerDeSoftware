package bo.edu.ucb.tallerdedesarollo.backend.DTO;

import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Evento_publicacion_recomendacionDTO {
    private Integer ep_id;
    private String titulo;
    private String descripcion;
    private String id_imagen;
    private String lugar;
    private String link;
    private Integer evento_publicacion_tipo_id_eptipo;
    private Timestamp date;
    private List<SubInteresesDTO> subInteresesDTOS;
}
