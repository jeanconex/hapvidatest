package vet.web.dto;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jean Lima
 */
@XmlRootElement(name = "consulta")
public class ConsultaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlElement(name = "id")
    private Long id;

    @XmlElement(name = "data_consulta")
    private String dataConsulta;

    @XmlElement(name = "status")
    private String status;

    @XmlElement(name = "animal")
    private Long animal;

    @XmlElement(name = "veterinario")
    private Long veterinario;

    public ConsultaDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(String dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getAnimal() {
        return animal;
    }

    public void setAnimal(Long animal) {
        this.animal = animal;
    }

    public Long getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Long veterinario) {
        this.veterinario = veterinario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof ConsultaDTO)) {
            return false;
        }
        ConsultaDTO other = (ConsultaDTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "";
    }

}
