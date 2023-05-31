package bo.edu.ucb.tallerdedesarollo.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
@AllArgsConstructor

public class UsuariosDTO {

    private Integer userId;
    private String sub;
    private Integer usertype;
    private String family_name;
    private String given_name;
    private String hd;
    private String email;
    private String picture;
    //private String nickname;
    //private String date;
    //private String career;

    
}


