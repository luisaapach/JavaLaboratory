package Beans;

import DAO.PersonManager;
import Entities.Person;

import javax.ejb.EJB;
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
    @EJB
    private PersonManager personManager;

    private Person entity = new Person();

    public PersonManager getPersonManager() {
        return personManager;
    }

    public Person getEntity() {
        return entity;
    }

    public void setEntity(Person entity) {
        this.entity = entity;
    }
}
