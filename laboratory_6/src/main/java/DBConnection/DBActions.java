package DBConnection;

import Beans.LocationBean;
import Beans.MeetingBean;
import Beans.PersonBean;
import Entities.*;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DBActions {
//    public static Connection createConnection() {
//        try {
//            Class.forName("org.postgresql.Driver").newInstance();
//            return DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/postgres?user=postgres&password=1");
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger("DBACTIONS").log(Level.SEVERE, ex.toString(), ex);
//        } catch (InstantiationException ex) {
//            Logger.getLogger("DBACTIONS").log(Level.SEVERE, ex.toString(), ex);
//        } catch (IllegalAccessException ex) {
//            Logger.getLogger("DBACTIONS").log(Level.SEVERE, ex.toString(), ex);
//        } catch (SQLException ex) {
//            Logger.getLogger("DBACTIONS").log(Level.SEVERE, ex.toString(), ex);
//        }
//        return null;
//    }

    public static List<PersonBean> getPersons(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("custom_persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Query query = entityManager.createQuery("SELECT p FROM Person p");
        List<Person> entities = query.getResultList();

        List<PersonBean> ls = new ArrayList<>();
        for (Person entity : entities)
        {
            PersonBean personBean = new PersonBean();
            personBean.setEntity(entity);
            ls.add(personBean);
        }

        entityManager.close();
        entityManagerFactory.close();
        return ls;
    }

    public static List<LocationBean> getLocations(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("custom_persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Query query = entityManager.createQuery("SELECT l FROM Location l");
        List<Location> entities = query.getResultList();

        List<LocationBean> ls = new ArrayList<>();
        for (Location entity : entities)
        {
            LocationBean locationBean = new LocationBean();
            locationBean.setEntity(entity);
            ls.add(locationBean);
        }

        entityManager.close();
        entityManagerFactory.close();
        return ls;
    }

    public static List<MeetingBean> getMeetings(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("custom_persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Query query = entityManager.createQuery("SELECT p FROM Meeting p");
        List<Meeting> entities = query.getResultList();

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

        entityManager.close();
        entityManagerFactory.close();
        return ls;
    }

    public static void addPerson(PersonBean personBean) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("custom_persistence");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();
        em.persist(personBean.getEntity());
        em.getTransaction().commit();

        em.close();
        factory.close();
    }

    public static void addMeeting(MeetingBean meetingBean) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("custom_persistence");
        EntityManager em = factory.createEntityManager();

        Location location = meetingBean.getSelectedLocation().getEntity();
        Meeting meeting = meetingBean.getEntity();
        meeting.setLocation(location);

        List<Person> persons = new ArrayList<>();
        for (PersonBean personBean : meetingBean.getSelectedPersons()){
            persons.add(personBean.getEntity());
        }

        meeting.setPersons(persons);
        em.getTransaction().begin();
        em.persist(meeting);
        em.getTransaction().commit();

        em.close();
        factory.close();
    }

    public static void addLocation(LocationBean locationBean) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("custom_persistence");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();
        em.persist(locationBean.getEntity());
        em.getTransaction().commit();

        em.close();
        factory.close();
    }

    public static List<MeetingBean> searchMeetings(String topic, String personName, String locationName) throws SQLException
    {
        List<MeetingBean> result = new ArrayList<>();

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("custom_persistence");
        EntityManager em = factory.createEntityManager();

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Meeting> query = builder.createQuery(Meeting.class);

        Root<Meeting> e = query.from(Meeting.class);
        ListJoin<Meeting, Person> person = e.join(Meeting_.persons);

        Predicate condition = null;

        if (topic != null)
        {
            condition = builder.equal(e.get(Meeting_.topic), topic);
        }

        if (personName != null)
        {

            if (condition != null)
                condition = builder.and(
                        condition,
                        builder.like(person.get(Person_.name), "%"+personName+"%")
                );
            else
                condition = builder.like(person.get(Person_.name), "%"+personName+"%");
        }

        if (locationName != null)
        {
            if (condition != null)
                condition = builder.and(
                        condition,
                        builder.like(e.get(Meeting_.location).get(Location_.name), "%" + locationName + "%")
                );
            else
                condition = builder.like(e.get(Meeting_.location).get(Location_.name), "%" + locationName + "%");
        }

        query.where(condition).distinct(true);

        TypedQuery<Meeting> q = em.createQuery(query);
        List<Meeting> entities = q.getResultList();

        for (Meeting entity : entities)
        {
            MeetingBean meeting = new MeetingBean();
            meeting.setEntity(entity);
            result.add(meeting);
        }

        em.close();
        factory.close();

        return result;
    }
}