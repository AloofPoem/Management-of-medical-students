package com.gestionestudiantesmedicina.daos;

import com.gestionestudiantesmedicina.entities.Person;

public class PersonDAO extends AbstractGenericDAO<Person, Long> {

    public PersonDAO() {
        super(Person.class);
    }

}
