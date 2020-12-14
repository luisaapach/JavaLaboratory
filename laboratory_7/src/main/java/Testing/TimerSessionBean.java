/*
 * Copyright (c) 17.2020 - Owned by lapachitei
 */

package Testing;

import DAO.AssignmentCheckAvailability;
import DAO.MeetingManager;
import Entities.Meeting;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.TimerService;
@Stateless
public class TimerSessionBean {
    @EJB
    private AssignmentCheckAvailability checkAvailability;
    @EJB
    private MeetingManager meetingManager;

    @Schedule(second="*/1", minute="*",hour="*", persistent=false)
    public void doWork(){
        System.out.println("Working hard...");
        Meeting m = meetingManager.findAll().get(0);
        checkAvailability.available(m,4);

    }
}

