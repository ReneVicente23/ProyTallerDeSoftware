package bo.edu.ucb.tallerdedesarollo.backend.DTO;

import lombok.Data;

@Data
public class UsuariosSignInDTO {
    private String sub;
    private Integer usertype;
    private String family_name;
    private String given_name;
    private String hd;
    private String email;
    private String picture;
}
