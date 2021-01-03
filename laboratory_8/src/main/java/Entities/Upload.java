package Entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
@Entity
public class Upload implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigDecimal id;

    @NotNull(message = "SubmissionID cannot be null")
    private String submissionID;

    @NotNull(message = "FileName cannot be null")
    @Size(min = 10, max = 200, message
            = "FileName must be between 10 and 200 characters")
    private String fileName;

    @ManyToOne
    @JoinColumn( name = "userentity_id", referencedColumnName = "id", nullable = false )
    private UserEntity user;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getSubmissionID() {
        return submissionID;
    }

    public void setSubmissionID(String submissionID) {
        this.submissionID = submissionID;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
