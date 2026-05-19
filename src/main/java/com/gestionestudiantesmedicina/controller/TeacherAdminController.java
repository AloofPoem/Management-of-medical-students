package com.gestionestudiantesmedicina.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.gestionestudiantesmedicina.daos.TeacherDAO;
import com.gestionestudiantesmedicina.entities.Teacher;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;

public class TeacherAdminController {

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtTeacherId;
    @FXML
    private TextField txtPassword;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtSpecialty;
    @FXML
    private DatePicker dpBirthDate;

    @FXML
    private TextField txtRecordId;

    @FXML
    private TableView<Teacher> tableTeachers;
    @FXML
    private TableColumn<Teacher, Long> colTeacherId;
    @FXML
    private TableColumn<Teacher, String> colName;
    @FXML
    private TableColumn<Teacher, String> colLastName;
    @FXML
    private TableColumn<Teacher, String> colSpecialty;
    @FXML
    private TableColumn<Teacher, LocalDate> colBirthDate;

    private TeacherDAO teacherDAO = new TeacherDAO();

    private ObservableList<Teacher> teacherList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        colTeacherId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colSpecialty.setCellValueFactory(new PropertyValueFactory<>("specialty"));
        colBirthDate.setCellValueFactory(new PropertyValueFactory<>("birtDate"));

        // Mostrar el Record asociado
        // teacher tiene una lista de records, no se pondria
        /*
         * colRecord.setCellValueFactory(cellData -> {
         * Record r = cellData.getValue().getRecord();
         * return new SimpleStringProperty(
         * r != null ? r.getDate() + " " + r.getTimeIn() : ""
         * );
         * });
         * // Mostrar cantidad de estudiantes asociados
         * colStudentCount.setCellValueFactory(cellData -> {
         * Teacher t = cellData.getValue();
         * int count = (t.getStudents() != null) ? t.getStudents().size() : 0;
         * return new SimpleStringProperty(count + " estudiantes");
         * });
         */

        loadTeacherList();

        tableTeachers.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> populateForm(newSelection));
    }

    private void loadTeacherList() {
        teacherList.clear();
        List<Teacher> teachers = teacherDAO.findAll();
        teacherList.addAll(teachers);
        tableTeachers.setItems(teacherList);
    }

    private void populateForm(Teacher teacher) {
        if (teacher != null) {
            txtTeacherId.setText(String.valueOf(teacher.getId()));
            txtPassword.setText(teacher.getPassword());
            txtName.setText(teacher.getName());
            txtLastName.setText(teacher.getLastName());
            txtSpecialty.setText(teacher.getSpecialty());
            dpBirthDate.setValue(teacher.getBirthDate());
            // teacher no tiene un record si no varios
            // if (teacher.getRecord() != null) {
            // txtRecordId.setText(String.valueOf(teacher.getRecord().getIdRecord()));
            // }
        }
    }

    @FXML
    private void handleCreate(ActionEvent event) {
        if (isInvalid()) {
            showAlert(AlertType.ERROR, "Error de Validación", "Todos los campos deben tener datos");
            return;
        }

        try {
            Teacher t = new Teacher();
            t.setId(Long.parseLong(txtTeacherId.getText().trim()));
            t.setPassword(txtPassword.getText());
            t.setName(txtName.getText());
            t.setLastName(txtLastName.getText());
            t.setSpecialty(txtSpecialty.getText());
            t.setBirthDate(dpBirthDate.getValue());
            // no tiene solo uno
            // t.setRecord(record);

            teacherDAO.save(t);
            loadTeacherList();
            handleClear(null);

        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Error de Creación", "No se pudo crear el profesor: " + e.getMessage());
        }
    }

    @FXML
    private void handleUpdate(ActionEvent event) {

        if (isInvalid()) {
            showAlert(AlertType.ERROR, "Error de Validación", "Todos los campos deben tener datos");
            return;
        }

        try {
            Long teacherId = Long.parseLong(txtTeacherId.getText().trim());
            Teacher teacher = teacherDAO.findById(teacherId);

            if (teacher == null) {
                showAlert(AlertType.ERROR, "Validación", "Profesor no encontrado con ID: " + teacherId);
                return;
            }

            teacher.setPassword(txtPassword.getText());
            teacher.setName(txtName.getText());
            teacher.setLastName(txtLastName.getText());
            teacher.setSpecialty(txtSpecialty.getText());
            teacher.setBirthDate(dpBirthDate.getValue());

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
            Long teacherId = Long.parseLong(txtTeacherId.getText().trim());

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
        txtPassword.clear();
        txtName.clear();
        txtLastName.clear();
        txtSpecialty.clear();
        dpBirthDate.setValue(null);
        tableTeachers.getSelectionModel().clearSelection();
        loadTeacherList();
    }

    @FXML
    private void handleSearch(ActionEvent event) {
        try {
            Long id = Long.parseLong(txtSearch.getText().trim());
            Teacher teacher = teacherDAO.findById(id);

            if (teacher != null) {
                populateForm(teacher);
                tableTeachers.getItems().setAll(teacher);
                tableTeachers.getSelectionModel().select(teacher);
            } else {
                showAlert(AlertType.INFORMATION, "Búsqueda", "Profesor no encontrado con ID: " + id);
            }

        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Error de Formato", "El ID debe ser un número.");
        }
    }

    private boolean isInvalid() {
        if (dpBirthDate.getValue() == null) {
            return true;
        }

        TextInputControl[] fields = { txtTeacherId, txtName, txtLastName, txtPassword };

        for (TextInputControl field : fields) {
            if (field == null || field.getText() == null || field.getText().trim().isEmpty()) {
                return true;
            }
        }

        return false;
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
