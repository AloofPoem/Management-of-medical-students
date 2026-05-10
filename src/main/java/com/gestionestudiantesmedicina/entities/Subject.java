package com.gestionestudiantesmedicina.entities;

import javax.persistence.Column;
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

    @Column(name = "name_subject", nullable = false)
    private String nameSubject;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Practice ptactice;

    public Subject() {
    }

    public Subject(Long idSubject, String nameSubject, Practice ptactice) {
        IdSubject = idSubject;
        this.nameSubject = nameSubject;
        this.ptactice = ptactice;
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

    public Practice getClassSubject() {
        return ptactice;
    }

    public void setClassSubject(Practice ptactice) {
        this.ptactice = ptactice;
    }
}