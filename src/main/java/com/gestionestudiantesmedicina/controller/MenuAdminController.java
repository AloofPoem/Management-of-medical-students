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
    void loadPractice(ActionEvent event) {
        loadView("ClassViewAdmin");
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

    private void loadView(String fxmlName) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource(fxmlName + ".fxml"));
            Parent view = loader.load();
            bpMenu.setCenter(view);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error al Cargar", "No se pudo cargar la vista: " + fxmlName + ".fxml");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
