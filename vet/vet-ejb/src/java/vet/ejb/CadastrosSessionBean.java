package vet.ejb;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import vet.entidades.Animal;
import vet.entidades.Especie;
import vet.entidades.Tutor;
import vet.entidades.Veterinario;
import vet.entidades.dto.AnimalDTO;
import vet.entidades.dto.TutorDTO;
import vet.entidades.dto.VeterinarioDTO;

/**
 *
 * @author Jean Lima
 */
@Stateless
public class CadastrosSessionBean implements CadastrosSessionBeanLocal {

    @PersistenceContext(unitName = "vet-ejbPU")
    private EntityManager em;

    @Override
    public Long novoVeterinario(VeterinarioDTO veterinarioDTO) {

        Veterinario veterinario = new Veterinario();
        veterinario.setNome(veterinarioDTO.getNome());
        veterinario.setTelefone(veterinarioDTO.getTelefone());
        veterinario.setEmail(veterinarioDTO.getEmail());
        em.persist(veterinario);
        return veterinario.getId();
    }

    @Override
    public String[] atualizaVeterinario(VeterinarioDTO veterinarioDTO) {

        String mensagemRetorno[] = {"SUCESSO_ATUALIZACAO", "Os dados foram atualizados."};

        Veterinario veterinario = em.find(Veterinario.class, veterinarioDTO.getId());

        if (veterinario != null) {
            veterinario.setNome(veterinario.getNome());
            veterinario.setTelefone(veterinarioDTO.getTelefone());
            veterinario.setEmail(veterinarioDTO.getEmail());
            em.merge(veterinario);
        } else {
            mensagemRetorno[0] = "ERRO_ATUALIZACAO";
            mensagemRetorno[1] = "Não foi possível atualizar os dados. Cadastro inexistente";
        }

        return mensagemRetorno;
    }

    @Override
    public Long novoAnimal(AnimalDTO animalDTO) {

        Animal animal = new Animal();
        animal.setNome(animalDTO.getNome());
        if (animalDTO.getEspecie() != null && animalDTO.getEspecie().equals("CANIS_FAMILIARIS")/*animalDTO.getEspecie().equals("C")*/) {
            animal.setEspecie(Especie.CANIS_FAMILIARIS);
        }
        if (animalDTO.getEspecie() != null && animalDTO.getEspecie().equals("FELINAE") /*animalDTO.getEspecie().equals("F")*/) {
            animal.setEspecie(Especie.FELINAE);
        }
        animal.setRaca(animalDTO.getRaca());
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate lDataNascimento = LocalDate.parse(animalDTO.getDataNascimento(), formato);
        Date dataNascimento = Date.from(lDataNascimento.atStartOfDay(ZoneId.systemDefault()).toInstant());
        animal.setDataNascimento(dataNascimento);
        if (animalDTO.getTutor() != null) {
            Tutor tutor = em.find(Tutor.class, animalDTO.getTutor());
            if (tutor != null) {
                animal.setTutor(tutor);
            }
        }
        em.persist(animal);
        return animal.getId();
    }

    @Override
    public String[] atualizaAnimal(AnimalDTO animalDTO) {

        String mensagemRetorno[] = {"SUCESSO_ATUALIZACAO", "Os dados foram atualizados."};

        Animal animal = em.find(Animal.class, animalDTO.getId());
        if (animal != null) {
            animal.setNome(animalDTO.getNome());
            if (animalDTO.getEspecie() != null && animalDTO.getEspecie().equals("C")) {
                animal.setEspecie(Especie.CANIS_FAMILIARIS);
            }
            if (animalDTO.getEspecie() != null && animalDTO.getEspecie().equals("F")) {
                animal.setEspecie(Especie.FELINAE);
            }
            animal.setRaca(animalDTO.getRaca());
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate lDataNascimento = LocalDate.parse(animalDTO.getDataNascimento(), formato);
            Date dataNascimento = Date.from(lDataNascimento.atStartOfDay(ZoneId.systemDefault()).toInstant());
            animal.setDataNascimento(dataNascimento);
            if (animalDTO.getTutor() != null) {
                Tutor tutor = em.find(Tutor.class, animalDTO.getTutor());
                if (tutor != null) {
                    animal.setTutor(tutor);
                }
            }
            em.merge(animal);
        } else {
            mensagemRetorno[0] = "ERRO_ATUALIZACAO";
            mensagemRetorno[1] = "Não foi possível atualizar os dados. Cadastro inexistente";
        }

        return mensagemRetorno;
    }

    @Override
    public Long novoTutor(TutorDTO tutorDTO) {

        Tutor tutor = new Tutor();
        tutor.setNome(tutorDTO.getNome());
        tutor.setTelefone(tutorDTO.getTelefone());
        tutor.setEmail(tutorDTO.getEmail());
        em.persist(tutor);
        return tutor.getId();
    }

    @Override
    public String[] atualizaTutor(TutorDTO tutorDTO) {

        String mensagemRetorno[] = {"SUCESSO_ATUALIZACAO", "Os dados foram atualizados."};

        Tutor tutor = em.find(Tutor.class, tutorDTO.getId());

        if (tutor != null) {
            tutor.setNome(tutorDTO.getNome());
            tutor.setTelefone(tutorDTO.getTelefone());
            tutor.setEmail(tutorDTO.getEmail());
            em.merge(tutor);
        } else {
            mensagemRetorno[0] = "ERRO_ATUALIZACAO";
            mensagemRetorno[1] = "Não foi possível atualizar os dados. Cadastro inexistente";
        }

        return mensagemRetorno;
    }

}
