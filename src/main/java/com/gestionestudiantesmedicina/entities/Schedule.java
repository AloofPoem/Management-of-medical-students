package com.gestionestudiantesmedicina.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSchedule;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = true)
    private Teacher teacher;

    @ManyToMany(mappedBy = "schedules", fetch = FetchType.EAGER)
    private List<Student> students;

    public Schedule() {
    }

    public Schedule(LocalDate date, LocalTime startTime, LocalTime endTime, Teacher teacher) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.teacher = teacher;
        this.students = new ArrayList<>();
    }

    public Long getidSchedule() {
        return idSchedule;
    }

    public void setidSchedule(Long idSchedule) {
        idSchedule = idSchedule;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Schedule))
            return false;
        Schedule other = (Schedule) o;
        return idSchedule != null && idSchedule.equals(other.getidSchedule());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}