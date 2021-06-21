package vet.web.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Jean Lima
 */
@javax.ws.rs.ApplicationPath("rest")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(vet.web.service.CadastroPesquisaAnimal.class);
        resources.add(vet.web.service.CadastroPesquisaTutor.class);
        resources.add(vet.web.service.CadastroPesquisaVeterinario.class);
    }

}
