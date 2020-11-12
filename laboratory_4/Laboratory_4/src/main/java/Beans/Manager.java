package Beans;

import DBConnection.DBActions;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.view.ViewScoped;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ManagedBean(name="manager")
@RequestScoped
public class Manager {
    private List<PersonBean> persons;
    private List<MeetingBean> meetings;
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
}
