package bo.edu.ucb.tallerdedesarollo.backend.DTO;

import java.util.List;

import lombok.Data;

@Data
public class UsuarioySubIntereses {
    
    private Integer userId;
    private Integer googleId;
    private Integer usertype;
    private List<SubInteresesDTO> subIntereses;
}
