package Entities;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Meeting.class)
public abstract class Meeting_ {

	public static volatile SingularAttribute<Meeting, BigDecimal> duration;
	public static volatile ListAttribute<Meeting, Person> persons;
	public static volatile SingularAttribute<Meeting, String> theMeetingType;
	public static volatile SingularAttribute<Meeting, String> topic;
	public static volatile SingularAttribute<Meeting, Date> starting_time;
	public static volatile SingularAttribute<Meeting, Location> location;
	public static volatile SingularAttribute<Meeting, BigDecimal> id;

	public static final String DURATION = "duration";
	public static final String PERSONS = "persons";
	public static final String THE_MEETING_TYPE = "theMeetingType";
	public static final String TOPIC = "topic";
	public static final String STARTING_TIME = "starting_time";
	public static final String LOCATION = "location";
	public static final String ID = "id";

}

