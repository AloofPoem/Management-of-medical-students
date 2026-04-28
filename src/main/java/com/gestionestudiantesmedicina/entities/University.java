package com.gestionestudiantesmedicina.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "university")
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUniversity;

    private String universityName;

    @OneToOne(mappedBy = "university")
    private AcademicData academicData;

    public University() {
    }

    public University(Long idUniversity, String universityName, AcademicData academicData) {
        this.idUniversity = idUniversity;
        this.universityName = universityName;
        this.academicData = academicData;
    }

    public Long getIdUniversity() {
        return idUniversity;
    }

    public void setIdUniversity(Long idUniversity) {
        this.idUniversity = idUniversity;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public AcademicData getAcademicData() {
        return academicData;
    }

    public void setAcademicData(AcademicData academicData) {
        this.academicData = academicData;
    }
}