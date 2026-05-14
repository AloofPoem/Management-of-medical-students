package com.gestionestudiantesmedicina.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "class_entity")
public class Practice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPractice;

    @OneToMany(mappedBy = "ptactice", cascade = CascadeType.ALL)
    private List<Subject> subjects;

    @OneToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @ManyToMany(mappedBy = "practices")
    private List<Student> students;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    public Practice() {}

    public Practice(Long idPractice, List<Subject> subjects, Schedule schedule, List<Student> students, Teacher teacher) {
        this.idPractice = idPractice;
        this.subjects = subjects;
        this.schedule = schedule;
        this.students = students;
        this.teacher = teacher;
    }

    public Long getIdPractice() {
        return idPractice;
    }

    public void setIdPractice(Long idPractice) {
        this.idPractice = idPractice;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
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
}