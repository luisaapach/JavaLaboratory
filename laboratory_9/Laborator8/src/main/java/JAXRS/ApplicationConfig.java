package JAXRS;

import javax.ws.rs.core.Application;
import java.util.Set;

@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {
    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(AddDocumentService.class);
        resources.add(UpdateDocumentService.class);
        resources.add(DeleteDocumentService.class);
        resources.add(ViewDocumentService.class);
    }
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }
}