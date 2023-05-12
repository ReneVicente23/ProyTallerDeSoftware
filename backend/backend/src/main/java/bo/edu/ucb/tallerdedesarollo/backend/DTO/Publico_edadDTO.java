package bo.edu.ucb.tallerdedesarollo.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Publico_edadDTO {
    private long id_rangos_edad;
    private Integer edad_inicio;
    private Integer edad_fin;
}
