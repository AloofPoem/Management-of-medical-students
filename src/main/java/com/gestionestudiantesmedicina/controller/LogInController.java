package com.gestionestudiantesmedicina.controller;

import java.io.IOException;

import com.gestionestudiantesmedicina.App;
import com.gestionestudiantesmedicina.daos.PersonDAO;
import com.gestionestudiantesmedicina.entities.Admin;
import com.gestionestudiantesmedicina.entities.Person;
import com.gestionestudiantesmedicina.entities.Student;
import com.gestionestudiantesmedicina.entities.Teacher;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class LogInController {

    @FXML
    private TextField txtId;
    
    @FXML
    private TextField txtPassword;

    private PersonDAO personDAO = new PersonDAO();

    @FXML
    private void login(ActionEvent event){

        try {
            Long id = Long.parseLong(txtId.getText());
            
            String password = txtPassword.getText();
            
            Person person = personDAO.findById(id);
            
            if (person == null) {
                showAlert(AlertType.WARNING, "Usuario no encontrado", "No se encontro un usuario con ese user");
                return;
            }

            if (!person.getPassword().equals(password)) {
                showAlert(AlertType.WARNING, "Contraseña Incorecta", "La contraseña del usuario " + id + " no es la indicada");
                return;
            }

            if (person instanceof Teacher) {
                //ir a la vista de profesor
                return;    
            }

            if (person instanceof Student) {
                //ir a la vista de estudiante
                return;    
            }

            if (person instanceof Admin) {
                //ir a la vista de Admin
                return;    
            }

        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Error de Formato", "El ID debe ser un número.");
        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Error", "Hubo un error al hacer el Login");
        }
            
    }


    //ponerle nombres de .fxml
    @FXML
    private void loadRegisterStudent(ActionEvent event) throws IOException{
        App.setRoot("student");   
    }
    
    @FXML
    private void loadRegisterTeacher(ActionEvent event) throws IOException{
        App.setRoot("teacher");   
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
