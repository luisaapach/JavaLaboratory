package Entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
@Entity
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigDecimal id;

    @NotNull(message = "Username cannot be null")
    @Size(min = 3, max = 50, message
            = "Username must be between 3 and 50 characters")
    private String username;

    @NotNull(message = "Password cannot be null")
    @Size(min = 3, max = 50, message
            = "Password must be between 3 and 50 characters")
    private String password;

    @NotNull(message = "Role cannot be null")
    private String role;
//
//    @OneToMany( cascade = CascadeType.ALL,
//            mappedBy = "user" )
//    private List<Upload> uploadList;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

//    public List<Upload> getUploadList() {
//        return uploadList;
//    }
//
//    public void setUploadList(List<Upload> uploadList) {
//        this.uploadList = uploadList;
//    }
}
