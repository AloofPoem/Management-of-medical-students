package com.gestionestudiantesmedicina.controller;

import java.util.List;
import java.util.Optional;

import com.gestionestudiantesmedicina.daos.PersonDAO;
import com.gestionestudiantesmedicina.daos.PracticeDAO;
import com.gestionestudiantesmedicina.daos.TeacherDAO;
import com.gestionestudiantesmedicina.daos.SubjectDAO;
import com.gestionestudiantesmedicina.entities.Person;
import com.gestionestudiantesmedicina.entities.Practice;
import com.gestionestudiantesmedicina.entities.Teacher;
import com.gestionestudiantesmedicina.entities.Subject;

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

public class PracticeController {

    @FXML
    private TextField txtPracticeId;
    @FXML
    private TextField txtTeacherId;
    @FXML
    private TextField txtSubjectId;
    @FXML
    private TextField txtSearch;
    @FXML
    private TextField txtLounge;

    @FXML
    private TableView<Practice> tablePractices;
    @FXML
    private TableColumn<Practice, Long> colPracticeId;
    @FXML
    private TableColumn<Practice, String> colTeacher;
    @FXML
    private TableColumn<Practice, String> colSubject;
    @FXML
    private TableColumn<Practice, String> colLounge;

    private PracticeDAO practiceDAO = new PracticeDAO();
    private TeacherDAO teacherDAO = new TeacherDAO();
    private SubjectDAO subjectDAO = new SubjectDAO();
    
    private ObservableList<Practice> practiceList = FXCollections.observableArrayList();
    
    private Long id;
    private Person person;

    public void setId(Long idP){
        this.id = idP;
        PersonDAO personDAO = new PersonDAO();
        person = personDAO.findById(idP);
        loadPracticeList();
    }

    @FXML
    private void initialize() {
        colPracticeId.setCellValueFactory(new PropertyValueFactory<>("idPractice"));
        colLounge.setCellValueFactory(new PropertyValueFactory<>("lounge"));
        
        // Mostrar nombre del Teacher asociado
        colTeacher.setCellValueFactory(cellData -> {
            Teacher t = cellData.getValue().getTeacher();
            return new SimpleStringProperty(t != null ? t.getName() + " " + t.getLastName() : "");
        });

        // Mostrar nombre del Subject asociado
        colSubject.setCellValueFactory(cellData -> {
            Subject s = cellData.getValue().getSubject();
            return new SimpleStringProperty(s != null ? s.getNameSubject() : "");
        });

        //loadPracticeList();

        tablePractices.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> populateForm(newSelection)
        );
    }

    private void loadPracticeList() {
        practiceList.clear();
        List<Practice> practices;
        if (person instanceof Teacher) {
            practices = practiceDAO.findByAttribute("teacher.id", id);
        } else {
            practices = practiceDAO.findByStudentId(id);
        }
        practiceList.addAll(practices);
        tablePractices.setItems(practiceList);
    }

    private void populateForm(Practice practice) {
        if (practice != null) {
            txtPracticeId.setText(String.valueOf(practice.getIdPractice()));
            if (practice.getTeacher() != null) {
                txtTeacherId.setText(String.valueOf(practice.getTeacher().getId()));
            }
            if (practice.getSubject() != null) {
                txtSubjectId.setText(String.valueOf(practice.getSubject().getIdSubject()));
            }
            if (practice.getLounge() != null) {
                txtLounge.setText(practice.getLounge());
            }
        }
    }

    @FXML
    private void handleCreate(ActionEvent event) {
        try {
            Teacher teacher = teacherDAO.findById(Long.parseLong(txtTeacherId.getText()));
            Subject subject = subjectDAO.findById(Long.parseLong(txtSubjectId.getText()));
            String lounge = txtLounge.getText();

            Practice p = new Practice();
            p.setTeacher(teacher);
            p.setSubject(subject);
            p.setLounge(lounge);

            practiceDAO.save(p);
            loadPracticeList();
            handleClear(null);

        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Error de Creación", "No se pudo crear la práctica: " + e.getMessage());
        }
    }

    @FXML
    private void handleUpdate(ActionEvent event) {
        try {
            Long practiceId = Long.parseLong(txtPracticeId.getText().trim());
            Practice practice = practiceDAO.findById(practiceId);
            String lounge = txtLounge.getText();


            if (practice == null) {
                showAlert(AlertType.ERROR, "Validación", "Práctica no encontrada con ID: " + practiceId);
                return;
            }

            Teacher teacher = teacherDAO.findById(Long.parseLong(txtTeacherId.getText()));
            Subject subject = subjectDAO.findById(Long.parseLong(txtSubjectId.getText()));

            practice.setTeacher(teacher);
            practice.setSubject(subject);
            practice.setLounge(lounge);

            practiceDAO.update(practice);
            loadPracticeList();
            handleClear(null);

        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Error de Actualización", "No se pudo actualizar: " + e.getMessage());
        }
    }

    @FXML
    private void handleDelete(ActionEvent event) {
        try {
            Long practiceId = Long.parseLong(txtPracticeId.getText().trim());

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmar Eliminación");
            alert.setHeaderText("¿Está seguro de que desea eliminar la práctica con ID " + practiceId + "?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                practiceDAO.delete(practiceId);
                loadPracticeList();
                handleClear(null);
            }
        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Error de Eliminación", "No se pudo eliminar: " + e.getMessage());
        }
    }

    @FXML
    private void handleClear(ActionEvent event) {
        txtPracticeId.clear();
        txtTeacherId.clear();
        txtSubjectId.clear();
        txtLounge.clear();
        tablePractices.getSelectionModel().clearSelection();
        loadPracticeList();
    }

    @FXML
    private void handleSearch(ActionEvent event) {
        try {
            Long id = Long.parseLong(txtSearch.getText().trim());
            Practice practice = practiceDAO.findById(id);

            if (practice != null) {
                populateForm(practice);
                tablePractices.getItems().setAll(practice);
                tablePractices.getSelectionModel().select(practice);
            } else {
                showAlert(AlertType.INFORMATION, "Búsqueda", "Clase no encontrado con ID: " + id);
            }

        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Error de Formato", "El ID debe ser un número.");
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