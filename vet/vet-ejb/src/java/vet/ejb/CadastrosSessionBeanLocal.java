package vet.ejb;

import javax.ejb.Local;
import vet.entidades.dto.AnimalDTO;
import vet.entidades.dto.TutorDTO;
import vet.entidades.dto.VeterinarioDTO;

/**
 *
 * @author Jean Lima
 */
@Local
public interface CadastrosSessionBeanLocal {

    public Long novoVeterinario(VeterinarioDTO veterinarioDTO);

    public String[] atualizaVeterinario(VeterinarioDTO veterinarioDTO);

    public Long novoAnimal(AnimalDTO animalDTO);

    public String[] atualizaAnimal(AnimalDTO animalDTO);

    public Long novoTutor(TutorDTO tutorDTO);

    public String[] atualizaTutor(TutorDTO tutorDTO);

}
