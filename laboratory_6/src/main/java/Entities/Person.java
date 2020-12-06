/*
 * Copyright (c) 17.2020 - Owned by lapachitei
 */

package Entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigDecimal id;
    @NotNull
    private String name;

//    @ManyToOne
//    @JoinColumn( name = "meeting_id", referencedColumnName = "id", nullable = false )
//    private Meeting meeting;

    @ManyToMany(mappedBy = "persons")
    private List<Meeting> meetings;

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

    public List<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
    }

    @Override
    public String toString() {
        return "PersonEntity{" +
                "id=" + id.toString() +
                ", name='" + name + '\'' +
                '}';
    }
}
