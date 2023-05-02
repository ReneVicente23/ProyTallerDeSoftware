package bo.edu.ucb.tallerdedesarollo.backend.DTO;

import lombok.ToString;

@ToString
public class CategoriaDTO {
    private long interesID;
    private String interes_name;

    public CategoriaDTO(long interesID, String interes_name) {
        this.interesID = interesID;
        this.interes_name = interes_name;
    }

    public long getInteresID() {
        return interesID;
    }

    public void setInteresID(long interesID) {
        this.interesID = interesID;
    }

    public String getInteres_name() {
        return interes_name;
    }

    public void setInteres_name(String interes_name) {
        this.interes_name = interes_name;
    }
}
