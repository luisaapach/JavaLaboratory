/*
 * Copyright (c) 17.2020 - Owned by lapachitei
 */

package Entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@DiscriminatorValue("conference")
public class Conference extends Meeting implements Serializable {
    String speakers;

    public String getSpeakers() {
        return speakers;
    }

    public void setSpeakers(String speakers) {
        this.speakers = speakers;
    }
}
