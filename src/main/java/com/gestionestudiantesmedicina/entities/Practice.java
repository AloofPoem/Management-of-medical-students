package com.gestionestudiantesmedicina.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "practice")
public class Practice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPractice;

    @ManyToOne
    @JoinColumn(name = "subject")
    private Subject subject;

    @OneToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @ManyToMany(mappedBy = "practices")
    private List<Student> students;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @Column(name = "lounge", nullable = false)
    private String lounge;

    public Practice() {
    }

    public Practice(Long idPractice, Subject subject, Schedule schedule, Teacher teacher, String lounge) {
        this.idPractice = idPractice;
        this.subject = subject;
        this.schedule = schedule;
        this.teacher = teacher;
        this.lounge = lounge;
        this.students = new ArrayList<>();
    }

    public Long getIdPractice() {
        return idPractice;
    }

    public void setIdPractice(Long idPractice) {
        this.idPractice = idPractice;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Practice))return false;
        Practice other = (Practice) o;
        return idPractice != null && idPractice.equals(other.idPractice);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public String getLounge() {
        return lounge;
    }

    public void setLounge(String lounge) {
        this.lounge = lounge;
    }
}