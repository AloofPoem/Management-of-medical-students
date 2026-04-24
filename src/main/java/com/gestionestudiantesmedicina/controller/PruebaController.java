package com.gestionestudiantesmedicina.controller;

import java.io.File;
import java.io.FileOutputStream;

import com.gestionestudiantesmedicina.daos.PruebaDAO;
import com.gestionestudiantesmedicina.entities.Prueba;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

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

    private File archivoSeleccionado;
    //file - path

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
            // TODO: handle exception
        }
    }
}
