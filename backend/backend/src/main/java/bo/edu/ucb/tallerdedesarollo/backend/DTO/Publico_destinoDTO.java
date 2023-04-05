package bo.edu.ucb.tallerdedesarollo.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Publico_destinoDTO {
    private long id_pd;
    private Integer edad_inicio;
    private Integer edad_limite;
    private String tipo;
}
