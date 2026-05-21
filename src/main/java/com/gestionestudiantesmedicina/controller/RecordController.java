package com.gestionestudiantesmedicina.controller;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import com.gestionestudiantesmedicina.daos.RecordDAO;
import com.gestionestudiantesmedicina.daos.TeacherDAO;
import com.gestionestudiantesmedicina.daos.StudentDAO;
import com.gestionestudiantesmedicina.entities.Record;
import com.gestionestudiantesmedicina.entities.Teacher;
import com.gestionestudiantesmedicina.entities.Student;

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
import javafx.scene.control.cell.PropertyValueFactory;

public class RecordController {

    @FXML
    private TextField txtRecordId;
    @FXML
    private DatePicker dpDate;
    @FXML
    private TextField txtTimeIn;
    @FXML
    private TextField txtTimeOut;
    @FXML
    private TextField txtTeacherId;
    @FXML
    private TextField txtStudentId;
    @FXML
    private TextField txtSearch;

    @FXML
    private TableView<Record> tableRecords;
    @FXML
    private TableColumn<Record, Long> colRecordId;
    @FXML
    private TableColumn<Record, String> colDate;
    @FXML
    private TableColumn<Record, String> colTimeIn;
    @FXML
    private TableColumn<Record, String> colTimeOut;
    @FXML
    private TableColumn<Record, String> colTeacher;
    @FXML
    private TableColumn<Record, String> colStudent;

    private RecordDAO recordDAO = new RecordDAO();
    private TeacherDAO teacherDAO = new TeacherDAO();
    private StudentDAO studentDAO = new StudentDAO();

    private ObservableList<Record> recordList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        colRecordId.setCellValueFactory(new PropertyValueFactory<>("idRecord"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTimeIn.setCellValueFactory(new PropertyValueFactory<>("timeIn"));
        colTimeOut.setCellValueFactory(new PropertyValueFactory<>("timeOut"));

        // Mostrar nombre del Teacher
        colTeacher.setCellValueFactory(cellData -> {
            Teacher t = cellData.getValue().getTeacher();
            return new javafx.beans.property.SimpleStringProperty(
                    t != null ? t.getNames() + " " + t.getLastNames() : ""
            );
        });

        // Mostrar nombre del Student
        colStudent.setCellValueFactory(cellData -> {
            Student s = cellData.getValue().getStudent();
            return new javafx.beans.property.SimpleStringProperty(
                    s != null ? s.getNames() + " " + s.getLastNames() : ""
            );
        });

        loadRecordList();

        tableRecords.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        populateForm(newSelection);
                    }
                }
        );
    }

    private void loadRecordList() {
        recordList.clear();
        List<Record> records = recordDAO.findAll();
        recordList.addAll(records);
        tableRecords.setItems(recordList);
    }

    private void populateForm(Record r) {
        txtRecordId.setText(String.valueOf(r.getIdRecord()));
        dpDate.setValue(r.getDate());
        if (r.getTimeIn() != null) txtTimeIn.setText(r.getTimeIn().toString());
        if (r.getTimeOut() != null) txtTimeOut.setText(r.getTimeOut().toString());
        if (r.getTeacher() != null) txtTeacherId.setText(String.valueOf(r.getTeacher().getIdTeacher()));
        if (r.getStudent() != null) txtStudentId.setText(String.valueOf(r.getStudent().getIdStudent()));
    }

    @FXML
    private void handleClear(ActionEvent event) {
        txtRecordId.clear();
        dpDate.setValue(null);
        txtTimeIn.clear();
        txtTimeOut.clear();
        txtTeacherId.clear();
        txtStudentId.clear();
        tableRecords.getSelectionModel().clearSelection();
        loadRecordList();
    }

    @FXML
    private void handleCreate(ActionEvent event) {
        try {
            Teacher teacher = teacherDAO.findById(Long.parseLong(txtTeacherId.getText()));
            Student student = studentDAO.findById(Long.parseLong(txtStudentId.getText()));

            Record record = new Record();
            record.setDate(dpDate.getValue());
            record.setTimeIn(LocalTime.parse(txtTimeIn.getText().trim()));
            record.setTimeOut(LocalTime.parse(txtTimeOut.getText().trim()));
            record.setTeacher(teacher);
            record.setStudent(student);

            recordDAO.save(record);
            loadRecordList();
            handleClear(null);

            showAlert(AlertType.INFORMATION, "Creación Exitosa", "Registro creado correctamente.");
        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Error de Creación", "No se pudo crear el registro: " + e.getMessage());
        }
    }

    @FXML
    private void handleUpdate(ActionEvent event) {
        try {
            Long recId = Long.parseLong(txtRecordId.getText().trim());
            Record record = recordDAO.findById(recId);

            if (record == null) {
                showAlert(AlertType.ERROR, "Validación", "Registro no encontrado con ID: " + recId);
                return;
            }

            record.setDate(dpDate.getValue());
            record.setTimeIn(LocalTime.parse(txtTimeIn.getText().trim()));
            record.setTimeOut(LocalTime.parse(txtTimeOut.getText().trim()));

            Teacher teacher = teacherDAO.findById(Long.parseLong(txtTeacherId.getText()));
            Student student = studentDAO.findById(Long.parseLong(txtStudentId.getText()));
            record.setTeacher(teacher);
            record.setStudent(student);

            recordDAO.update(record);
            loadRecordList();
            handleClear(null);

            showAlert(AlertType.INFORMATION, "Actualización Exitosa", "Registro actualizado correctamente.");
        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Error de Actualización", "No se pudo actualizar: " + e.getMessage());
        }
    }

    @FXML
    private void handleDelete(ActionEvent event) {
        try {
            Long recId = Long.parseLong(txtRecordId.getText().trim());

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmar Eliminación");
            alert.setHeaderText("¿Está seguro de que desea eliminar el registro con ID " + recId + "?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                recordDAO.delete(recId);
                loadRecordList();
                handleClear(null);
                showAlert(AlertType.INFORMATION, "Eliminación Exitosa", "Registro eliminado correctamente.");
            }
        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Error de Eliminación", "No se pudo eliminar: " + e.getMessage());
        }
    }

    @FXML
    private void handleSearch(ActionEvent event) {
        try {
            Long recId = Long.parseLong(txtSearch.getText().trim());
            Record record = recordDAO.findById(recId);

            if (record != null) {
                populateForm(record);
                tableRecords.getItems().setAll(record);
                tableRecords.getSelectionModel().select(record);
            } else {
                showAlert(AlertType.INFORMATION, "Búsqueda", "Registro no encontrado con ID: " + recId);
            }
        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Error de Búsqueda", "No se pudo buscar: " + e.getMessage());
        }
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}