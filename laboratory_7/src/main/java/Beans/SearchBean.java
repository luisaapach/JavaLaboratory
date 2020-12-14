/*
 * Copyright (c) 17.2020 - Owned by lapachitei
 */

package Beans;

import DAO.MeetingManager;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "search")
@SessionScoped
public class SearchBean implements Serializable {
    @EJB
    MeetingManager meetingManager;

    private List<String> selectedCriterias;
    private String toSearch;
    private List<MeetingBean> meetings = new ArrayList<>();

    public SearchBean() {
        meetings = meetingManager.getMeetings();
    }

    public String getToSearch() {
        return toSearch;
    }

    public void setToSearch(String toSearch) {
        this.toSearch = toSearch;
    }

    public List<String> getSelectedCriterias() {
        return selectedCriterias;
    }

    public void setSelectedCriterias(List<String> selectedCriterias) {
        this.selectedCriterias = selectedCriterias;
    }

    public List<MeetingBean> getMeetings() {
        return meetings;
    }

    public void doSearch(){
        String topic = null;
        String personName = null;
        String location = null;
        if(selectedCriterias.size()==0) {
            meetings = meetingManager.getMeetings();
            return;
        }
        if(selectedCriterias.contains("topic")){
            topic = toSearch;
        }
        if(selectedCriterias.contains("name")){
            personName = toSearch;
        }
        if(selectedCriterias.contains("location")){
            location = toSearch;
        }
        //meetings = meetingManager.searchMeetings(topic,personName,location);
    }
}
