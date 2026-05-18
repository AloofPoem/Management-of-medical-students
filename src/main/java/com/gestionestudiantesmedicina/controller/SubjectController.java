package com.gestionestudiantesmedicina.controller;

import java.util.List;
import java.util.Optional;

import com.gestionestudiantesmedicina.daos.SubjectDAO;
import com.gestionestudiantesmedicina.entities.Subject;

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

public class SubjectController {

    @FXML
    private TextField txtSubjectId;

    @FXML
    private TextField txtSubjectName;

    @FXML
    private TableView<Subject> tableSubject;

    @FXML
    private TableColumn<Subject, Long> colSubjectId;

    @FXML
    private TableColumn<Subject, String> colSubjectName;

    private SubjectDAO subjectDAO = new SubjectDAO();

    private ObservableList<Subject> subjectList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {

        colSubjectId.setCellValueFactory(new PropertyValueFactory<>("idSubject"));
        colSubjectName.setCellValueFactory(new PropertyValueFactory<>("nameSubject"));

        loadSubjectList();

        tableSubject.getSelectionModel().selectedItemProperty().addListener(
            (obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    populateForm(newSelection);
                }
            }
        );
    }

    private void loadSubjectList() {

        subjectList.clear();

        List<Subject> subjects = subjectDAO.findAll();

        subjectList.addAll(subjects);

        tableSubject.setItems(subjectList);
    }

    private void populateForm(Subject subject) {

        txtSubjectId.setText(String.valueOf(subject.getIdSubject()));
        txtSubjectName.setText(subject.getNameSubject());
    }

    @FXML
    private void handleClear(ActionEvent event) {

        txtSubjectId.clear();
        txtSubjectName.clear();

        tableSubject.getSelectionModel().clearSelection();

        loadSubjectList();
    }

    @FXML
    private void handleCreate(ActionEvent event) {

        try {

            String subjectName = txtSubjectName.getText();

            Subject subject = new Subject();

            subject.setNameSubject(subjectName);

            subjectDAO.save(subject);

            loadSubjectList();

            handleClear(null);

            showAlert(AlertType.INFORMATION, "Creación Exitosa", "Materia creada correctamente.");

        } catch (Exception e) {

            showAlert(AlertType.ERROR, "Error de Creación",
                    "No se pudo crear la materia: " + e.getMessage());
        }
    }

    @FXML
    private void handleUpdate(ActionEvent event) {

        try {

            Long subjectId = Long.parseLong(txtSubjectId.getText().trim());

            Subject subject = subjectDAO.findById(subjectId);

            if (subject == null) {

                showAlert(AlertType.ERROR, "Validación",
                        "Materia no encontrada.");

                return;
            }

            subject.setNameSubject(txtSubjectName.getText());

            subjectDAO.update(subject);

            loadSubjectList();

            handleClear(null);

            showAlert(AlertType.INFORMATION, "Actualización Exitosa",
                    "Materia actualizada correctamente.");

        } catch (NumberFormatException e) {

            showAlert(AlertType.ERROR, "Error de Formato",
                    "El ID debe ser numérico.");

        } catch (Exception e) {

            showAlert(AlertType.ERROR, "Error de Actualización",
                    "No se pudo actualizar: " + e.getMessage());
        }
    }

    @FXML
    private void handleDelete(ActionEvent event) {

        try {

            Long subjectId = Long.parseLong(txtSubjectId.getText().trim());

            Alert alert = new Alert(AlertType.CONFIRMATION);

            alert.setTitle("Confirmar Eliminación");
            alert.setHeaderText("¿Está seguro de eliminar esta materia?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {

                subjectDAO.delete(subjectId);

                loadSubjectList();

                handleClear(null);

                showAlert(AlertType.INFORMATION, "Eliminación Exitosa",
                        "Materia eliminada correctamente.");
            }

        } catch (NumberFormatException e) {

            showAlert(AlertType.ERROR, "Error de Formato",
                    "El ID debe ser numérico.");

        } catch (Exception e) {

            showAlert(AlertType.ERROR, "Error de Eliminación",
                    "No se pudo eliminar: " + e.getMessage());
        }
    }

    @FXML
    private void handleSearch(ActionEvent event) {

        try {

            Long subjectId = Long.parseLong(txtSubjectId.getText().trim());

            Subject subject = subjectDAO.findById(subjectId);

            if (subject != null) {

                populateForm(subject);

                tableSubject.getItems().setAll(subject);

                tableSubject.getSelectionModel().select(subject);

            } else {

                showAlert(AlertType.INFORMATION, "Búsqueda",
                        "Materia no encontrada.");
            }

        } catch (NumberFormatException e) {

            showAlert(AlertType.ERROR, "Error de Formato",
                    "El ID debe ser numérico.");
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