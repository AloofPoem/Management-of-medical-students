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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LogInController {

    @FXML
    private TextField txtId;
    
    @FXML
    private TextField txtPassword;

    private PersonDAO personDAO = new PersonDAO();

    @FXML
    private void logIn(ActionEvent event){

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
                try {
                    FXMLLoader loader = new FXMLLoader(App.class.getResource("MenuDocen.fxml"));
                    Parent root = loader.load();
                    
                    MenuTeacherController menuTeacherController = loader.getController();
                    menuTeacherController.setId(id);
                    
                    Stage stage = (Stage) txtId.getScene().getWindow();
                    stage.getScene().setRoot(root);
                    stage.setMaximized(true);
                    stage.show();
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                return;      
            }
            
            if (person instanceof Student) {
                try {
                    FXMLLoader loader = new FXMLLoader(App.class.getResource("MenuEstu.fxml"));
                    Parent root = loader.load();
                    
                    MenuStudentController menuStudentController = loader.getController();
                    menuStudentController.setIStudent(id);

                    Stage stage = (Stage) txtId.getScene().getWindow();
                    stage.getScene().setRoot(root);
                    stage.setMaximized(true);
                    stage.show();

                } catch (Exception e) {
                    showAlert(AlertType.ERROR,"Error al Cargar", "No se pudo cargar la vista: GestionFamiliares.fxml");
                    e.printStackTrace();
                }
                    
                return;    
            }
            
            if (person instanceof Admin) {
                App.setRoot("MenuAdmin");
                return;    
            }

        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Error de Formato", "El ID debe ser un número.");
        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Error", "Hubo un error al hacer el Login");
            System.out.println(e);
        }
            
    }


    //ponerle nombres de .fxml
    @FXML
    private void loadRegisterStudent(ActionEvent event) throws IOException{
        App.setRoot("RegisEstu");   
    }
    
    @FXML
    private void loadRegisterTeacher(ActionEvent event) throws IOException{
        App.setRoot("RegisDocente");   
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
