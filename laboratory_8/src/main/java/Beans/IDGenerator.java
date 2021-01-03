package Beans;

import AnnotationsCustom.Unique;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import java.io.Serializable;
import java.util.UUID;

@ApplicationScoped
public class IDGenerator implements Serializable {

    public @Produces @Unique String getSubmissionID() {

        return UUID.randomUUID().toString();
    }
}
