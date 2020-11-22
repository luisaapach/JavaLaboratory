package DBConnection;

import Beans.LocationBean;
import Beans.MeetingBean;
import Beans.PersonBean;
import sun.rmi.runtime.Log;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBActions {
//    ```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````public static Connection createConnection() {
//        try {
//            Class.forName("org.postgresql.Driver").newInstance();
//            return DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/postgres?user=postgres&password=1");
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger("DatabaseUtils").log(Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            Logger.getLogger("DatabaseUtils").log(Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            Logger.getLogger("DatabaseUtils").log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger("DatabaseUtils").log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````
    public static List<PersonBean> getPersons(DataSource dataSource){
        List<PersonBean> ls = new ArrayList<>();
        try {
            Connection conn = dataSource.getConnection();
            assert conn != null;
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM public.\"Persons\"");
            while (rs.next()) {
                PersonBean p = new PersonBean();
                p.setId(rs.getBigDecimal(1));
                p.setName((String) rs.getString(2));
                ls.add(p);
            }
            rs.close();
            st.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ls;
    }

    public static PersonBean getPerson(DataSource dataSource, BigDecimal id){
        PersonBean p = new PersonBean();
        try {
            Connection conn = dataSource.getConnection();
            assert conn != null;
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM public.\"Persons\" where id="+String.valueOf(id));
            while (rs.next()) {
                p.setId(rs.getBigDecimal(1));
                p.setName((String) rs.getString(2));
            }
            rs.close();
            st.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return p;
    }

    public static LocationBean getLocation(DataSource dataSource, BigDecimal id){
        LocationBean p = new LocationBean();
        try {
            Connection conn = dataSource.getConnection();
            assert conn != null;
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM public.\"Locations\" where id="+String.valueOf(id));
            while (rs.next()) {
                p.setId(rs.getBigDecimal(1));
                p.setName((String) rs.getString(2));
            }
            rs.close();
            st.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return p;
    }

    public static List<LocationBean> getLocations(DataSource dataSource){
        List<LocationBean> ls = new ArrayList<>();
        try {
            Connection conn = dataSource.getConnection();
            assert conn != null;
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM public.\"Locations\"");
            while (rs.next()) {
                LocationBean p = new LocationBean();
                p.setId(rs.getBigDecimal(1));
                p.setName((String) rs.getString(2));
                ls.add(p);
            }
            rs.close();
            st.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ls;
    }

    public static List<MeetingBean> getMeetings(DataSource dataSource){
        List<MeetingBean> ls = new ArrayList<>();
        try {
            Connection conn = dataSource.getConnection();
            assert conn != null;
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM public.\"MeetingsTable\"");
            while (rs.next()) {
                MeetingBean p = new MeetingBean();
                p.setId(rs.getBigDecimal(1));
                p.setDuration(rs.getBigDecimal(2));
                p.setTopic(rs.getString(3));
                Array personIDs = rs.getArray(4);
                Long[] ids = (Long[]) personIDs.getArray();
                List<PersonBean> s = new ArrayList<>();
                for (Long id : ids){
                    s.add(DBActions.getPerson(dataSource,new BigDecimal(id)));
                }
                p.setSelectedPersons(s);
                p.setSelectedLocation(DBActions.getLocation(dataSource,rs.getBigDecimal(6)));
                p.setStarting_time(new Date(rs.getString(5)));
                ls.add(p);
            }
            rs.close();
            st.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ls;
    }

    public static boolean addPerson(DataSource dataSource, PersonBean personBean) {
        String SQLinsert = "INSERT INTO public.\"Persons\"(id,name) "
                + "VALUES(?,?)";

        try {
            Connection conn = dataSource.getConnection("postgres","1");

            PreparedStatement pstmt = conn.prepareStatement(SQLinsert, Statement.RETURN_GENERATED_KEYS);
            pstmt.setBigDecimal(1, personBean.getId());
            pstmt.setString(2, personBean.getName());

            int rowsAffected = pstmt.executeUpdate();
// this will check the affected row(s)
            if (rowsAffected > 0) {
// then we get the ID of the affected row(s)
                return true;
            }
        }catch (SQLException throwables){
            throwables.getStackTrace();
            return false;
        }
        return false;
    }

    public static boolean addMeeting(DataSource dataSource, MeetingBean meetingBean) {
        String SQLinsert = "INSERT INTO public.\"MeetingsTable\"(id,topic,starting_time,duration,persons) "
                + "VALUES(?,?,?,?,?)";

        try {
            Connection conn = dataSource.getConnection();

            PreparedStatement pstmt = conn.prepareStatement(SQLinsert, Statement.RETURN_GENERATED_KEYS);
            pstmt.setBigDecimal(1, meetingBean.getId());
            pstmt.setString(2, meetingBean.getTopic());
            //System.out.println(new java.sql.Timestamp(meetingBean.getStarting_time().getTime()));
            //System.out.println(new java.sql.Time(meetingBean.getStarting_time().getTime()));
            pstmt.setString(3, new java.sql.Timestamp(meetingBean.getStarting_time().getTime()).toString());
            pstmt.setBigDecimal(4,meetingBean.getDuration());
            List<BigDecimal> s = new ArrayList<>();
            for (PersonBean personBean:meetingBean.getSelectedPersons()){
                s.add(personBean.getId());
            }
            BigDecimal[] itemsArray = new BigDecimal[meetingBean.getSelectedPersons().size()];
            itemsArray = s.toArray(itemsArray);
            Array items = conn.createArrayOf("bigint",itemsArray);
            pstmt.setArray(5, items);

            int rowsAffected = pstmt.executeUpdate();
// this will check the affected row(s)
            if (rowsAffected > 0) {
// then we get the ID of the affected row(s)
                return true;
            }
        }catch (SQLException throwables){
            throwables.getStackTrace();
            return false;
        }
        return false;
    }

    public static boolean addLocation(DataSource dataSource, LocationBean locationBean) {
        String SQLinsert = "INSERT INTO public.\"Locations\"(id,name) "
                + "VALUES(?,?)";

        try {
            Connection conn = dataSource.getConnection();

            PreparedStatement pstmt = conn.prepareStatement(SQLinsert, Statement.RETURN_GENERATED_KEYS);
            pstmt.setBigDecimal(1, locationBean.getId());
            pstmt.setString(2, locationBean.getName());

            int rowsAffected = pstmt.executeUpdate();
// this will check the affected row(s)
            if (rowsAffected > 0) {
// then we get the ID of the affected row(s)
                return true;
            }
        }catch (SQLException throwables){
            throwables.getStackTrace();
            return false;
        }
        return false;
    }
}