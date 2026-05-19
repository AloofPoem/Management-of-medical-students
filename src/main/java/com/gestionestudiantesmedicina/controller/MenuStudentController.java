package com.gestionestudiantesmedicina.controller;


import com.gestionestudiantesmedicina.App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;

public class MenuStudentController {

    @FXML
    private BorderPane bpMenu;

    private Long idStudent;

    public void setIStudent(Long id){
        this.idStudent = id;
    }

    //darle un view que solo sea actualizar
    @FXML
    private void loadStudent(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("InfoEstu.fxml"));   
            Parent view = loader.load();
            InfoStudentController infoStudentController = loader.getController();
            
            infoStudentController.setId(idStudent);
            bpMenu.setCenter(view);        
        } catch (Exception e) {
            showAlert("Error al Cargar", "No se pudo cargar la vista: InfoEstu.fxml");
            e.printStackTrace();
        }
        
    }
    
    @FXML
    private void loadRelative(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("GestionFamiliares.fxml"));   
            Parent view = loader.load();
            RelativeStudentController relativeController = loader.getController();
            
            relativeController.setId(idStudent);
            bpMenu.setCenter(view);        
        } catch (Exception e) {
            showAlert("Error al Cargar", "No se pudo cargar la vista: GestionFamiliares.fxml");
            e.printStackTrace();
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
