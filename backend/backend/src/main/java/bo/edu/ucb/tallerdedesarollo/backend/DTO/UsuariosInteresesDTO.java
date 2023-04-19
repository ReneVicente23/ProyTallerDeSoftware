package bo.edu.ucb.tallerdedesarollo.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
public class UsuariosInteresesDTO {
    private Integer id;
    private UsuariosDTO usuario;
    private InteresesDTO interes;
}
