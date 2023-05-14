package bo.edu.ucb.tallerdedesarollo.backend.DTO;

import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SolicitudEventoWPublicoDTO extends SolicitudEventoDTO {
    private String publico;

    public SolicitudEventoWPublicoDTO(long solicitudid, long usuarios_userid, String descripcion, long estado, long evento_publicacion_ep_id, Timestamp fecha_solicitud, Timestamp fecha_revisado, String publico) {
        super(solicitudid, usuarios_userid, descripcion, estado, evento_publicacion_ep_id, fecha_solicitud, fecha_revisado);
        this.publico = publico;
    }
}
