package com.gestionestudiantesmedicina.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "portero")
public class Portero extends Person {

    public Portero(Long id, String name, String lastName, LocalDate birthDate, String password) {
        super(id, name, lastName, birthDate, password);
    }

    public Portero() {
    }
}