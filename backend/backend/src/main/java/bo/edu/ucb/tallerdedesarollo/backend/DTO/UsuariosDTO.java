package bo.edu.ucb.tallerdedesarollo.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
@AllArgsConstructor

public class UsuariosDTO {

    private Integer userId;
    private Integer googleId;
    private Integer usertype;

    
}


