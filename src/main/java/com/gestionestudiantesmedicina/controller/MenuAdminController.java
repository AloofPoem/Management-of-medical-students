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

public class MenuAdminController {

    @FXML
    private BorderPane bpMenu;

    @FXML
    void loadEstudentType(ActionEvent event) {
        loadView("EstudentTypeViewAdmin");
    }

    @FXML
    void loadPractice(ActionEvent event) {
        loadView("ClassViewAdmin");
    }

    @FXML
    void loadRelative(ActionEvent event) {
        loadView("GestionFamiliaresAdmin");
    }

    @FXML
    void loadSchedule(ActionEvent event) {
        loadView("ScheduleViewAdmin");
    }

    @FXML
    void loadStudent(ActionEvent event) {
        loadView("EstuViewAdmin");
    }

    @FXML
    void loadSubject(ActionEvent event) {
        loadView("SubjectViewAdmin");
    }
    
    @FXML
    void loadTeacher(ActionEvent event) {
        loadView("DocenteViewAdmin");
    }
    
    @FXML
    void loadUniversity(ActionEvent event) {
        loadView("UniversityViewAdmin");
    }

    @FXML
    void logout(ActionEvent event){
        try {
            App.setRoot("LoginView");
        } catch (IOException e) {
            showAlert(AlertType.ERROR,"Error al cerrar sesion", "No se pudo cerrar sesion");
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
