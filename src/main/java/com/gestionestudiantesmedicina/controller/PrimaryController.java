package com.gestionestudiantesmedicina.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PrimaryController {

    @FXML
    private Label idText;

    private String tex;

    @FXML
    private Button primaryButton;

    @FXML
    private void switchToSecondary() throws IOException {
        idText.setText(tex);
        
    }

    public void setText(String text){
        this.tex = text;
    }
}
