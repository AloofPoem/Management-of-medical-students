package com.gestionestudiantesmedicina.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "university")
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUniversity;

    @Column(name = "university_name", nullable = false)
    private String universityName;

    @OneToMany(mappedBy = "university")
    private List<AcademicData> academicData;

    public University() {
    }

    public University(Long idUniversity, String universityName, List<AcademicData> academicData) {
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

    public List<AcademicData> getAcademicData() {
        return academicData;
    }

    public void setAcademicData(List<AcademicData> academicData) {
        this.academicData = academicData;
    }
}