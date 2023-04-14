package bo.edu.ucb.tallerdedesarollo.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class InteresesUsuarioDTO {

    private Integer idinterUs;
    private Integer usuarios_userId;
    private Integer sub_intereses_id_subinteres;
    
}
