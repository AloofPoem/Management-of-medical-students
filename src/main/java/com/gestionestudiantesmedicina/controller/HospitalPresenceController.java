package com.gestionestudiantesmedicina.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import com.gestionestudiantesmedicina.daos.RecordDAO;
import com.gestionestudiantesmedicina.entities.Person;
import com.gestionestudiantesmedicina.entities.Record;
import com.gestionestudiantesmedicina.entities.Student;
import com.gestionestudiantesmedicina.entities.Teacher;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.beans.property.SimpleStringProperty;

public class HospitalPresenceController {

    @FXML
    private TableView<Record> tablePresence;

    @FXML
    private TableColumn<Record, String> colPersonName;

    @FXML
    private TableColumn<Record, String> colPersonId;

    @FXML
    private TableColumn<Record, String> colRole;

    @FXML
    private TableColumn<Record, LocalDate> colDate;

    @FXML
    private TableColumn<Record, LocalTime> colTimeIn;

    @FXML
    private TableColumn<Record, String> colService;

    @FXML
    private TableColumn<Record, String> colAlert;

    private RecordDAO recordDAO = new RecordDAO();
    private ObservableList<Record> activeList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        colPersonName.setCellValueFactory(cellData -> {
            Person p = cellData.getValue().getPerson();
            if (p instanceof Student) {
                Student s = (Student) p;
                return new SimpleStringProperty(s.getName() + " " + s.getLastName());
            } else if (p instanceof Teacher) {
                Teacher t = (Teacher) p;
                return new SimpleStringProperty(t.getName() + " " + t.getLastName());
            }
            return new SimpleStringProperty("");
        });

        colPersonId.setCellValueFactory(cellData -> {
            Person p = cellData.getValue().getPerson();
            if (p instanceof Student) {
                return new SimpleStringProperty(String.valueOf(((Student) p).getId()));
            } else if (p instanceof Teacher) {
                return new SimpleStringProperty(String.valueOf(((Teacher) p).getId()));
            }
            return new SimpleStringProperty("");
        });

        colRole.setCellValueFactory(cellData -> {
            Person p = cellData.getValue().getPerson();
            if (p instanceof Student) return new SimpleStringProperty("Estudiante");
            if (p instanceof Teacher) return new SimpleStringProperty("Docente");
            return new SimpleStringProperty("");
        });

        colDate.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("date"));
        colTimeIn.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("timeIn"));

        colService.setCellValueFactory(cellData -> {
            Person p = cellData.getValue().getPerson();
            if (p instanceof Student) {
                Student s = (Student) p;
                if (s.getAcademicData() != null) {
                    return new SimpleStringProperty(s.getAcademicData().getAcademicProgram());
                }
            }
            return new SimpleStringProperty("-");
        });

        colAlert.setCellValueFactory(cellData -> {
            Record r = cellData.getValue();

            if (r.getPerson() instanceof Student) {
                StudentDAO studentDAO = new StudentDAO();
                Student student = studentDAO.findByIdWithList(((Student) r.getPerson()).getId(), "schedules");

                if (student.getSchedules() != null && !student.getSchedules().isEmpty()) {
                    boolean expirado = student.getSchedules().stream()
                            .anyMatch(schedule -> schedule.getEndTime().isBefore(LocalTime.now()));
                    if (expirado) {
                        return new SimpleStringProperty("Horario expirado");
                    }
                }
            } else if (r.getPerson() instanceof Teacher) {
                TeacherDAO teacherDAO = new TeacherDAO();
                Teacher teacher = teacherDAO.findByIdWithList(((Teacher) r.getPerson()).getId(), "schedules");

                if (teacher.getSchedules() != null && !teacher.getSchedules().isEmpty()) {
                    boolean expirado = teacher.getSchedules().stream()
                            .anyMatch(schedule -> schedule.getEndTime().isBefore(LocalTime.now()));
                    if (expirado) {
                        return new SimpleStringProperty("Horario expirado");
                    }
                }
            }

            return new SimpleStringProperty("");
        });

        loadActivePresence();
    }

    private void loadActivePresence() {
        List<Record> activeRecords = recordDAO.findAll().stream()
                .filter(r -> r.getTimeIn() != null && r.getTimeOut() == null)
                .collect(Collectors.toList());

        activeList.setAll(activeRecords);
        tablePresence.setItems(activeList);
    }
}
