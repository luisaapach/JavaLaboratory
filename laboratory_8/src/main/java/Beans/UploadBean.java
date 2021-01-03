package Beans;

import AnnotationsCustom.Unique;
import Entities.Upload;
import Entities.UserEntity;
import Interfaces.UploadInterface;
import Services.UploadService;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Instance;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@ManagedBean(name="uploadBean")
@SessionScoped
public class UploadBean implements Serializable {
    @Inject
    private UploadInterface uploadService;

    UploadedFile file;

    Upload entity = new Upload();
    @Inject
    @Unique
    Instance<String> submissionIDImplementation;

    public UploadInterface getUploadService() {
        return uploadService;
    }

    public void setUploadService(UploadInterface uploadService) {
        this.uploadService = uploadService;
    }

    public Upload getEntity() {
        return entity;
    }

    public void setEntity(Upload entity) {
        this.entity = entity;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    public void upload(){
        getEntity().setSubmissionID(submissionIDImplementation.get());
        getEntity().setFileName(file.getFileName());
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        UserEntity currentUser = (UserEntity) externalContext.getSessionMap().get("user");
        getEntity().setUser(currentUser);
        uploadService.addUpload(getEntity());
    }
}
