//package Beans;
//
//import DBConnection.DBActions;
//import Solver.ChocoSolver;
//import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
//
//import javax.annotation.Resource;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.RequestScoped;
//import javax.faces.context.ExternalContext;
//import javax.faces.context.FacesContext;
//import javax.faces.context.Flash;
//import javax.faces.view.ViewScoped;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.sql.DataSource;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.*;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//@ManagedBean(name="manager")
//@RequestScoped
//public class Manager {
//    private static final DBActions dbActions = new DBActions();
//    private static List<PersonBean> persons;
//    private static List<MeetingBean> meetings;
//    private static List<String> problemResult;
//    private static List<LocationBean> locations;
//    private static List<AssignmentBean> assignments;
//
//    public static void addPerson(PersonBean personBean) {
//
//        dbActions.addPerson(personBean);
//    }
//
//    public static void addMeeting(MeetingBean meetingBean){
//        dbActions.addMeeting(meetingBean);
//    }
//
//    public static void addLocation(LocationBean locationBean){
//        locationBean.getLocationRepo().addLocation(locationBean);
//    }
//
//    public static List<PersonBean> getPersons(){
//        Manager.persons = dbActions.getPersons();
//        return Manager.persons;
//    }
//    public static List<MeetingBean> getMeetings(){
//        meetings = dbActions.getMeetings();
//        return meetings;
//    }
//
//    public static List<LocationBean> getLocations() {
//        locations =  dbActions.getLocations(); return locations;
//    }
//
//    public static List<String> getProblemResult() {
//        return problemResult;
//    }
//
//    public static String importAndSolveFile(String importedFile){
//        problemResult = new ArrayList<>();
//        try {
//            ChocoSolver solver = new ChocoSolver(importedFile);
//            solver.solve();
//            problemResult = solver.result();
//        } catch (IOException e) {
//            e.printStackTrace();
//            problemResult.add("Error when solving [Maybe invalid file path / invalid format]");
//        } catch (Exception e) {
//            e.printStackTrace();
//            problemResult.add(e.getMessage());
//        }
//        Flash flash = FacesContext.getCurrentInstance().
//                getExternalContext().getFlash();
//        flash.put("result", problemResult);
//
//        return "displayResult?faces-redirect=true";
//    }
//
//    public static List<MeetingBean> searchMeetings(String topic, String personName, String locationName)
//    {
//        return dbActions.searchMeetings(topic,personName,locationName);
//    }
//
//}
