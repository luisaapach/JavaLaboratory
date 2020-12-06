package Beans;

import Entities.Location;
import Entities.Meeting;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
@ManagedBean(name = "locationBean")
@SessionScoped
public class LocationBean  implements Serializable {
    Location entity = new Location();

    public Location getEntity() {
        return entity;
    }

    public void setEntity(Location entity) {
        this.entity = entity;
    }
}
