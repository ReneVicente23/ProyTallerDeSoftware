package bo.edu.ucb.tallerdedesarollo.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InteresesDTO {
    private Integer interesid;
    private String nombre_interes;
    private String imagen;
}
