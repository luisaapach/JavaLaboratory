package Beans;

import Entities.Upload;
import Interceptor.Logged;
import Interfaces.UploadInterface;
import Services.UploadService;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.faces.annotation.FacesConfig;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import static javax.faces.annotation.FacesConfig.Version.JSF_2_3;

@FacesConfig(
        // Activates CDI build-in beans
        version = JSF_2_3
)
@Named("cacheBean")
@ApplicationScoped
public class UploadsCacheBean implements Serializable {
    @Inject
    protected UploadInterface uploadService;
    protected List<UploadBean> uploads;

    public List<UploadBean> getUploads(){
        if (uploads == null){
            uploads = uploadService.getUploads();
        }
        return uploads;
    }
    @Logged
    public void onUploadsUpdate(@Observes Upload upload){
        uploads = null;
    }
}
