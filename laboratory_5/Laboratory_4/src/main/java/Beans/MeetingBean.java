package Beans;

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
    private BigDecimal id;
    private String topic;
    private Date starting_time;
    private BigDecimal duration;
    private List<PersonBean> selectedPersons;
    private LocationBean selectedLocation;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Date getStarting_time() {
        return starting_time;
    }

    public void setStarting_time(Date starting_time) {
        this.starting_time = starting_time;
    }

    public BigDecimal getDuration() {
        return duration;
    }

    public void setDuration(BigDecimal duration) {
        this.duration = duration;
    }

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
}
