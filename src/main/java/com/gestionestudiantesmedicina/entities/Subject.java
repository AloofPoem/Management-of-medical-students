package com.gestionestudiantesmedicina.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdSubject;

    private String nameSubject;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Class classSubject;

    public Subject() {
    }

    public Subject(Long idSubject, String nameSubject, Class classSubject) {
        IdSubject = idSubject;
        this.nameSubject = nameSubject;
        this.classSubject = classSubject;
    }

    public Long getIdSubject() {
        return IdSubject;
    }

    public void setIdSubject(Long idSubject) {
        IdSubject = idSubject;
    }

    public String getNameSubject() {
        return nameSubject;
    }

    public void setNameSubject(String nameSubject) {
        this.nameSubject = nameSubject;
    }

    public Class getClassSubject() {
        return classSubject;
    }

    public void setClassSubject(Class classSubject) {
        this.classSubject = classSubject;
    }
}