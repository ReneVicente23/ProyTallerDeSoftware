package bo.edu.ucb.tallerdedesarollo.backend.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InteresesSub {
    private Integer interesid;
    private String nombre_interes;
    private String imagen;
    private List<SubInteresesDTO> subIntereses;
}
