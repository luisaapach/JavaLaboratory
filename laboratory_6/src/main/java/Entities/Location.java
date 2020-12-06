/*
 * Copyright (c) 17.2020 - Owned by lapachitei
 */

package Entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Location implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigDecimal id;
    @NotNull
    private String name;

    @OneToMany( cascade = CascadeType.ALL,
            mappedBy = "location" )
    private List<Meeting> meetingsList;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public List<Meeting> getMeetingsList() {
//        return meetingsList;
//    }
//
//    public void setMeetingsList(List<Meeting> meetingsList) {
//        this.meetingsList = meetingsList;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Location)) {
            return false;
        }
        Location other = (Location) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }


    @Override
    public String toString() {
        return "LocationEntity{" +
                "id=" + id.toString() +
                ", name='" + name + '\'' +
                '}';
    }
}
