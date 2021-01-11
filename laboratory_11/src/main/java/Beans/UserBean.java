package Beans;

import Entities.UserEntity;
import Interfaces.UserInterface;
import Services.UserService;

import javax.ejb.EJB;
//import javax.enterprise.context.SessionScoped;
//import javax.inject.Named;
//import java.io.Serializable;

// to be injected
//@Named("userBean")
//@SessionScoped
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;

@ManagedBean(name="userBean")
@SessionScoped
public class UserBean implements Serializable {
    @Inject
    private UserInterface userService;
    private UserEntity entity = new UserEntity();

    public UserInterface getUserService() {
        return userService;
    }

    public void setUserService(UserInterface userService) {
        this.userService = userService;
    }

    public UserEntity getEntity() {
        return entity;
    }

    public void setEntity(UserEntity entity) {
        this.entity = entity;
    }
}
