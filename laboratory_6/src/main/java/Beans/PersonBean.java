package Beans;

import Entities.Person;

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
@ManagedBean(name = "personBean")
@SessionScoped
public class PersonBean  implements Serializable {
    private Person entity = new Person();

    public Person getEntity() {
        return entity;
    }

    public void setEntity(Person entity) {
        this.entity = entity;
    }
}
