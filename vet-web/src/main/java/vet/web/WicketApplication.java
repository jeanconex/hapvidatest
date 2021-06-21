package vet.web;

import org.apache.wicket.core.util.file.WebApplicationPath;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

/**
 *
 * @author Jean Lima
 */
public class WicketApplication extends WebApplication {

    @Override
    public Class<? extends WebPage> getHomePage() {
        return PaginaVeterinario.class;
    }

    @Override
    public void init() {
        super.init();
        
        getResourceSettings().getResourceFinders().add(
				new WebApplicationPath(getServletContext(), "assets"));
        
        
        getMarkupSettings().setDefaultMarkupEncoding("utf-8");
        getRequestCycleSettings().setResponseRequestEncoding("utf-8");
        
        mountPage("/v", PaginaVeterinario.class);
        mountPage("/t", PaginaTutor.class);

    }
}
