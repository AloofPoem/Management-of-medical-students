package com.gestionestudiantesmedicina.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import com.gestionestudiantesmedicina.daos.RecordDAO;
import com.gestionestudiantesmedicina.daos.StudentDAO;
import com.gestionestudiantesmedicina.daos.TeacherDAO;
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
            return new SimpleStringProperty(p.getName() + " " + p.getLastName());
        });

        colPersonId.setCellValueFactory(cellData -> {
            Person p = cellData.getValue().getPerson();
            return new SimpleStringProperty(String.valueOf((p.getId())));
        });

        colRole.setCellValueFactory(cellData -> {
            Person p = cellData.getValue().getPerson();
            if (p instanceof Student)
                return new SimpleStringProperty("Estudiante");
            if (p instanceof Teacher)
                return new SimpleStringProperty("Docente");
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
            } else {
                Teacher t = (Teacher) p;
                return new SimpleStringProperty(t.getSpecialty());
            }

            return new SimpleStringProperty("-");
        });

        colAlert.setCellValueFactory(cellData -> {
            Record r = cellData.getValue();

            if (r.getPerson() instanceof Student) {
                StudentDAO studentDAO = new StudentDAO();
                Student student = studentDAO.findByIdWithList(((Student) r.getPerson()).getId(), "schedules");

                if (student != null && student.getSchedules() != null && !student.getSchedules().isEmpty()) {
                    boolean expirado = student.getSchedules().stream()
                            .anyMatch(schedule -> schedule.getEndTime().isBefore(LocalTime.now()));
                    if (expirado) {
                        return new SimpleStringProperty("Horario expirado");
                    }
                }
            } else if (r.getPerson() instanceof Teacher) {
                TeacherDAO teacherDAO = new TeacherDAO(); // Instanciamos el DAO que acabas de corregir
                Teacher teacher = teacherDAO.findByIdWithList(((Teacher) r.getPerson()).getId(), "schedules");

                if (teacher != null && teacher.getSchedules() != null && !teacher.getSchedules().isEmpty()) {
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
        activeList.clear();
        List<Record> activeRecords = recordDAO.findAll().stream()
                .filter(r -> r.getTimeIn() != null && r.getTimeOut() == null)
                .collect(Collectors.toList());

        activeList.addAll(activeRecords);
        // activeList.setAll(activeRecords);
        tablePresence.setItems(activeList);
    }
}
