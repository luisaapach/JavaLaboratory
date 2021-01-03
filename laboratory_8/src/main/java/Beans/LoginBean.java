package Beans;

import Entities.UserEntity;
import Interfaces.UserInterface;
import Services.UserService;

import javax.faces.bean.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

@ManagedBean(name="loginBean")
@SessionScoped
public class LoginBean implements Serializable {
    private String username;
    private String password;
    @Inject
    private UserInterface userService;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserInterface getUserService() {
        return userService;
    }

    public void setUserService(UserInterface userService) {
        this.userService = userService;
    }

    public void validateUser() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        UserEntity user = userService.findUser(getUsername(),getPassword());
        if(user!=null){
            externalContext.getSessionMap().put("user", user);
            if(user.getRole().equals("guest"))
                externalContext.redirect(externalContext.getRequestContextPath() + "/upload.xhtml");
            else
                externalContext.redirect(externalContext.getRequestContextPath() + "/adminUploads.xhtml");
        }
        else{
            context.addMessage(null, new FacesMessage("Unknown login"));
        }
    }

    public void logout() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        externalContext.redirect(externalContext.getRequestContextPath() + "/login.xhtml");
    }
}
