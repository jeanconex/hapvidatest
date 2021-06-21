package vet.ejb;

import java.util.List;
import javax.ejb.Local;
import vet.entidades.dto.AnimalDTO;
import vet.entidades.dto.TutorDTO;
import vet.entidades.dto.VeterinarioDTO;

/**
 *
 * @author Jean Lima
 */
@Local
public interface PesquisasSessionBeanLocal {

    public List<VeterinarioDTO> pesquisaVeterinarios();

    public List<TutorDTO> pesquisaTutores();

    public List<AnimalDTO> pesquisaAnimais();

}
