package vet.entidades.dto;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jean Lima
 */
@XmlRootElement(name = "animal")
public class AnimalDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlElement(name = "id")
    private Long id;

    @XmlElement(name = "nome")
    private String nome;

    @XmlElement(name = "especie")
    private String especie;

    @XmlElement(name = "raca")
    private String raca;

    @XmlElement(name = "data_nascimento")
    private String dataNascimento;

    @XmlElement(name = "tutor")
    private Long tutor;

    @XmlElement(name = "nome_tutor")
    private String nomeTutor;

    public AnimalDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Long getTutor() {
        return tutor;
    }

    public void setTutor(Long tutor) {
        this.tutor = tutor;
    }

    public String getNomeTutor() {
        return nomeTutor;
    }

    public void setNomeTutor(String nomeTutor) {
        this.nomeTutor = nomeTutor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof AnimalDTO)) {
            return false;
        }
        AnimalDTO other = (AnimalDTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nome;
    }
}
