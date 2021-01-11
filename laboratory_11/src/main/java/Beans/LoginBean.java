package Beans;

import Authentification.AuthentificationUtils;
import Entities.GroupEntity;
import Entities.UserEntity;
import Interfaces.GroupInterface;
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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Map;

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

    public void validateUser() throws IOException, NoSuchAlgorithmException {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();

        HttpServletRequest request = (HttpServletRequest)
                externalContext.getRequest();

        try {
            System.out.println( AuthentificationUtils.encodeSHA256(getPassword()));
            request.login(getUsername(), AuthentificationUtils.encodeSHA256(getPassword()));
        } catch (ServletException | NoSuchAlgorithmException e) {
            System.out.println(e.toString());
            e.printStackTrace();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed!", null));
            return;
        }
        UserEntity user = userService.findUser(getUsername(),AuthentificationUtils.encodeSHA256(getPassword()));
        externalContext.getSessionMap().put("user", user);
        if(user!=null){
            if (request.isUserInRole("guest")) {
                externalContext.redirect(externalContext.getRequestContextPath() + "/guest/upload.xhtml");
            }
            else{
                externalContext.redirect(externalContext.getRequestContextPath() + "/admin/adminUploads.xhtml");
            }
//            for (GroupEntity groupEntity: user.getGroups()){
//                if(groupEntity.getName().equals("guest")){
//                    externalContext.redirect(externalContext.getRequestContextPath() + "/guest/upload.xhtml");
//                }
//                if(groupEntity.getName().equals("admin")){
//                    externalContext.redirect(externalContext.getRequestContextPath() + "/admin/adminUploads.xhtml");
//                }
//            }
        }
        else{
            context.addMessage(null, new FacesMessage("Unknown login"));
        }
    }

    public void logout() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        try {
            request.logout();
            // clear the session
            ((HttpSession) externalContext.getSession(false)).invalidate();
        } catch (ServletException e) {
            System.out.println("Cannot invalidate session");
        }
        externalContext.redirect(externalContext.getRequestContextPath() + "/login.xhtml");
    }
}
