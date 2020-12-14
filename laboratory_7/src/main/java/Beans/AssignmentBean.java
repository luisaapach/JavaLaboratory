/*
 * Copyright (c) 17.2020 - Owned by lapachitei
 */

package Beans;


import DAO.AssignmentManager;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name="assignmentBean")
@SessionScoped
public class AssignmentBean implements Serializable {
    @EJB
    private AssignmentManager meetingManager;

    List<PersonBean> selectedPersons;
    MeetingBean selectedMeeting;

    public AssignmentManager getMeetingManager() {
        return meetingManager;
    }

    public List<PersonBean> getSelectedPersons() {
        return selectedPersons;
    }

    public void setSelectedPersons(List<PersonBean> selectedPersons) {
        this.selectedPersons = selectedPersons;
    }

    public MeetingBean getSelectedMeeting() {
        return selectedMeeting;
    }

    public void setSelectedMeeting(MeetingBean selectedMeeting) {
        this.selectedMeeting = selectedMeeting;
    }
}
