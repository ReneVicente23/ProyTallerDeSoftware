package bo.edu.ucb.tallerdedesarollo.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsignarInteresUsuarioDTO {
    private String usuarioId;
    private List<Integer> subInteresId;

    public List<Integer> getSubInteresIds() {
        return subInteresId;
    }

}
