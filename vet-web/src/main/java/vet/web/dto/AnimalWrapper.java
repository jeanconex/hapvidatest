
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
@XmlRootElement(name = "animais")
@XmlAccessorType(XmlAccessType.FIELD)
public class AnimalWrapper {
    
    private static final long serialVersionUID = 1L;
    
    @XmlElement(name = "animais")
    private List<AnimalDTO> animais;

    public AnimalWrapper() {

    }

    public List<AnimalDTO> getAnimais() {
        return animais;
    }

    public void setAnimais(List<AnimalDTO> animais) {
        this.animais = animais;
    }


   

   
    
}
