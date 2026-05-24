package com.gestionestudiantesmedicina.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.gestionestudiantesmedicina.daos.PersonDAO;
import com.gestionestudiantesmedicina.daos.RecordDAO;
import com.gestionestudiantesmedicina.daos.ScheduleDAO;
import com.gestionestudiantesmedicina.daos.StudentDAO;
import com.gestionestudiantesmedicina.entities.Admin;
import com.gestionestudiantesmedicina.entities.Person;
import com.gestionestudiantesmedicina.entities.Record;
import com.gestionestudiantesmedicina.entities.Schedule;
import com.gestionestudiantesmedicina.entities.Student;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
    private TextField txtPersonId;
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
    private TableColumn<Record, String> colPerson;

    private RecordDAO recordDAO = new RecordDAO();
    private PersonDAO personDAO = new PersonDAO();

    private ObservableList<Record> recordList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        colRecordId.setCellValueFactory(new PropertyValueFactory<>("idRecord"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTimeIn.setCellValueFactory(new PropertyValueFactory<>("timeIn"));
        colTimeOut.setCellValueFactory(new PropertyValueFactory<>("timeOut"));

        // Mostrar nombre del Teacher
        colPerson.setCellValueFactory(cellData -> {
            Person t = cellData.getValue().getPerson();
            return new SimpleStringProperty(t != null ? t.getName() + " " + t.getLastName() : "");
        });

        loadRecordList();

        tableRecords.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        populateForm(newSelection);
                    }
                });
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
        if (r.getTimeIn() != null)
            txtTimeIn.setText(r.getTimeIn().toString());
        if (r.getTimeOut() != null)
            txtTimeOut.setText(r.getTimeOut().toString());
        if (r.getPerson() != null)
            txtPersonId.setText(String.valueOf(r.getPerson().getId()));
    }

    @FXML
    private void handleClear(ActionEvent event) {
        txtRecordId.clear();
        dpDate.setValue(null);
        txtTimeIn.clear();
        txtTimeOut.clear();
        txtPersonId.clear();
        tableRecords.getSelectionModel().clearSelection();
        loadRecordList();
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

    @FXML
    private void handleCheckInOut(ActionEvent event) {
        try {

            Long personId = Long.parseLong(txtPersonId.getText());
            Person p = personDAO.findById(personId);

            if (p == null || p instanceof Admin) {
                showAlert(AlertType.ERROR, "Validación", "El ID " + personId + " no está asociado ");
                return;
            }

            Record lastRecord = recordDAO.findLastByPersonId(personId);

            if (lastRecord == null || lastRecord.getTimeOut() != null) {

                Schedule schedule = veryfySchedule(p, personId);

                if (schedule == null) {
                    showAlert(AlertType.INFORMATION, "Acceso Denegado", "No puede acceder fuera de su horario");
                    return;
                }

                Record newRecord = new Record();
                newRecord.setDate(LocalDate.now());
                newRecord.setTimeIn(LocalTime.now());

                newRecord.setPerson(p);

                recordDAO.save(newRecord);
                showAlert(AlertType.INFORMATION, "Entrada registrada", "Se registró la entrada para ID " + personId);

            } else {
                // Caso salida
                lastRecord.setTimeOut(LocalTime.now());
                recordDAO.update(lastRecord);
                p.updateTotalHours(lastRecord);
                personDAO.update(p);

                showAlert(AlertType.INFORMATION, "Salida registrada", "Se registró la salida para ID " + personId);
            }

            loadRecordList();

        } catch (NumberFormatException e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Error de Formato", "El ID debe ser numérico.");
        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Error", "No se pudo registrar entrada/salida: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private Schedule veryfySchedule(Person p, Long personId) {

        ScheduleDAO scheduleDAO = new ScheduleDAO();

        List<Schedule> schedules;
        if (p instanceof Student) {
            StudentDAO studentDAO = new StudentDAO();
            Student student = studentDAO.findByIdWithList(personId, "schedules");
            schedules = student.getSchedules();
            // schedules = scheduleDAO.findByStudentId(personId);
        } else {
            schedules = scheduleDAO.findByAttribute("teacher.id", personId);
        }

        if (schedules == null || schedules.isEmpty()) {
            return null;
        }

        LocalTime nowTime = LocalTime.now();
        LocalDate nowDate = LocalDate.now();
        for (Schedule schedule : schedules) {
            if (schedule.getDate().equals(nowDate)) {
                if (schedule.getStartTime().isBefore(nowTime)
                        || schedule.getStartTime().equals(nowTime) && schedule.getEndTime().isAfter(nowTime)) {
                    return schedule;
                }
            }
        }

        return null;
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}