/*
 * Copyright (c) 17.2020 - Owned by lapachitei
 */

package Beans;

import org.primefaces.event.CloseEvent;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "dataEdit")
@RequestScoped
public class DialogDataEdit{

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void onClose(CloseEvent closeEvent){
        addMessage(closeEvent.getComponent().getId() + " closed", "So you don't want to edit anymore?");
    }

    public void save(){
        addMessage("Save button pressed", "");
    }

}
