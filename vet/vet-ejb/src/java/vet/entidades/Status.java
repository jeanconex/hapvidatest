package vet.entidades;

/**
 *
 * @author Jean Lima
 */
public enum Status {

    AGENDADA("A"), REALIZADA("R"), CANCELADA("C");

    private String status;

    private Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
