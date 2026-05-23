package com.gestionestudiantesmedicina.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import com.gestionestudiantesmedicina.daos.PersonDAO;
import com.gestionestudiantesmedicina.daos.ScheduleDAO;
import com.gestionestudiantesmedicina.daos.StudentDAO;
import com.gestionestudiantesmedicina.entities.Admin;
import com.gestionestudiantesmedicina.entities.Person;
import com.gestionestudiantesmedicina.entities.Schedule;
import com.gestionestudiantesmedicina.entities.Student;
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

public class ScheduleController {

        @FXML
        private TextField txtScheduleId;

        @FXML
        private DatePicker dpDate;

        @FXML
        private TextField txtStartTime;

        @FXML
        private TextField txtEndTime;

        @FXML
        private TextField txtSearch;

        @FXML
        private TextField txtPersonId;

        @FXML
        private TableView<Schedule> tableSchedule;

        @FXML
        private TableColumn<Schedule, Long> colScheduleId;

        @FXML
        private TableColumn<Schedule, LocalDate> colDate;

        @FXML
        private TableColumn<Schedule, LocalTime> colStartTime;

        @FXML
        private TableColumn<Schedule, LocalTime> colEndTime;

        private ScheduleDAO scheduleDAO = new ScheduleDAO();

        private ObservableList<Schedule> scheduleList = FXCollections.observableArrayList();

        @FXML
        private void initialize() {

                colScheduleId.setCellValueFactory(new PropertyValueFactory<>("idSchedule"));

                colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

                colStartTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));

                colEndTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));

                loadScheduleList();

                tableSchedule.getSelectionModel().selectedItemProperty().addListener(
                                (obs, oldSelection, newSelection) -> {

                                        if (newSelection != null) {

                                                populateForm(newSelection);
                                        }
                                });
        }

        private void loadScheduleList() {

                scheduleList.clear();

                List<Schedule> schedules = scheduleDAO.findAll();

                scheduleList.addAll(schedules);

                tableSchedule.setItems(scheduleList);
        }

        private void populateForm(Schedule schedule) {

                txtScheduleId.setText(String.valueOf(schedule.getidSchedule()));

                dpDate.setValue(schedule.getDate());

                txtStartTime.setText(String.valueOf(schedule.getStartTime()));

                txtEndTime.setText(String.valueOf(schedule.getEndTime()));
        }

        @FXML
        private void handleClear(ActionEvent event) {

                txtScheduleId.clear();

                dpDate.setValue(null);

                txtStartTime.clear();

                txtEndTime.clear();

                tableSchedule.getSelectionModel().clearSelection();

                loadScheduleList();
        }

        @FXML
        private void handleCreate(ActionEvent event) {

                try {

                        LocalDate date = dpDate.getValue();

                        LocalTime startTime = LocalTime.parse(txtStartTime.getText().trim());

                        LocalTime endTime = LocalTime.parse(txtEndTime.getText().trim());

                        Schedule schedule = new Schedule(date, startTime, endTime, null);

                        scheduleDAO.save(schedule);

                        loadScheduleList();

                        handleClear(null);

                        showAlert(AlertType.INFORMATION, "Creación Exitosa", "Horario creado correctamente.");

                } catch (Exception e) {
                        showAlert(AlertType.ERROR, "Error de Creación",
                                        "No se pudo crear el horario: " + e.getMessage());
                        e.printStackTrace();
                }
        }

        @FXML
        private void handleUpdate(ActionEvent event) {

                try {

                        Long scheduleId = Long.parseLong(txtScheduleId.getText().trim());

                        Schedule schedule = scheduleDAO.findById(scheduleId);

                        if (schedule == null) {

                                showAlert(AlertType.ERROR, "Validación", "Horario no encontrado.");

                                return;
                        }

                        schedule.setDate(dpDate.getValue());

                        schedule.setStartTime(LocalTime.parse(txtStartTime.getText()));

                        schedule.setEndTime(LocalTime.parse(txtEndTime.getText()));

                        scheduleDAO.update(schedule);

                        loadScheduleList();

                        handleClear(null);

                        showAlert(AlertType.INFORMATION, "Actualización Exitosa", "Horario actualizado correctamente.");

                } catch (NumberFormatException e) {

                        showAlert(AlertType.ERROR, "Error de Formato", "El ID debe ser numérico.");

                } catch (Exception e) {

                        showAlert(AlertType.ERROR, "Error de Actualización",
                                        "No se pudo actualizar: " + e.getMessage());
                }
        }

        @FXML
        private void handleDelete(ActionEvent event) {

                try {

                        Long scheduleId = Long.parseLong(txtScheduleId.getText().trim());

                        Alert alert = new Alert(AlertType.CONFIRMATION);

                        alert.setTitle("Confirmar Eliminación");

                        alert.setHeaderText("¿Está seguro de eliminar este horario?");

                        Optional<ButtonType> result = alert.showAndWait();

                        if (result.isPresent() && result.get() == ButtonType.OK) {

                                scheduleDAO.delete(scheduleId);

                                loadScheduleList();

                                handleClear(null);

                                showAlert(AlertType.INFORMATION, "Eliminación Exitosa",
                                                "Horario eliminado correctamente.");
                        }

                } catch (NumberFormatException e) {
                        showAlert(AlertType.ERROR, "Error de Formato", "El ID debe ser numérico.");

                } catch (Exception e) {
                        showAlert(AlertType.ERROR, "Error de Eliminación", "No se pudo eliminar: " + e.getMessage());
                }
        }

        @FXML
        private void handleSearch(ActionEvent event) {

                try {

                        Long scheduleId = Long.parseLong(txtSearch.getText().trim());

                        Schedule schedule = scheduleDAO.findById(scheduleId);

                        if (schedule != null) {

                                populateForm(schedule);

                                tableSchedule.getItems().setAll(schedule);

                                tableSchedule.getSelectionModel().select(schedule);

                        } else {

                                showAlert(AlertType.INFORMATION, "Búsqueda", "Horario no encontrado.");
                        }

                } catch (NumberFormatException e) {

                        showAlert(AlertType.ERROR, "Error de Formato", "El ID debe ser numérico.");
                }
        }

        private void showAlert(AlertType alertType, String title, String message) {

                Alert alert = new Alert(alertType);

                alert.setTitle(title);

                alert.setHeaderText(null);

                alert.setContentText(message);

                alert.showAndWait();
        }

        @FXML
        private void addPerson(ActionEvent event) {

                try {

                        if (txtPersonId.getText() == null || txtPersonId.getText().trim().isEmpty()
                                        || txtScheduleId.getText() == null
                                        || txtScheduleId.getText().trim().isEmpty()) {
                                showAlert(AlertType.WARNING, "Campos Vacíos", "Por favor, complete ambos IDs.");
                                return;
                        }

                        Schedule schedule = scheduleDAO.findById(Long.parseLong(txtScheduleId.getText()));

                        if (schedule == null) {
                                showAlert(AlertType.ERROR, "Validación", "Horario no encontrado.");
                                return;
                        }

                        PersonDAO personDAO = new PersonDAO();
                        Person person = personDAO.findById(Long.parseLong(txtPersonId.getText()));

                        if (person == null || person instanceof Admin) {
                                showAlert(AlertType.ERROR, "Validación", "Persona no valida.");
                                return;
                        }
                        if (person instanceof Teacher) {
                                Teacher teacher = (Teacher) person;
                                schedule.setTeacher(teacher);
                                scheduleDAO.update(schedule);
                                showAlert(AlertType.INFORMATION, "Éxito",
                                                "Profesor asignado al horario correctamente.");
                                return;
                        }

                        if (person instanceof Student) {
                                StudentDAO studentDAO = new StudentDAO();
                                Student student = studentDAO
                                                .findByIdWithSchedules(Long.parseLong(txtPersonId.getText()));
                                if (student.getSchedules().contains(schedule)) {
                                        showAlert(AlertType.WARNING, "Validación",
                                                        "El estudiante ya está inscrito en este horario.");
                                        return;
                                }
                                schedule.getStudents().add(student);
                                scheduleDAO.update(schedule);

                                student.getSchedules().add(schedule);
                                studentDAO.update(student);

                                showAlert(AlertType.INFORMATION, "Éxito",
                                                "Estudiante agregado al horario correctamente.");
                                return;
                        }

                } catch (NumberFormatException e) {
                        showAlert(AlertType.ERROR, "Error de Formato", "El ID debe ser numérico.");
                } catch (Exception e) {
                        e.printStackTrace();
                        showAlert(AlertType.ERROR, "Error", "Ocurrió un error inesperado al guardar los datos.");
                }
        }
}