/*
 * Copyright (c) 17.2020 - Owned by lapachitei
 */

package DAO;

import Beans.AssignmentBean;
import Beans.AssignmentsStorage;
import Beans.MeetingBean;
import Beans.PersonBean;
import Entities.Meeting;
import Entities.Person;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateful
public class AssignmentManager extends DataRepository<Meeting,Integer> {
    // responsible with the assignment of one or more persons to a specific meeting.
    // The assignment should be atomic, either all persons are successfully assigned,
    // or the transaction will be rolled back
    @EJB
    private AssignmentCheckAvailability checkAvailability;
    @EJB
    private AssignmentsStorage assignmentsStorage;
    @PersistenceContext
    private EntityManager em;

    public void addAssignment(AssignmentBean assignment){
        if(checkAvailability.available(assignment.getSelectedMeeting().getEntity(),assignment.getSelectedPersons().size()))
        {
            Meeting new_meeting = assignment.getSelectedMeeting().getEntity();
            List<Person> personList = new_meeting.getPersons();
            for(PersonBean p : assignment.getSelectedPersons()){
                if(!personList.contains(p.getEntity()))
                    personList.add(p.getEntity());
            }
            new_meeting.setPersons(personList);
            em.merge(new_meeting);
            assignmentsStorage.addAssignment(new_meeting,new_meeting.getPersons());
        }
    }




}
