package com.gestionestudiantesmedicina.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.gestionestudiantesmedicina.enumeration.MaritalStatus;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStudent;

    private String identityStudent;

    @Enumerated(EnumType.STRING)
    @Column(name = "marital_status")
    private MaritalStatus maritalStatus;

    private String birthPlace;
    private String addressTunja;
    private String permanentAddress;
    private String phoneNumber;
    private String email;
    private String secondLanguage;
    private int roomies;
    private int familyCoreTunja;
    private LocalDate entryDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "health_data_id")
    private HealthData healthData;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "academic_data_id")
    private AcademicData academicData;

    @ManyToMany
    @JoinTable(
        name = "student_class",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "class_id")
    )
    private List<Class> classes;

    @ManyToOne
    @JoinColumn(name = "student_type_id")
    private StudentType studentType;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Relative> relatives;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Legalrepresentative> legalRepresentatives;

    public Student() {}

    public Student(Long idStudent, String identityStudent, MaritalStatus maritalStatus, String birthPlace,
            String addressTunja, String permanentAddress, String phoneNumber, String email, String secondLanguage,
            int roomies, int familyCoreTunja, LocalDate entryDate, HealthData healthData, AcademicData academicData,
            List<Class> classes, StudentType studentType, List<Relative> relatives,
            List<Legalrepresentative> legalRepresentatives) {
        this.idStudent = idStudent;
        this.identityStudent = identityStudent;
        this.maritalStatus = maritalStatus;
        this.birthPlace = birthPlace;
        this.addressTunja = addressTunja;
        this.permanentAddress = permanentAddress;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.secondLanguage = secondLanguage;
        this.roomies = roomies;
        this.familyCoreTunja = familyCoreTunja;
        this.entryDate = entryDate;
        this.healthData = healthData;
        this.academicData = academicData;
        this.classes = classes;
        this.studentType = studentType;
        this.relatives = relatives;
        this.legalRepresentatives = legalRepresentatives;
    }

    public Long getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(Long idStudent) {
        this.idStudent = idStudent;
    }

    public String getIdentityStudent() {
        return identityStudent;
    }

    public void setIdentityStudent(String identityStudent) {
        this.identityStudent = identityStudent;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getAddressTunja() {
        return addressTunja;
    }

    public void setAddressTunja(String addressTunja) {
        this.addressTunja = addressTunja;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSecondLanguage() {
        return secondLanguage;
    }

    public void setSecondLanguage(String secondLanguage) {
        this.secondLanguage = secondLanguage;
    }

    public int getRoomies() {
        return roomies;
    }

    public void setRoomies(int roomies) {
        this.roomies = roomies;
    }

    public int getFamilyCoreTunja() {
        return familyCoreTunja;
    }

    public void setFamilyCoreTunja(int familyCoreTunja) {
        this.familyCoreTunja = familyCoreTunja;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public HealthData getHealthData() {
        return healthData;
    }

    public void setHealthData(HealthData healthData) {
        this.healthData = healthData;
    }

    public AcademicData getAcademicData() {
        return academicData;
    }

    public void setAcademicData(AcademicData academicData) {
        this.academicData = academicData;
    }

    public List<Class> getClasses() {
        return classes;
    }

    public void setClasses(List<Class> classes) {
        this.classes = classes;
    }

    public StudentType getStudentType() {
        return studentType;
    }

    public void setStudentType(StudentType studentType) {
        this.studentType = studentType;
    }

    public List<Relative> getRelatives() {
        return relatives;
    }

    public void setRelatives(List<Relative> relatives) {
        this.relatives = relatives;
    }

    public List<Legalrepresentative> getLegalRepresentatives() {
        return legalRepresentatives;
    }

    public void setLegalRepresentatives(List<Legalrepresentative> legalRepresentatives) {
        this.legalRepresentatives = legalRepresentatives;
    }

    public void uploadDoc(String documentName) {
        System.out.println("Subiendo documento: " + documentName);
    }
}