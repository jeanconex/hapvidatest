package vet.ejb;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import vet.entidades.Animal;
import vet.entidades.Especie;
import vet.entidades.Tutor;
import vet.entidades.Veterinario;
import vet.entidades.dto.AnimalDTO;
import vet.entidades.dto.TutorDTO;
import vet.entidades.dto.VeterinarioDTO;
import vet.util.ConversorFormatadorDatas;

/**
 *
 * @author Jean Lima
 */
@Stateless
public class PesquisasSessionBean implements PesquisasSessionBeanLocal {

    @PersistenceContext(unitName = "vet-ejbPU")
    private EntityManager em;

    @Override
    public List<VeterinarioDTO> pesquisaVeterinarios() {

        List<VeterinarioDTO> veterinariosDTO = new ArrayList();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Veterinario> cq = cb.createQuery(Veterinario.class);
        Root<Veterinario> rootEntry = cq.from(Veterinario.class);
        CriteriaQuery<Veterinario> all = cq.select(rootEntry).orderBy(cb.desc(rootEntry.<Long>get("id")));
        TypedQuery<Veterinario> allQuery = em.createQuery(all);
        List<Veterinario> veterinarios = allQuery.getResultList();
        Iterator<Veterinario> it = veterinarios.iterator();
        while (it.hasNext()) {
            Veterinario vi = it.next();
            VeterinarioDTO veterinarioDTO = new VeterinarioDTO();
            veterinarioDTO.setId(vi.getId());
            veterinarioDTO.setNome(vi.getNome());
            veterinarioDTO.setTelefone(vi.getTelefone());
            veterinarioDTO.setEmail(vi.getEmail());
            veterinariosDTO.add(veterinarioDTO);
        }

        return veterinariosDTO;
    }

    @Override
    public List<TutorDTO> pesquisaTutores() {

        List<TutorDTO> tutoresDTO = new ArrayList();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tutor> cq = cb.createQuery(Tutor.class);
        Root<Tutor> rootEntry = cq.from(Tutor.class);
        CriteriaQuery<Tutor> all = cq.select(rootEntry).orderBy(cb.desc(rootEntry.<Long>get("id")));
        TypedQuery<Tutor> allQuery = em.createQuery(all);
        List<Tutor> tutores = allQuery.getResultList();
        Iterator<Tutor> it = tutores.iterator();
        while (it.hasNext()) {
            Tutor ti = it.next();
            TutorDTO tutorDTO = new TutorDTO();
            tutorDTO.setId(ti.getId());
            tutorDTO.setNome(ti.getNome());
            tutorDTO.setTelefone(ti.getTelefone());
            tutorDTO.setEmail(ti.getEmail());
            tutoresDTO.add(tutorDTO);
        }

        return tutoresDTO;

    }

    @Override
    public List<AnimalDTO> pesquisaAnimais() {

        List<AnimalDTO> animaisDTO = new ArrayList();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Animal> cq = cb.createQuery(Animal.class);
        Root<Animal> rootEntry = cq.from(Animal.class);
        CriteriaQuery<Animal> all = cq.select(rootEntry).orderBy(cb.desc(rootEntry.<Long>get("id")));
        TypedQuery<Animal> allQuery = em.createQuery(all);
        List<Animal> animais = allQuery.getResultList();
        Iterator<Animal> it = animais.iterator();
        while (it.hasNext()) {
            Animal ai = it.next();
            AnimalDTO animalDTO = new AnimalDTO();
            animalDTO.setId(ai.getId());
            animalDTO.setNome(ai.getNome());
            if (ai.getEspecie().equals(Especie.CANIS_FAMILIARIS)) {
                animalDTO.setEspecie("C");
            }
            if (ai.getEspecie().equals(Especie.FELINAE)) {
                animalDTO.setEspecie("F");
            }
            animalDTO.setRaca(ai.getRaca());
            String dataNascimentoStr = ConversorFormatadorDatas.formataLocalDateDiaMesAno(ConversorFormatadorDatas.convertToLocalDateViaSqlDate(ai.getDataNascimento()));
            animalDTO.setDataNascimento(dataNascimentoStr);
            animalDTO.setTutor(ai.getTutor().getId());
            animalDTO.setNomeTutor(ai.getTutor().getNome());
            animaisDTO.add(animalDTO);
        }

        return animaisDTO;

    }

}
