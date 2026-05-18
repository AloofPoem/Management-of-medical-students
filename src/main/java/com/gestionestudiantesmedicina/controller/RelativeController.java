package com.gestionestudiantesmedicina.controller;

import java.util.List;
import java.util.Optional;

import com.gestionestudiantesmedicina.daos.RelativeDAO;
import com.gestionestudiantesmedicina.daos.StudentDAO;
import com.gestionestudiantesmedicina.entities.Relative;
import com.gestionestudiantesmedicina.entities.Student;
import com.gestionestudiantesmedicina.enumeration.RelationShip;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class RelativeController {

    //no tenemos student

    @FXML
    private ComboBox<RelationShip> cbRelRelationship;

    @FXML
    private TextField txtRelId;
    
    @FXML
    private TextField txtRelName;
    
    @FXML
    private TextField txtRelLastName;

    @FXML    
    private TableView<Relative> tableRelative;

    @FXML
    private TableColumn<Relative, Long> colRelId;
    
    @FXML
    private TableColumn<Relative, String> colRelName;
    
    @FXML
    private TableColumn<Relative, String> colRelLastName;
    
    @FXML
    private TableColumn<Relative, RelationShip> colRelRelationship;

    //ponerlo de una vez con Student ???
    private Long studentId;
    private RelativeDAO relativeDAO = new RelativeDAO();
    private ObservableList<Relative> relativeList = FXCollections.observableArrayList();

    @FXML
    private void initialize(){
        colRelId.setCellValueFactory(new PropertyValueFactory<>("idRelative"));
        colRelName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colRelLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colRelRelationship.setCellValueFactory(new PropertyValueFactory<>("relationship"));

        cbRelRelationship.getItems().setAll(RelationShip.values());

        loadRlativeList();

        tableRelative.getSelectionModel().selectedItemProperty().addListener(
            (obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        populateForm(newSelection);
                    }
                }
        );
    }

    private void loadRlativeList() {
        relativeList.clear();
        List<Relative> relatives = relativeDAO.findAll();
        relativeList.addAll(relatives);
        tableRelative.setItems(relativeList);
    }

    private void populateForm(Relative r) {
        txtRelId.setText(String.valueOf(r.getIdRelative()));   
        txtRelName.setText(r.getName());
        txtRelLastName.setText(r.getLastName());
        cbRelRelationship.setValue(r.getRelationship());
    }

    @FXML
    private void handleClear(ActionEvent event){
        txtRelId.clear();
        txtRelName.clear();
        txtRelLastName.clear();
        cbRelRelationship.setValue(null);
        tableRelative.getSelectionModel().clearSelection();
        loadRlativeList();
    }

    @FXML   
    private void handleCreate(ActionEvent event){
        try {
            
            String relName = txtRelName.getText();
            String relLastName = txtRelLastName.getText();
            RelationShip relRelationShip = cbRelRelationship.getValue();
            StudentDAO studentDAO = new StudentDAO();
            Student student = studentDAO.findById(studentId);

            Relative relative = new Relative(relName, relLastName, student, relRelationShip);

            relativeDAO.save(relative);

            loadRlativeList();
            handleClear(null);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            showAlert(AlertType.ERROR, "Error de Creación", "No se pudo crear el familiar: " + e.getMessage());
        }
    }

    @FXML
    private void handleUpdate(ActionEvent event){
        try {
            Long relId = Long.parseLong(txtRelId.getText().trim());
            Relative relative = relativeDAO.findById(relId);

            if (relative == null) {
                showAlert(AlertType.ERROR, "Validacion", "Familiar no encontrado con ID: " + relId);
                return;
            }

            relative.setName(txtRelName.getText());
            relative.setLastName(txtRelLastName.getText());
            relative.setRelationship(cbRelRelationship.getValue());

            relativeDAO.update(relative);

            loadRlativeList();
            handleClear(null);
           
        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Error de Formato", "Algunos campos deben ser numeros");
        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Error de Actualización", "No se pudo actualizar: " + e.getMessage());
        }
    }

    @FXML
    private void handleDelete(ActionEvent event){
        try {
            Long relId = Long.parseLong(txtRelId.getText().trim());
            
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmar Eliminación");
            alert.setHeaderText("¿Está seguro de que desea eliminar al familiar con ID " + relId + "?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                relativeDAO.delete(relId);
                loadRlativeList();
                handleClear(null);
            }
        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Error de Formato", "El ID debe ser un número.");
        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Error de Eliminación", "No se pudo eliminar: " + e.getMessage());
        }
    }

    @FXML
    private void handleSearch(ActionEvent event){
        try {
            Long relId = Long.parseLong(txtRelId.getText().trim());
            Relative relative = relativeDAO.findById(relId);

            /*
            Revisar si se puede asi en vez del if else
            if (relative == null) {
                showAlert(AlertType.INFORMATION, "Búsqueda", "Familiar no encontrado con ID: " + relId);
                return;
            }
            */

            if (relative != null) {
                populateForm(relative);
                tableRelative.getItems().setAll(relative); 
                tableRelative.getSelectionModel().select(relative);
            } else {
                showAlert(AlertType.INFORMATION, "Búsqueda", "Familiar no encontrado con ID: " + relId);
            }

        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Error de Formato", "El ID debe ser un número.");
        }
    }

    public void setStudentId(Long id){
        this.studentId = id;
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
