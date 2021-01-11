package Beans;

import Authentification.AuthentificationUtils;
import Entities.GroupEntity;
import Entities.UserEntity;
import Interfaces.GroupInterface;
import Services.UserService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;

@ManagedBean(name="registrationBean")
@SessionScoped
public class RegistrationBean implements Serializable {
    @Inject
    private GroupInterface groupService;

    public GroupInterface getGroupService() {
        return groupService;
    }

    public void setGroupService(GroupInterface groupService) {
        this.groupService = groupService;
    }

    public void addUser(UserBean userBean){
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        UserEntity user = userBean.getUserService().findUser(userBean.getEntity().getUsername());
        try{
            if(user==null){
                ArrayList<GroupEntity> groupEntities = new ArrayList<>();
                GroupEntity groupEntity;
                if(userBean.getEntity().getUsername().equals("admin") && userBean.getEntity().getPassword().equals("admin")){
                    groupEntity = groupService.findGroup("admin");
                }
                else{
                    groupEntity = groupService.findGroup("guest");
                }
                groupEntities.add(groupEntity);
                userBean.getEntity().setGroups(groupEntities);
                userBean.getEntity().setPassword(AuthentificationUtils.encodeSHA256(userBean.getEntity().getPassword()));
                userBean.getUserService().addUser(userBean);
                user = userBean.getUserService().findUser(userBean.getEntity().getUsername());
                if(user==null){
                    throw new Exception("Cannot insert new item");
                }
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
