package com.gestionestudiantesmedicina.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin extends Person {

    public Admin(Long id, String name, String lastName, LocalDate birthDate, String password) {
        super(id, name, lastName, birthDate, password);
    }
    
    public Admin() {
    }
}
