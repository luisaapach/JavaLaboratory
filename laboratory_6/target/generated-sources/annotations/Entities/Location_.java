package Entities;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Location.class)
public abstract class Location_ {

	public static volatile SingularAttribute<Location, String> name;
	public static volatile ListAttribute<Location, Meeting> meetingsList;
	public static volatile SingularAttribute<Location, BigDecimal> id;

	public static final String NAME = "name";
	public static final String MEETINGS_LIST = "meetingsList";
	public static final String ID = "id";

}

