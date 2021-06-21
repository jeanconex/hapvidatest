package vet.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import vet.web.dto.VeterinarioDTO;
import vet.web.dto.VeterinarioWrapper;

/**
 *
 * @author Jean Lima
 */
public class PaginaVeterinario extends WebPage implements Serializable {

    private static final long serialVersionUID = 1L;
    private WebMarkupContainer painelForm = new WebMarkupContainer("painel-form");
    private Form form = new Form("form");
    private WebMarkupContainer tabela = new WebMarkupContainer("tabela");
    private List<VeterinarioDTO> veterinarios = new ArrayList();
    private CadastroPesquisaVeterinario_JerseyClient jc;
    private VeterinarioDTO veterinarioDTO = new VeterinarioDTO();
    private FeedbackPanel feedBackPanel = new FeedbackPanel("feedback");
    private CompoundPropertyModel compoundPropertyModel;

    public PaginaVeterinario() {
        super();
        jc = new CadastroPesquisaVeterinario_JerseyClient();
        adicionaRegiaoFeedback();
        adicionaPainelForm();
        adicionaTabela();
    }

    private void adicionaRegiaoFeedback() {
        feedBackPanel.setOutputMarkupId(true);
        feedBackPanel.setEscapeModelStrings(false);
        add(feedBackPanel);
    }

    private void adicionaPainelForm() {

        painelForm.setOutputMarkupId(true);

        compoundPropertyModel = new CompoundPropertyModel(veterinarioDTO);
        Form form = new Form("form");
        form.setDefaultModel(compoundPropertyModel);
        form.setOutputMarkupId(true);

        TextField campoNome = new TextField("nome-veterinario", compoundPropertyModel.bind("nome"));
        campoNome.setMarkupId("nome-veterinario");
        campoNome.setOutputMarkupId(true);

        TextField campoTelefone = new TextField("telefone-veterinario", compoundPropertyModel.bind("telefone"));
        campoTelefone.setMarkupId("telefone-veterinario");
        campoTelefone.setOutputMarkupId(true);

        TextField campoEmail = new TextField("email-veterinario", compoundPropertyModel.bind("email"));
        campoEmail.setMarkupId("email-veterinario");
        campoEmail.setOutputMarkupId(true);

        form.add(campoNome);
        form.add(campoTelefone);
        form.add(campoEmail);

        AjaxSubmitLink botaoNovo = new org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink("botao-novo") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form form) {

                Response resposta = jc.adicionaNovo(veterinarioDTO);
                if (resposta.getStatusInfo() == Response.Status.CREATED) {                    
                    String id = (String) resposta.getEntity().toString(); // Obtem o id
                    info("Cadastro realizado com sucesso.");                    
                    VeterinarioWrapper wrapper = jc.pesquisaTodos(VeterinarioWrapper.class);
                    veterinarios = wrapper.getVeterinarios();                    
                    target.add(feedBackPanel);
                    target.add(tabela);
                } else {
                    error("Não foi possível adicionar um novo veterinario.");
                    target.add(feedBackPanel);
                }
            }
        };
        botaoNovo.setMarkupId("botao-novo");
        botaoNovo.setOutputMarkupId(true);
        form.add(botaoNovo);
        painelForm.add(form);
        add(painelForm);
    }

    private void adicionaTabela() {

        IDataProvider dataProvider = new IDataProvider<VeterinarioDTO>() {
            @Override
            public Iterator<? extends VeterinarioDTO> iterator(long first, long count) {
                return veterinarios.subList((int) first, (int) (first + count)).iterator();
            }

            @Override
            public long size() {
                return veterinarios.size();
            }

            @Override
            public IModel model(final VeterinarioDTO object) {
                return new LoadableDetachableModel() {
                    @Override
                    protected VeterinarioDTO load() {
                        return (VeterinarioDTO) object;
                    }
                };
            }

            @Override
            public void detach() {
            }
        };

        DataView dataView = new DataView<VeterinarioDTO>("linhas", dataProvider) {
            @Override
            protected void populateItem(Item<VeterinarioDTO> item) {
                VeterinarioDTO veterinarioDTO = (VeterinarioDTO) item.getModelObject();
                //item.add(new Label("id", veterinarioDTO.getId()));
                item.add(new Label("nome", veterinarioDTO.getNome()));
                item.add(new Label("telefone", veterinarioDTO.getTelefone()));
                item.add(new Label("email", veterinarioDTO.getEmail()));
                item.add(new AjaxLink("editar", item.getModel()) {
                    @Override
                    public void onClick(AjaxRequestTarget target) {
                        VeterinarioDTO veterinarioSelecionado = (VeterinarioDTO) getModelObject();
                        //setResponsePage(new EdicaoVeterinario(veterinarioSelecionado.getId())); // A fazer...
                    }
                });
            }
        };

        tabela.setOutputMarkupId(true);
        tabela.add(dataView);
        add(tabela);
    }

    class CadastroPesquisaVeterinario_JerseyClient implements Serializable {

        private WebTarget webTarget;
        private Client client;
        private static final String BASE_URI = "http://localhost:8888/vet-war/rest"; // Endereço pode ser ser obtido via System.getProperty("ENDERECO_APLICACAO_ALVO");

        public CadastroPesquisaVeterinario_JerseyClient() {
            client = javax.ws.rs.client.ClientBuilder.newClient();
            webTarget = client.target(BASE_URI).path("veterinario");
        }

        public <T> T schema(Class<T> responseType) throws ClientErrorException {
            WebTarget resource = webTarget;
            resource = resource.path("schema-json");
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
        }

        public <T> T pesquisaTodos(Class<T> responseType) throws ClientErrorException {
            WebTarget resource = webTarget;
            resource = resource.path("todos");
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
        }

        public Response atualizaCadastro(Object requestEntity) throws ClientErrorException {
            return webTarget.path("atualiza").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), Response.class);
        }

        public Response adicionaNovo(Object requestEntity) throws ClientErrorException {
            return webTarget.path("novo").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), Response.class);
        }

        public void close() {
            client.close();
        }
    }

}
