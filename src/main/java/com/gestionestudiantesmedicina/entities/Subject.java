package com.gestionestudiantesmedicina.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdSubject;

    @Column(name = "name_subject", nullable = false)
    private String nameSubject;

    @OneToMany(mappedBy = "subject", cascade = { CascadeType.MERGE, CascadeType.REFRESH,
            CascadeType.REMOVE }, orphanRemoval = true)
    private List<Practice> practices;

    public Subject() {
    }

    public Subject(Long idSubject, String nameSubject, List<Practice> practices) {
        IdSubject = idSubject;
        this.nameSubject = nameSubject;
        this.practices = practices;
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

    public List<Practice> getListPractices() {
        return practices;
    }

    public void setPractices(List<Practice> practices) {
        this.practices = practices;
    }
}