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

public class InfoTeacherController {
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
    private Long idTeacher;

    public void setId(Long id){
        this.idTeacher = id;

        Teacher teacher = teacherDAO.findById(idTeacher);
        populateForm(teacher);
    }

   @FXML
    private void handleUpdate(ActionEvent event) {

        if (isInvalid()) {
            showAlert(AlertType.ERROR, "Error de Validación", "Todos los campos deben tener datos");
            return;
        }

        try {
            Teacher teacher = teacherDAO.findById(idTeacher);

            if (teacher == null) {
                showAlert(AlertType.ERROR, "Validación", "Profesor no encontrado con ID: " + idTeacher);
                return;
            }

            teacher.setPassword(txtPassword.getText());
            teacher.setName(txtName.getText());
            teacher.setLastName(txtLastName.getText());
            teacher.setSpecialty(txtSpecialty.getText());
            teacher.setBirthDate(dpBirthDate.getValue());

            teacherDAO.update(teacher);

        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Error de Actualización", "No se pudo actualizar: " + e.getMessage());
        }
    }

    private void populateForm(Teacher teacher) {
        if (teacher != null) {
            txtTeacherId.setText(String.valueOf(teacher.getId()));
            txtPassword.setText(teacher.getPassword());
            txtName.setText(teacher.getName());
            txtLastName.setText(teacher.getLastName());
            txtSpecialty.setText(teacher.getSpecialty());
            dpBirthDate.setValue(teacher.getBirthDate());
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
