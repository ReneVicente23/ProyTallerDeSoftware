package bo.edu.ucb.tallerdedesarollo.backend.DTO;

public class SolicitudEventoDTO {
    private long solicitud_id;
    private long user_id;
    private long event_id;
    private String sol_info;

    public SolicitudEventoDTO(long solicitud_id, long user_id, long event_id, String sol_info) {
        this.solicitud_id = solicitud_id;
        this.user_id = user_id;
        this.event_id = event_id;
        this.sol_info = sol_info;
    }

    public long getSolicitud_id() {
        return solicitud_id;
    }

    public void setSolicitud_id(long solicitud_id) {
        this.solicitud_id = solicitud_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getEvent_id() {
        return event_id;
    }

    public void setEvent_id(long event_id) {
        this.event_id = event_id;
    }

    public String getSol_info() {
        return sol_info;
    }

    public void setSol_info(String sol_info) {
        this.sol_info = sol_info;
    }
}
