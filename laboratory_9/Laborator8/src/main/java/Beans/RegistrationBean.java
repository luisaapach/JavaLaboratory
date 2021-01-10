package Beans;

import Entities.UserEntity;
import Services.UserService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@ManagedBean(name="registrationBean")
@SessionScoped
public class RegistrationBean implements Serializable {
    public void addUser(UserBean userBean){
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        UserEntity user = userBean.getUserService().findUser(userBean.getEntity().getUsername());
        try{
            if(user==null){

                userBean.getUserService().addUser(userBean);
                user = userBean.getUserService().findUser(userBean.getEntity().getUsername());
                if(user==null){
                    throw new Exception("Cannot insert new item");
                }
                externalContext.getSessionMap().put("user", user);
                if(user.getRole().equals("guest"))
                    externalContext.redirect(externalContext.getRequestContextPath() + "/upload.xhtml");
                else
                    externalContext.redirect(externalContext.getRequestContextPath() + "/adminUploads.xhtml");
                context.addMessage(null, new FacesMessage("Registration succeeded."));
            }
            else{
                context.addMessage(null, new FacesMessage("Username already exists"));
            }
        }
        catch(Exception e){
            context.addMessage(null, new FacesMessage("Register failed"));
        }
    }
}
