/*
 * Copyright (c) 17.2020 - Owned by lapachitei
 */

package DAO;

import Beans.LocationBean;
import Beans.MeetingBean;
import Beans.PersonBean;
import Entities.*;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class MeetingManager extends DataRepository<Meeting,Integer>{
    @PersistenceContext
    private EntityManager em;

    public MeetingManager(){
        super(Meeting.class);
    }

    public void addMeeting(MeetingBean m){
        Location location = m.getSelectedLocation().getEntity();
        Meeting meeting = m.getEntity();
        meeting.setLocation(location);

        List<Person> persons = new ArrayList<>();
        for (PersonBean personBean : m.getSelectedPersons()){
            persons.add(personBean.getEntity());
        }

        meeting.setPersons(persons);
        this.persist(meeting);
    }

    public List<MeetingBean> getMeetings(){
        List<Meeting> entities = this.findAll();

        List<MeetingBean> ls = new ArrayList<>();
        for (Meeting entity : entities)
        {
            MeetingBean meetingBean = new MeetingBean();
            meetingBean.setEntity(entity);

            LocationBean locationBean = new LocationBean();
            locationBean.setEntity(entity.getLocation());
            meetingBean.setSelectedLocation(locationBean);

            List<PersonBean> persons = new ArrayList<>();
            for(Person p : entity.getPersons()){
                PersonBean personBean = new PersonBean();
                personBean.setEntity(p);
                persons.add(personBean);
            }
            meetingBean.setSelectedPersons(persons);
            ls.add(meetingBean);
        }
        return ls;
    }

}
