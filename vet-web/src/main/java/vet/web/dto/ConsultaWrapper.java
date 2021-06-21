package vet.web.dto;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jean Lima
 */
@XmlRootElement(name = "consultas")
@XmlAccessorType(XmlAccessType.FIELD)
public class ConsultaWrapper implements Serializable {

    private static final long serialVersionUID = 1L;
    @XmlElement(name = "consultas")
    private List<ConsultaDTO> consutas;

    public ConsultaWrapper() {

    }

    public List<ConsultaDTO> getConsutas() {
        return consutas;
    }

    public void setConsutas(List<ConsultaDTO> consutas) {
        this.consutas = consutas;
    }
}
