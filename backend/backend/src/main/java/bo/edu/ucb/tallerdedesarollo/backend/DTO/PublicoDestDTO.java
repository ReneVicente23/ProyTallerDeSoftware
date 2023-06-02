package bo.edu.ucb.tallerdedesarollo.backend.DTO;

import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PublicoDestDTO {

    private List<Publico_edadDTO> publicoEdadDTOS;
    private List<Publico_tipoDTO> publicoTipoDTOS;
}
