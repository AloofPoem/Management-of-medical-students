package com.gestionestudiantesmedicina.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "teacher")
public class Teacher extends Person {

    @Column(name = "specialty", nullable = false)
    private String specialty;

    @OneToMany(mappedBy = "teacher", cascade = {CascadeType.MERGE,CascadeType.REFRESH, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Record> records;

    @OneToMany(mappedBy = "teacher", cascade = {CascadeType.MERGE,CascadeType.REFRESH, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Schedule> schedules;

    public Teacher() {
    }

    public Teacher(Long id, String name, String lastName, LocalDate birthDate, String password, String specialty) {
        super(id, name, lastName, birthDate, password);
        this.specialty = specialty;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
    
}