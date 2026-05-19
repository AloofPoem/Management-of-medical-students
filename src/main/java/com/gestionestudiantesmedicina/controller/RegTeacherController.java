package com.gestionestudiantesmedicina.controller;

import com.gestionestudiantesmedicina.App;
import com.gestionestudiantesmedicina.daos.TeacherDAO;
import com.gestionestudiantesmedicina.entities.Teacher;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;

public class RegTeacherController {
    
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
    
    private TeacherDAO teacherDAO = new TeacherDAO();

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

            teacherDAO.save(t);

            showAlert(AlertType.INFORMATION, "Profesor creado", "El profesor se registro de forma correcta");

            App.setRoot("LoginView");

        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Error de Creación", "No se pudo crear el profesor: " + e.getMessage());
        }
    }

    private boolean isInvalid(){
        if (dpBirthDate.getValue() == null) {
            return true;
        }

        TextInputControl[] fields = {txtTeacherId, txtName, txtLastName,txtPassword};

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
