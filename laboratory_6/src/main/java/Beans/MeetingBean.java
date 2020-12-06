package Beans;

import Entities.Meeting;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@ManagedBean(name="meetingBean")
@SessionScoped
public class MeetingBean implements Serializable {
    Meeting entity = new Meeting();
    List<PersonBean> selectedPersons;
    LocationBean selectedLocation;

    public List<PersonBean> getSelectedPersons() {
        return selectedPersons;
    }

    public void setSelectedPersons(List<PersonBean> selectedPersons) {
        this.selectedPersons = selectedPersons;
    }

    public LocationBean getSelectedLocation() {
        return selectedLocation;
    }

    public void setSelectedLocation(LocationBean selectedLocation) {
        this.selectedLocation = selectedLocation;
    }

    public Meeting getEntity() {
        return entity;
    }
    public void setEntity(Meeting entity) {
        this.entity = entity;
    }
}
