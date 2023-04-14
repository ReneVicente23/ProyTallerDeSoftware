package bo.edu.ucb.tallerdedesarollo.backend.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EventoRecepcionDTO {
    private long ep_id;
    private String titulo;
    private String descripcion;
    private String id_imagen;
    private String lugar;
    private String link;
    //private CategoriaDTO[] categoriaDTOS;
    private InteresesDTO[] interesesDTOS;
    private String publico;
}
