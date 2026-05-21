package com.gestionestudiantesmedicina.controller;

import java.util.List;
import java.util.Optional;

import com.gestionestudiantesmedicina.daos.TeacherDAO;
import com.gestionestudiantesmedicina.daos.RecordDAO;
import com.gestionestudiantesmedicina.daos.ScheduleDAO;
import com.gestionestudiantesmedicina.daos.PracticeDAO;

import com.gestionestudiantesmedicina.entities.Teacher;
import com.gestionestudiantesmedicina.entities.Record;
import com.gestionestudiantesmedicina.entities.Schedule;
import com.gestionestudiantesmedicina.entities.Practice;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;

public class TeacherController {

    @FXML
    private TextField txtTeacherId;
    @FXML
    private TextField txtNames;
    @FXML
    private TextField txtLastNames;
    @FXML
    private TextField txtSpecialty;

    @FXML
    private TableView<Teacher> tableTeachers;
    @FXML
    private TableColumn<Teacher, Integer> colTeacherId;
    @FXML
    private TableColumn<Teacher, String> colNames;
    @FXML
    private TableColumn<Teacher, String> colLastNames;
    @FXML
    private TableColumn<Teacher, String> colSpecialty;

    // Relaciones
    @FXML
    private TableColumn<Teacher, String> colRecords;
    @FXML
    private TableColumn<Teacher, String> colSchedules;
    @FXML
    private TableColumn<Teacher, String> colPractices;
    @FXML
    private TableColumn<Teacher, String> colStudentsFromPractice;



    private TeacherDAO teacherDAO = new TeacherDAO();
    private ObservableList<Teacher> teacherList = FXCollections.observableArrayList();

    @FXML
    @FXML
    private void initialize() {
        colTeacherId.setCellValueFactory(new PropertyValueFactory<>("idTeacher"));
        colNames.setCellValueFactory(new PropertyValueFactory<>("names"));
        colLastNames.setCellValueFactory(new PropertyValueFactory<>("lastNames"));
        colSpecialty.setCellValueFactory(new PropertyValueFactory<>("specialty"));

        // Records
        colRecords.setCellValueFactory(cellData -> {
            Teacher t = cellData.getValue();
            int count = (t.getRecords() != null) ? t.getRecords().size() : 0;
            return new SimpleStringProperty(count + " records");
        });

        // Schedules
        colSchedules.setCellValueFactory(cellData -> {
            Teacher t = cellData.getValue();
            int count = (t.getSchedules() != null) ? t.getSchedules().size() : 0;
            return new SimpleStringProperty(count + " schedules");
        });

        // Practices
        colPractices.setCellValueFactory(cellData -> {
            Teacher t = cellData.getValue();
            int count = (t.getPractices() != null) ? t.getPractices().size() : 0;
            return new SimpleStringProperty(count + " practices");
        });

        // Estudiantes a través de Practices
        colStudentsFromPractice.setCellValueFactory(cellData -> {
            Teacher t = cellData.getValue();
            int count = 0;
            if (t.getPractices() != null) {
                count = (int) t.getPractices().stream()
                        .flatMap(p -> p.getStudents().stream()) // cada práctica tiene estudiantes
                        .filter(s -> s != null)
                        .distinct()
                        .count();
            }
            return new SimpleStringProperty(count + " estudiantes");
        });

        loadTeacherList();

        tableTeachers.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> populateForm(newSelection)
        );
    }

    private void loadTeacherList() {
        teacherList.clear();
        List<Teacher> teachers = teacherDAO.findAll();
        teacherList.addAll(teachers);
        tableTeachers.setItems(teacherList);
    }

    private void populateForm(Teacher teacher) {
        if (teacher != null) {
            txtTeacherId.setText(String.valueOf(teacher.getIdTeacher()));
            txtNames.setText(teacher.getNames());
            txtLastNames.setText(teacher.getLastNames());
            txtSpecialty.setText(teacher.getSpecialty());
        }
    }

    @FXML
    private void handleCreate(ActionEvent event) {
        try {
            Teacher t = new Teacher();
            t.setNames(txtNames.getText());
            t.setLastNames(txtLastNames.getText());
            t.setSpecialty(txtSpecialty.getText());

            teacherDAO.save(t);
            loadTeacherList();
            handleClear(null);

        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Error de Creación", "No se pudo crear el profesor: " + e.getMessage());
        }
    }

    @FXML
    private void handleUpdate(ActionEvent event) {
        try {
            Integer teacherId = Integer.parseInt(txtTeacherId.getText().trim());
            Teacher teacher = teacherDAO.findById(teacherId);

            if (teacher == null) {
                showAlert(AlertType.ERROR, "Validación", "Profesor no encontrado con ID: " + teacherId);
                return;
            }

            teacher.setNames(txtNames.getText());
            teacher.setLastNames(txtLastNames.getText());
            teacher.setSpecialty(txtSpecialty.getText());

            teacherDAO.update(teacher);
            loadTeacherList();
            handleClear(null);

        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Error de Actualización", "No se pudo actualizar: " + e.getMessage());
        }
    }

    @FXML
    private void handleDelete(ActionEvent event) {
        try {
            Integer teacherId = Integer.parseInt(txtTeacherId.getText().trim());

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmar Eliminación");
            alert.setHeaderText("¿Está seguro de que desea eliminar el profesor con ID " + teacherId + "?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                teacherDAO.delete(teacherId);
                loadTeacherList();
                handleClear(null);
            }
        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Error de Eliminación", "No se pudo eliminar: " + e.getMessage());
        }
    }

    @FXML
    private void handleClear(ActionEvent event) {
        txtTeacherId.clear();
        txtNames.clear();
        txtLastNames.clear();
        txtSpecialty.clear();
        tableTeachers.getSelectionModel().clearSelection();
        loadTeacherList();
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}