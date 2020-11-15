package Beans;

import DBConnection.DBActions;
import Solver.ChocoSolver;
import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

@ManagedBean(name="manager")
@RequestScoped
public class Manager {
    private List<PersonBean> persons;
    private List<MeetingBean> meetings;
    private List<String> problemResult;

    public void addPerson(PersonBean personBean) {
            DBActions.addPerson(personBean);
    }

    public void addMeeting(MeetingBean meetingBean){
        DBActions.addMeeting(meetingBean);
    }

    public List<PersonBean> getPersons(){
        this.persons = DBActions.getPersons(); return this.persons;
    }
    public List<MeetingBean> getMeetings(){return DBActions.getMeetings();}

    public List<String> getProblemResult() {
        return problemResult;
    }

    public String importAndSolveFile(String importedFile){
        problemResult = new ArrayList<>();
        try {
            ChocoSolver solver = new ChocoSolver(importedFile);
            solver.solve();
            problemResult = solver.result();
        } catch (IOException e) {
            e.printStackTrace();
            problemResult.add("Error when solving [Maybe invalid file path / invalid format]");
        } catch (Exception e) {
            e.printStackTrace();
            problemResult.add(e.getMessage());
        }
        Flash flash = FacesContext.getCurrentInstance().
                getExternalContext().getFlash();
        flash.put("result", problemResult);
        return "displayResult?faces-redirect=true";
    }
}
