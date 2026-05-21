package com.gestionestudiantesmedicina.controller;

import java.io.IOException;

import com.gestionestudiantesmedicina.App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;

public class MenuTeacherController {
    
    @FXML
    private BorderPane bpMenu;

    private Long idTeacher;

    public void setId(Long id){
        this.idTeacher = id;
    }

    // LOS LOADVIEW ESTAN MAL PORQUE AUN NO ESTAN LAS VISTAS CORRECTAS
    @FXML
    void loadPractice(ActionEvent event) {
        loadView("ClassViewAdmin");
        //FALTA UN VIEW
    }

    @FXML
    void loadRecords(ActionEvent event) {
        loadView("RecordViewAdmin");
    }

    @FXML
    void loadTeacher(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("InfoDocente.fxml"));   
            Parent view = loader.load();
            InfoTeacherController infoTeacherController = loader.getController();
            
            infoTeacherController.setId(idTeacher);
            bpMenu.setCenter(view);        
        } catch (Exception e) {
            e.printStackTrace();
        }
        //loadView("RegisDocente");
    }

    @FXML
    void logout(ActionEvent event){
        try {
            App.setRoot("LoginView");
        } catch (IOException e) {
            showAlert(AlertType.ERROR, "Error al cerrar sesion", "No se pudo cerrar sesion");
            e.printStackTrace();
        }
    }

    
    private void loadView(String fxmlName) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource(fxmlName + ".fxml"));
            Parent view = loader.load();
            bpMenu.setCenter(view);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR,"Error al Cargar", "No se pudo cargar la vista: " + fxmlName + ".fxml");
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
