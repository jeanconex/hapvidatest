
package vet.entidades.dto;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jean Lima
 */
@XmlRootElement(name = "veterinarios")
@XmlAccessorType(XmlAccessType.FIELD)
public class VeterinarioWrapper {
    
    private static final long serialVersionUID = 1L;
    @XmlElement(name = "veterinarios")
    private List<VeterinarioDTO> veterinarios;

    public VeterinarioWrapper() {

    }

    public List<VeterinarioDTO> getVeterinarios() {
        return veterinarios;
    }

    public void setVeterinarios(List<VeterinarioDTO> veterinarios) {
        this.veterinarios = veterinarios;
    }

   
    
}
