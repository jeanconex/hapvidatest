
package vet.web.dto;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jean Lima
 */
@XmlRootElement(name = "tutores")
@XmlAccessorType(XmlAccessType.FIELD)
public class TutorWrapper {
    
    private static final long serialVersionUID = 1L;
    @XmlElement(name = "tutores")
    private List<TutorDTO> tutores;

    public TutorWrapper() {

    }

    public List<TutorDTO> getTutores() {
        return tutores;
    }

    public void setTutores(List<TutorDTO> tutores) {
        this.tutores = tutores;
    }

   

   
    
}
