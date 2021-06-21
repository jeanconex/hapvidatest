package vet.web.service;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import vet.ejb.CadastrosSessionBeanLocal;
import vet.ejb.PesquisasSessionBeanLocal;
import vet.entidades.dto.AnimalDTO;
import vet.entidades.dto.AnimalWrapper;

/**
 *
 * @author Jean Lima
 */
@Path("animal")
public class CadastroPesquisaAnimal {

    private CadastrosSessionBeanLocal cadastrosSessionBean = lookupCadastrosSessionBeanLocal();
    private PesquisasSessionBeanLocal pesquisasSessionBean = lookupPesquisasSessionBeanLocal();

    public CadastroPesquisaAnimal() {
    }

    @GET
    @Path("schema-json")
    @Produces(MediaType.APPLICATION_JSON)
    public AnimalDTO schema() {

        AnimalDTO animalDTO = new AnimalDTO();
        animalDTO.setNome("Animal 1");
        animalDTO.setEspecie("CANIS_FAMILIARIS"); // CANIS_FAMILIARIS, FELINAE
        animalDTO.setRaca("Raça 1");
        animalDTO.setDataNascimento("01/01/2015");
        animalDTO.setTutor(1L);
        return animalDTO;

    }

    @POST
    @Path("novo")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response adicionaNovo(AnimalDTO animalDTO) {

        Long id = cadastrosSessionBean.novoAnimal(animalDTO);
        if (id != null) {
            return Response.status(Response.Status.CREATED).entity(id).build();
        } else {
            return Response.status(Response.Status.NOT_MODIFIED).entity("Não foi possível realizar o cadastro").build();
        }
    }

    @POST
    @Path("atualiza")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizaCadastro(AnimalDTO animalDTO) {

        String[] resposta = cadastrosSessionBean.atualizaAnimal(animalDTO);
        if (resposta[0].equals("SUCESSO_ATUALIZACAO")) {
            return Response.status(Response.Status.CREATED).entity(resposta[1]).build();
        } else {
            return Response.status(Response.Status.NOT_MODIFIED).entity(resposta[1]).build();
        }

    }

    @GET
    @Path("todos")
    @Produces(MediaType.APPLICATION_JSON)
    public AnimalWrapper pesquisaTodos() {

        AnimalWrapper wrapper = new AnimalWrapper();
        wrapper.setAnimais(pesquisasSessionBean.pesquisaAnimais());
        return wrapper;
    }

    private CadastrosSessionBeanLocal lookupCadastrosSessionBeanLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (CadastrosSessionBeanLocal) c.lookup("java:global/vet/vet-ejb/CadastrosSessionBean!vet.ejb.CadastrosSessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private PesquisasSessionBeanLocal lookupPesquisasSessionBeanLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (PesquisasSessionBeanLocal) c.lookup("java:global/vet/vet-ejb/PesquisasSessionBean!vet.ejb.PesquisasSessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
