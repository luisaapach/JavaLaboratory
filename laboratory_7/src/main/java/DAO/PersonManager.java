/*
 * Copyright (c) 17.2020 - Owned by lapachitei
 */

package DAO;

import Beans.PersonBean;
import Entities.Person;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class PersonManager extends DataRepository<Person,Integer>{
    public PersonManager(){
        super(Person.class);
    }

    @Transactional
    public void addPerson(PersonBean p)
    {

        this.persist(p.getEntity());
    }

    public List<PersonBean> getPersons()
    {
        List<Person> entities = this.findAll();

        List<PersonBean> ls = new ArrayList<>();
        for (Person entity : entities)
        {
            PersonBean personBean = new PersonBean();
            personBean.setEntity(entity);
            ls.add(personBean);
        }
        return ls;
    }
}
