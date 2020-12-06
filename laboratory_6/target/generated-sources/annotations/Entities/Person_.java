package Entities;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Person.class)
public abstract class Person_ {

	public static volatile SingularAttribute<Person, String> name;
	public static volatile ListAttribute<Person, Meeting> meetings;
	public static volatile SingularAttribute<Person, BigDecimal> id;

	public static final String NAME = "name";
	public static final String MEETINGS = "meetings";
	public static final String ID = "id";

}

