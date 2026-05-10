package com.gestionestudiantesmedicina.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.Desktop;

import com.gestionestudiantesmedicina.App;
import com.gestionestudiantesmedicina.daos.PruebaDAO;
import com.gestionestudiantesmedicina.entities.Prueba;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class PruebaController {
    @FXML
    private Label lblNombreArchivo;
    @FXML
    private Button btnSubir;
    @FXML
    private Button btnVerPdf;
    @FXML
    private TextField idF;
    @FXML
    private ProgressBar progressBar;

    @FXML
    private Button idEnviar;

    private File archivoSeleccionado;
    // file - path

    // Acción para abrir el explorador de archivos
    @FXML
    private void handleSeleccionarArchivo() {
        FileChooser fileChooser = new FileChooser();

        // Filtramos para que solo permita PDFs
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos permitidos", "*.pdf"));

        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            this.archivoSeleccionado = file;
            lblNombreArchivo.setText(file.getName());
            btnSubir.setDisable(false);
        }
    }

    // Acción para guardar el archivo
    @FXML
    private void handleSubirArchivo() {
        if (archivoSeleccionado != null) {
            try {

                String rutaParaElDao = archivoSeleccionado.getAbsolutePath();

                PruebaDAO dao = new PruebaDAO();

                dao.guardarArchivo(rutaParaElDao);

                lblNombreArchivo.setText("¡PDF guardado correctamente!");
                btnSubir.setDisable(true);

            } catch (Exception e) {
                lblNombreArchivo.setText("Error al guardar en la BD.");
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleVerArchivo() {

        long id = Long.parseLong(idF.getText());

        PruebaDAO dao = new PruebaDAO();
        Prueba prueba = dao.findById(id);

        if (prueba == null || prueba.getContenido() == null) {
            return;
        }

        byte[] cont = prueba.getContenido();

        File tempFile = null;
        try {
            tempFile = File.createTempFile("reporte", ".pdf");
            tempFile.deleteOnExit();

        } catch (Exception e) {

        }

        try {
            FileOutputStream fot = new FileOutputStream(tempFile);
            fot.write(cont);
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(tempFile);
            }
        } catch (Exception e) {

        }
    }

    @FXML
    private void handleEnviar(ActionEvent event) {
        String id = idF.getText();
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("primary.fxml"));
            Parent root = loader.load();
            // loader.load();

            PrimaryController primaryController = loader.getController();
            primaryController.setText(id);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
