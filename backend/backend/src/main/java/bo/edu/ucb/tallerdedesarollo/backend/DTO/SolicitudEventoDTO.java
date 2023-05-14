package bo.edu.ucb.tallerdedesarollo.backend.DTO;

import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SolicitudEventoDTO {
    private long solicitudid;
    private long usuarios_userid;
    private String descripcion;
    private long estado;
    private long evento_publicacion_ep_id;
    private Timestamp fecha_solicitud;
    private Timestamp fecha_revisado;

}
