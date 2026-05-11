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
@Table(name = "academic_data")
public class AcademicData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "academic_program", nullable = false)
    private String academicProgram;

    @Column(name = "semester", nullable = false)
    private String semester;

    @Column(name = "cumulative_average", nullable = false)
    private double cumulativeAverage;

    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;

    public AcademicData() {}

    public AcademicData(String academicProgram, String semester, double cumulativeAverage, University university) {
        this.academicProgram = academicProgram;
        this.semester = semester;
        this.cumulativeAverage = cumulativeAverage;
        this.university = university;
    }

    public Long getId() {
        return id;
    }

    public String getAcademicProgram() {
        return academicProgram;
    }

    public void setAcademicProgram(String academicProgram) {
        this.academicProgram = academicProgram;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public double getCumulativeAverage() {
        return cumulativeAverage;
    }

    public void setCumulativeAverage(double cumulativeAverage) {
        this.cumulativeAverage = cumulativeAverage;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }
}