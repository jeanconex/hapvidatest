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
import vet.entidades.dto.VeterinarioDTO;
import vet.entidades.dto.VeterinarioWrapper;

/**
 *
 * @author Jean Lima
 */
@Path("veterinario")
public class CadastroPesquisaVeterinario {

    private CadastrosSessionBeanLocal cadastrosSessionBean = lookupCadastrosSessionBeanLocal();
    private PesquisasSessionBeanLocal pesquisasSessionBean = lookupPesquisasSessionBeanLocal();

    public CadastroPesquisaVeterinario() {
    }

    @GET
    @Path("schema-json")
    @Produces(MediaType.APPLICATION_JSON)
    public VeterinarioDTO schema() {

        VeterinarioDTO veterinarioDTO = new VeterinarioDTO();
        veterinarioDTO.setNome("Veterinário Exemplo");
        veterinarioDTO.setTelefone("98999999999");
        veterinarioDTO.setEmail("email@local.domain");
        return veterinarioDTO;
    }

    @POST
    @Path("novo")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response adicionaNovo(VeterinarioDTO veterinarioDTO) {

        Long id = cadastrosSessionBean.novoVeterinario(veterinarioDTO);
        if (id != null) {
            return Response.status(Response.Status.CREATED).entity(id).build();
        } else {
            return Response.status(Response.Status.NOT_MODIFIED).entity("Não foi possível realizar o cadastro").build();
        }
    }

    @POST
    @Path("atualiza")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizaCadastro(VeterinarioDTO veterinarioDTO) {

        String[] resposta = cadastrosSessionBean.atualizaVeterinario(veterinarioDTO);
        if (resposta[0].equals("SUCESSO_ATUALIZACAO")) {
            return Response.status(Response.Status.CREATED).entity(resposta[1]).build();
        } else {
            return Response.status(Response.Status.NOT_MODIFIED).entity(resposta[1]).build();
        }

    }

    @GET
    @Path("todos")
    @Produces(MediaType.APPLICATION_JSON)
    public VeterinarioWrapper pesquisaTodos() {

        VeterinarioWrapper wrapper = new VeterinarioWrapper();
        wrapper.setVeterinarios(pesquisasSessionBean.pesquisaVeterinarios());
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
