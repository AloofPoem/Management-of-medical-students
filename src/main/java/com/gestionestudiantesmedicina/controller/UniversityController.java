package com.gestionestudiantesmedicina.controller;

import java.util.List;
import java.util.Optional;

import com.gestionestudiantesmedicina.daos.UniversityDAO;
import com.gestionestudiantesmedicina.entities.University;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class UniversityController {

    @FXML
    private TextField txtUniversityId;

    @FXML
    private TextField txtUniversityName;
    
    @FXML
    private TextField txtSearch;

    @FXML
    private TableView<University> tableUniversity;

    @FXML
    private TableColumn<University, Long> colUniversityId;

    @FXML
    private TableColumn<University, String> colUniversityName;

    private UniversityDAO universityDAO = new UniversityDAO();
    private ObservableList<University> universityList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        colUniversityId.setCellValueFactory(new PropertyValueFactory<>("idUniversity"));
        colUniversityName.setCellValueFactory(new PropertyValueFactory<>("universityName"));
        loaduniversityList();

        tableUniversity.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        populateForm(newSelection);
                    }
                }
            );
    }

    private void loaduniversityList() {
        universityList.clear();
        List<University> universities = universityDAO.findAll();
        universityList.addAll(universities);
        tableUniversity.setItems(universityList);
    }

    private void populateForm(University university) {
        txtUniversityId.setText(String.valueOf(university.getIdUniversity()));
        txtUniversityName.setText(university.getUniversityName());
    }

    @FXML
    private void handleClear(ActionEvent event) {
        txtUniversityId.clear();
        txtUniversityName.clear();
        tableUniversity.getSelectionModel().clearSelection();

        loaduniversityList();
    }

    @FXML
    private void handleCreate(ActionEvent event) {

        try {

            String universityName = txtUniversityName.getText();

            University university = new University();

            university.setUniversityName(universityName);

            universityDAO.save(university);

            loaduniversityList();

            handleClear(null);

            showAlert(AlertType.INFORMATION, "Creación Exitosa", "La universidad se ha creado exitosamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            showAlert(AlertType.ERROR, "Error de Creación", "No se pudo crear la universidad: " + e.getMessage());
        }
    }

    @FXML
    private void handleUpdate(ActionEvent event) {

        try {

            Long universityId = Long.parseLong(txtUniversityId.getText().trim());

            University university = universityDAO.findById(universityId);

            if (university == null) {
                showAlert(AlertType.ERROR, "Validación", "Universidad no encontrada.");
                return;
            }

            university.setUniversityName(txtUniversityName.getText());

            universityDAO.update(university);

            loaduniversityList();

            handleClear(null);

            showAlert(AlertType.INFORMATION, "Actualización Exitosa", "Universidad actualizada correctamente.");

        } catch (NumberFormatException e) {

            showAlert(AlertType.ERROR, "Error de Formato", "El ID debe ser numérico.");

        } catch (Exception e) {

            showAlert(AlertType.ERROR, "Error de Actualización", "No se pudo actualizar: " + e.getMessage());
        }
    }

    @FXML
    private void handleDelete(ActionEvent event) {

        try {

            Long universityId = Long.parseLong(txtUniversityId.getText().trim());

            Alert alert = new Alert(AlertType.CONFIRMATION);

            alert.setTitle("Confirmar Eliminación");
            alert.setHeaderText("¿Está seguro de eliminar esta universidad?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {

                universityDAO.delete(universityId);

                loaduniversityList();

                handleClear(null);

                showAlert(AlertType.INFORMATION, "Eliminación Exitosa", "Universidad eliminada correctamente.");
            }

        } catch (NumberFormatException e) {

            showAlert(AlertType.ERROR, "Error de Formato", "El ID debe ser numérico.");

        } catch (Exception e) {

            showAlert(AlertType.ERROR, "Error de Eliminación", "No se pudo eliminar: " + e.getMessage());
        }
    }

    @FXML
    private void handleSearch(ActionEvent event) {

        try {

            Long universityId = Long.parseLong(txtSearch.getText().trim());

            University university = universityDAO.findById(universityId);

            if (university != null) {

                populateForm(university);

                tableUniversity.getItems().setAll(university);

                tableUniversity.getSelectionModel().select(university);

            } else {

                showAlert(AlertType.INFORMATION, "Búsqueda", "Universidad no encontrada.");
            }

        } catch (NumberFormatException e) {

            showAlert(AlertType.ERROR, "Error de Formato", "El ID debe ser numérico.");
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
