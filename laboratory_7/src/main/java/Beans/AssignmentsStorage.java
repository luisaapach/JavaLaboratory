/*
 * Copyright (c) 17.2020 - Owned by lapachitei
 */

package Beans;

import Entities.Meeting;
import Entities.Person;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@ConcurrencyManagement(javax.ejb.ConcurrencyManagementType.CONTAINER)
@Singleton
@Startup
public class AssignmentsStorage {
    HashMap<Meeting,List<Person>> assignments;

    @PostConstruct
    public void init() {
        assignments = new HashMap<>();
    }

    @Lock(LockType.WRITE)
    public void addAssignment(Meeting meeting, List<Person> personList){
        assignments.put(meeting,personList);
    }

}
