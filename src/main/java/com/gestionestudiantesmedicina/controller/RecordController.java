package com.gestionestudiantesmedicina.controller;

import java.util.List;
import java.util.Optional;

import com.gestionestudiantesmedicina.daos.RecordDAO;
import com.gestionestudiantesmedicina.daos.ScheduleDAO;
import com.gestionestudiantesmedicina.daos.TeacherDAO;
import com.gestionestudiantesmedicina.entities.Record;
import com.gestionestudiantesmedicina.entities.Schedule;
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
    private TextField txtScheduleId;
    
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
    private TableColumn<Record, String> colSchedule;
    
    //@FXML
    //private TableColumn<Record, String> colStudent;

    private RecordDAO recordDAO = new RecordDAO();
    private TeacherDAO teacherDAO = new TeacherDAO();
    private ScheduleDAO scheduleDAO = new ScheduleDAO();

    private ObservableList<Record> recordList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        colRecordId.setCellValueFactory(new PropertyValueFactory<>("idRecord"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTimeIn.setCellValueFactory(new PropertyValueFactory<>("timeIn"));
        colTimeOut.setCellValueFactory(new PropertyValueFactory<>("timeOut"));

        /*
        // Mostrar nombre del Teacher3
        colTeacher.setCellValueFactory(cellData -> {
            Teacher t = cellData.getValue().getTeacher();
            return new javafx.beans.property.SimpleStringProperty(
                    t != null ? t.getName() + " " + t.getLastName() : ""
            );
        });
        */

        //schedule no tiene profesor, solo va unido a record
        /* 
        colStudent.setCellValueFactory(cellData -> {
            Schedule s = cellData.getValue().getSchedule();
            Teacher t = (s != null) ? s.getTeacher() : null;

            int count = (t != null && t.getStudents() != null)
                    ? t.getStudents().size()
                    : 0;

            return new javafx.beans.property.SimpleStringProperty(count + " estudiantes");
        });
        */

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
        txtTimeIn.setText(r.getTimeIn().toString());
        txtTimeOut.setText(r.getTimeOut().toString());
        //if (r.getTeacher() != null) txtTeacherId.setText(String.valueOf(r.getTeacher().getId()));
        //if (r.getSchedule() != null) txtScheduleId.setText(String.valueOf(r.getSchedule().getIdSchedule()));
    }

    @FXML
    private void handleClear(ActionEvent event) {
        txtRecordId.clear();
        dpDate.setValue(null);;
        txtTimeIn.clear();
        txtTimeOut.clear();
        txtTeacherId.clear();
        txtScheduleId.clear();
        tableRecords.getSelectionModel().clearSelection();
        loadRecordList();
    }

    @FXML
    private void handleCreate(ActionEvent event) {
        try {
            Teacher teacher = teacherDAO.findById(Long.parseLong(txtTeacherId.getText()));
            Schedule schedule = scheduleDAO.findById(Long.parseLong(txtScheduleId.getText()));

            Record record = new Record();
            //record.setDate(java.sql.Date.valueOf(dpDate.getValue()));
           // record.setTimeIn(java.sql.Time.valueOf(txtTimeIn.getText()));
            //record.setTimeOut(java.sql.Time.valueOf(txtTimeOut.getText()));
            //record.setTeacher(teacher);
            //record.setSchedule(schedule);

            recordDAO.save(record);
            loadRecordList();
            handleClear(null);

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

            //record.setDate(java.sql.Date.valueOf(dpDate.getValue()));
            //record.setTimeIn(java.sql.Time.valueOf(txtTimeIn.getText()));
            //record.setTimeOut(java.sql.Time.valueOf(txtTimeOut.getText()));

            Teacher teacher = teacherDAO.findById(Long.parseLong(txtTeacherId.getText()));
            Schedule schedule = scheduleDAO.findById(Long.parseLong(txtScheduleId.getText()));
            //record.setTeacher(teacher);
           // record.setSchedule(schedule);

            recordDAO.update(record);
            loadRecordList();
            handleClear(null);

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
