/*
 * Copyright (c) 17.2020 - Owned by lapachitei
 */

package Entities;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Meeting.getAll",
                query = "select m from Meeting m")
})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING,name = "meeting_type")
@DiscriminatorValue("simple")
public class Meeting implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigDecimal id;
    @NotNull
    private String topic;
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date starting_time;
    private BigDecimal duration = BigDecimal.valueOf(0);

    @JoinTable(name = "meeting_person",
            joinColumns = {
                    @JoinColumn(name = "meeting_id",
                            referencedColumnName = "id",nullable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "person_id",
                            referencedColumnName = "id",nullable = false)})
    @ManyToMany
    private List<Person> persons;

    @ManyToOne
    @JoinColumn( name = "location_id", referencedColumnName = "id", nullable = false )
    private Location location;

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

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> selectedPersons) {
        this.persons = selectedPersons;

    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location selectedLocation) {
        this.location = selectedLocation;
    }

    private String theMeetingType;

    @Formula("meeting_type")
    public String getTheMeetingType() {
        return theMeetingType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Meeting)) {
            return false;
        }
        Meeting other = (Meeting) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "MeetingEntity{" +
                "id=" + id.toString() ;
    }
}
