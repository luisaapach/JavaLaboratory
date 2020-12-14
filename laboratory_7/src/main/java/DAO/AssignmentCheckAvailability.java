/*
 * Copyright (c) 17.2020 - Owned by lapachitei
 */

package DAO;

import Entities.Meeting;
import Testing.InterceptorCustom;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
@Interceptors(InterceptorCustom.class)
public class AssignmentCheckAvailability {
    // offers methods for checking the availability of a meeting
    @PersistenceContext
    private EntityManager em;

    public boolean available(Meeting meeting, Integer numberOfPersons){
        Integer capacity = meeting.getCapacity();
        Meeting getMeeting = em.find(Meeting.class, meeting.getId());

        return (getMeeting.getPersons().size() + numberOfPersons) <= capacity;
    }
}
