package Beans;

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
    private BigDecimal id;
    private String name;

    public BigDecimal getId() {
        return id;
    }

//    public void setId(String id) {
//        this.id = new BigInteger(id);
//    }


    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
