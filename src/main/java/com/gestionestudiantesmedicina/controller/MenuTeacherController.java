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
        loadView("RegisDocente");
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
