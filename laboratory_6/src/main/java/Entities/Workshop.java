/*
 * Copyright (c) 17.2020 - Owned by lapachitei
 */

package Entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@DiscriminatorValue("workshop")
public class Workshop extends Meeting implements Serializable {
    String resources;

    public String getResources() {
        return resources;
    }

    public void setResources(String resources) {
        this.resources = resources;
    }
}
