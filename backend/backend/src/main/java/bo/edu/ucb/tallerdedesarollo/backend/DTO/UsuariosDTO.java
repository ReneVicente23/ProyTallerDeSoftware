package bo.edu.ucb.tallerdedesarollo.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuariosDTO {

    private Integer userId;
    private Integer googleId;
    private Integer usertype;
    private List<InteresesDTO> intereses = new ArrayList<>();
}



