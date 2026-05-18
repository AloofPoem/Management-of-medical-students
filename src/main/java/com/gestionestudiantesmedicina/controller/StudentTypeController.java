package com.gestionestudiantesmedicina.controller;

import java.util.List;
import java.util.Optional;

import com.gestionestudiantesmedicina.daos.StudentTypeDAO;
import com.gestionestudiantesmedicina.entities.StudentType;

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

public class StudentTypeController {

    @FXML
    private TextField txtStudentTypeId;

    @FXML
    private TextField txtStudentTypeName;

    @FXML
    private TableView<StudentType> tableStudentType;

    @FXML
    private TableColumn<StudentType, Long> colStudentTypeId;

    @FXML
    private TableColumn<StudentType, String> colStudentTypeName;

    private StudentTypeDAO studentTypeDAO = new StudentTypeDAO();

    private ObservableList<StudentType> studentTypeList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {

        colStudentTypeId.setCellValueFactory(new PropertyValueFactory<>("idStuType"));

        colStudentTypeName.setCellValueFactory(new PropertyValueFactory<>("nameStuType"));

        loadStudentTypeList();

        tableStudentType.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> {

                    if (newSelection != null) {

                        populateForm(newSelection);
                    }
                }
            );
    }

    private void loadStudentTypeList() {

        studentTypeList.clear();

        List<StudentType> studentTypes = studentTypeDAO.findAll();

        studentTypeList.addAll(studentTypes);

        tableStudentType.setItems(studentTypeList);
    }

    private void populateForm(StudentType studentType) {

        txtStudentTypeId.setText(
                String.valueOf(studentType.getIdStuType()));

        txtStudentTypeName.setText(
                studentType.getNameStuType());
    }

    @FXML
    private void handleClear(ActionEvent event) {

        txtStudentTypeId.clear();

        txtStudentTypeName.clear();

        tableStudentType.getSelectionModel().clearSelection();

        loadStudentTypeList();
    }

    @FXML
    private void handleCreate(ActionEvent event) {

        try {

            String studentTypeName = txtStudentTypeName.getText();

            StudentType studentType = new StudentType();

            studentType.setNameStuType(studentTypeName);

            studentTypeDAO.save(studentType);

            loadStudentTypeList();

            handleClear(null);

            showAlert(
                    AlertType.INFORMATION,
                    "Creación Exitosa",
                    "Tipo de estudiante creado correctamente.");

        } catch (Exception e) {

            showAlert(
                    AlertType.ERROR,
                    "Error de Creación",
                    "No se pudo crear el tipo de estudiante: " + e.getMessage());
        }
    }

    @FXML
    private void handleUpdate(ActionEvent event) {

        try {

            Long studentTypeId = Long.parseLong(
                    txtStudentTypeId.getText().trim());

            StudentType studentType = studentTypeDAO.findById(studentTypeId);

            if (studentType == null) {

                showAlert(
                        AlertType.ERROR,
                        "Validación", "Tipo de estudiante no encontrado.");

                return;
            }

            studentType.setNameStuType(
                    txtStudentTypeName.getText());

            studentTypeDAO.update(studentType);

            loadStudentTypeList();

            handleClear(null);

            showAlert(
                    AlertType.INFORMATION,
                    "Actualización Exitosa",
                    "Tipo de estudiante actualizado correctamente.");

        } catch (NumberFormatException e) {

            showAlert(
                    AlertType.ERROR,
                    "Error de Formato",
                    "El ID debe ser numérico.");

        } catch (Exception e) {

            showAlert(
                    AlertType.ERROR,
                    "Error de Actualización",
                    "No se pudo actualizar: " + e.getMessage());
        }
    }

    @FXML
    private void handleDelete(ActionEvent event) {

        try {

            Long studentTypeId = Long.parseLong(
                    txtStudentTypeId.getText().trim());

            Alert alert = new Alert(AlertType.CONFIRMATION);

            alert.setTitle("Confirmar Eliminación");

            alert.setHeaderText(
                    "¿Está seguro de eliminar este tipo de estudiante?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {

                studentTypeDAO.delete(studentTypeId);

                loadStudentTypeList();

                handleClear(null);

                showAlert(
                        AlertType.INFORMATION,
                        "Eliminación Exitosa",
                        "Tipo de estudiante eliminado correctamente.");
            }

        } catch (NumberFormatException e) {

            showAlert(
                    AlertType.ERROR,
                    "Error de Formato",
                    "El ID debe ser numérico.");

        } catch (Exception e) {

            showAlert(
                    AlertType.ERROR,
                    "Error de Eliminación",
                    "No se pudo eliminar: " + e.getMessage());
        }
    }

    @FXML
    private void handleSearch(ActionEvent event) {

        try {

            Long studentTypeId = Long.parseLong(
                    txtStudentTypeId.getText().trim());

            StudentType studentType = studentTypeDAO.findById(studentTypeId);

            if (studentType != null) {

                populateForm(studentType);

                tableStudentType.getItems().setAll(studentType);

                tableStudentType.getSelectionModel().select(studentType);

            } else {

                showAlert(
                        AlertType.INFORMATION,
                        "Búsqueda",
                        "Tipo de estudiante no encontrado.");
            }

        } catch (NumberFormatException e) {

            showAlert(
                    AlertType.ERROR,
                    "Error de Formato",
                    "El ID debe ser numérico.");
        }
    }

    private void showAlert(
            AlertType alertType,
            String title,
            String message) {

        Alert alert = new Alert(alertType);

        alert.setTitle(title);

        alert.setHeaderText(null);

        alert.setContentText(message);

        alert.showAndWait();
    }
}