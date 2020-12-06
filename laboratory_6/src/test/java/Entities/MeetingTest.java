/*
 * Copyright (c) 17.2020 - Owned by lapachitei
 */

package Entities;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class MeetingTest extends Init{

    @Test
    public void testGetObjectById_success() {
        // Retrieving existing Entities
        Meeting meeting = em.find(Meeting.class, BigDecimal.valueOf(1));
        assertNotNull(meeting);
    }

    @Test
    public void testGetAll_success() {
        // JPA Queries with JPQL
        List<Meeting> meetings = em.createNamedQuery("Meeting.getAll", Meeting.class).getResultList();
        assertEquals(3, meetings.size());
    }

    @Test
    public void testPersist_success() {
        // Storing new entity object
        em.getTransaction().begin();
        Meeting meeting = new Meeting();
        meeting.setId(BigDecimal.valueOf(1));
        meeting.setDuration(BigDecimal.valueOf(40));
        meeting.setStarting_time(new Date(System.currentTimeMillis()));
        Location location = em.find(Location.class, BigDecimal.valueOf(1));

        meeting.setLocation(location);
        meeting.setTopic("Topic");
        em.persist(meeting);
        em.getTransaction().commit();

        // Retrieving existing Entities
        List<Meeting> meetings = em.createNamedQuery("Meeting.getAll", Meeting.class).getResultList();

        assertNotNull(meetings);
        assertEquals(3, meetings.size());
    }

    @Test
    public void testDelete_success(){
        // Deleting Entities
        Meeting meeting = em.find(Meeting.class, BigDecimal.valueOf(1));

        em.getTransaction().begin();
        em.remove(meeting);
        em.getTransaction().commit();

        // Retrieving existing Entities
        List<Meeting> meetings = em.createNamedQuery("Meeting.getAll", Meeting.class).getResultList();

        assertEquals(2, meetings.size());
    }

}