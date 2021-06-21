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
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import vet.web.dto.TutorDTO;
import vet.web.dto.TutorWrapper;

/**
 *
 * @author Jean Lima
 */
public class PaginaTutor extends WebPage implements Serializable {

    private static final long serialVersionUID = 1L;
    private Form form = new Form("form");
    final private WebMarkupContainer tabela = new WebMarkupContainer("tabela");
    private List<TutorDTO> tutores = new ArrayList();
    private CadastroPesquisaTutor_JerseyClient jc;

    public PaginaTutor() {
        super();
        jc = new CadastroPesquisaTutor_JerseyClient();
        adicionaForm();
        adicionaBotaoNovo();
        adicionaTabela();
    }

    private void adicionaForm() {
        add(form);
    }

    private void adicionaBotaoNovo() {

        AjaxSubmitLink botaoNovo = new org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink("botao-novo") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form form) {

                // Aqui normalmente os dados vem de um formulário. No exemplo a ação do botão insere um registro e pesquisa todos para listar na tabela. 
                TutorDTO tutorDTO = new TutorDTO();
                tutorDTO.setNome("Tutor 1");
                tutorDTO.setTelefone("98999999999");
                tutorDTO.setEmail("tutor1@domain.local");
                Response resposta = jc.adicionaNovo(tutorDTO);
                String id = (String) resposta.getEntity().toString();
                if (id != null) {
                    TutorWrapper wrapper = jc.pesquisaTodos(TutorWrapper.class);
                    tutores = wrapper.getTutores();
                    target.add(tabela);
                } else {
                    System.out.println("Não foi possível adicionar um novo tutor");
                }
            }
        };

        form.add(botaoNovo);
    }

    private void adicionaTabela() {

        IDataProvider dataProvider = new IDataProvider<TutorDTO>() {
            @Override
            public Iterator<? extends TutorDTO> iterator(long first, long count) {
                return tutores.subList((int) first, (int) (first + count)).iterator();
            }

            @Override
            public long size() {
                return tutores.size();
            }

            @Override
            public IModel model(final TutorDTO object) {
                return new LoadableDetachableModel() {
                    @Override
                    protected TutorDTO load() {
                        return (TutorDTO) object;
                    }
                };
            }

            @Override
            public void detach() {
            }
        };

        DataView dataView = new DataView<TutorDTO>("linhas", dataProvider) {
            @Override
            protected void populateItem(Item<TutorDTO> item) {
                TutorDTO tutorDTO = (TutorDTO) item.getModelObject();
                item.add(new Label("id", tutorDTO.getId()));
                item.add(new Label("nome", tutorDTO.getNome()));
                item.add(new Label("telefone", tutorDTO.getTelefone()));
                item.add(new Label("email", tutorDTO.getEmail()));
                item.add(new AjaxLink("editar", item.getModel()) {
                    @Override
                    public void onClick(AjaxRequestTarget target) {
                        TutorDTO tutorSelecionado = (TutorDTO) getModelObject();
                        //setResponsePage(new EdicaoTutor(veterinarioSelecionado.getId())); // A fazer...
                    }
                });
            }
        };

        tabela.setOutputMarkupId(true);
        tabela.add(dataView);
        add(tabela);
    }

    class CadastroPesquisaTutor_JerseyClient implements Serializable {

        private WebTarget webTarget;
        private Client client;
        private static final String BASE_URI = "http://localhost:8888/vet-war/rest"; // Endereço pode ser ser obtido via System.getProperty("ENDERECO_APLICACAO_ALVO");

        public CadastroPesquisaTutor_JerseyClient() {
            client = javax.ws.rs.client.ClientBuilder.newClient();
            webTarget = client.target(BASE_URI).path("tutor");
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
