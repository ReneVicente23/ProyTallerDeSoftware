package bo.edu.ucb.tallerdedesarollo.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Evento_publicacionDTO {
    private long ep_id;
    private String titulo;
    private String descripcion;
    private String id_imagen;
    private String lugar;
    private String link;
    private Integer evento_publicacion_tipo_id_eptipo;
}
