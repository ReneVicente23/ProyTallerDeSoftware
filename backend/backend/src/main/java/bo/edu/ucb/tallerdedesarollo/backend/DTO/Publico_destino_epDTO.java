package bo.edu.ucb.tallerdedesarollo.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Publico_destino_epDTO {
    private long id_ped;
    private long evento_publicacion_ep_id;
    private long publico_destino_id_pd;
}
