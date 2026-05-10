package com.gestionestudiantesmedicina.controller;

import java.time.LocalDate;

import com.gestionestudiantesmedicina.entities.Student;
import com.gestionestudiantesmedicina.enumeration.BloodType;
import com.gestionestudiantesmedicina.enumeration.MaritalStatus;
import com.gestionestudiantesmedicina.enumeration.RelationShip;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class StudentController {

    @FXML
    private ComboBox<?> cbAcademicStatus;

    @FXML
    private ComboBox<BloodType> cbBloodType;

    @FXML
    private ComboBox<MaritalStatus> cbMaritalStatus;

    @FXML
    private ComboBox<?> cbModality;

    @FXML
    private ComboBox<RelationShip> cbRelationship;

    @FXML
    private ComboBox<?> cbShift;
    
    @FXML
    private TableView<Student> tableStudents;

    @FXML
    private TableColumn<?, ?> colAcademicStatus;

    @FXML
    private TableColumn<Student, String> colAddress;

    @FXML
    private TableColumn<Student, String> colAllergies;

    @FXML
    private TableColumn<Student, Double> colAverage;

    @FXML
    private TableColumn<Student, LocalDate> colBirthDate;

    @FXML
    private TableColumn<Student, String> colBirthPlace;

    @FXML
    private TableColumn<Student, BloodType> colBloodType;

    @FXML
    private TableColumn<?, ?> colCredits;

    @FXML
    private TableColumn<Student, String> colDiseases;

    @FXML
    private TableColumn<Student, String> colEmail;

    @FXML
    private TableColumn<?, ?> colEnrollmentDate;

    @FXML
    private TableColumn<?, ?> colEps;

    @FXML
    private TableColumn<Student, String> colFullName;
    //faltaria agregar last name

    @FXML
    private TableColumn<?, ?> colHeight;

    @FXML
    private TableColumn<Student, Long> colId;
    //no se si cambiarlo a String 

    @FXML
    private TableColumn<?, ?> colIdentity;
    //identity seria el mismo id

    @FXML
    private TableColumn<Student, String> colLegalRepAddress;

    @FXML
    private TableColumn<Student, LocalDate> colLegalRepBirthDate;

    @FXML
    private TableColumn<Student, Long> colLegalRepId;
    //si se cambia el id aca tambien seria el documento de identidad

    @FXML
    private TableColumn<Student, String> colLegalRepName;

    @FXML
    private TableColumn<Student, String> colLegalRepPhone;

    @FXML
    private TableColumn<Student, MaritalStatus> colMaritalStatus;

    @FXML
    private TableColumn<Student, String> colPhone;

    @FXML
    private TableColumn<Student, String> colProgram;

    @FXML
    private TableColumn<Student, RelationShip> colRelationship;

    @FXML
    private TableColumn<Student, Integer> colSemester;
    //revisar creo que esta como String

    @FXML
    private TableColumn<Student, Double> colWeight;

    @FXML
    private DatePicker dpBirthDate;

    @FXML
    private DatePicker dpEnrollmentDate;

    @FXML
    private DatePicker dpLegalRepBirthDate;

    @FXML
    private Label lblDateTime;

    @FXML
    private Label lblRecordCount;

    @FXML
    private Label lblStatus;

    @FXML
    private Label lblUser;

    @FXML
    private Spinner<Integer> spSemester;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextArea txtAllergies;

    @FXML
    private TextField txtAverage;

    @FXML
    private TextField txtBirthPlace;

    @FXML
    private TextField txtCredits;

    @FXML
    private TextArea txtDiseases;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtEps;

    @FXML
    private TextField txtFullName;

    @FXML
    private TextField txtHeight;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtIdentity;

    @FXML
    private TextField txtLegalRepAddress;

    @FXML
    private TextField txtLegalRepDocument;

    @FXML
    private TextField txtLegalRepEmail;

    @FXML
    private TextField txtLegalRepId;

    @FXML
    private TextField txtLegalRepName;

    @FXML
    private TextField txtLegalRepOccupation;

    @FXML
    private TextField txtLegalRepPhone;

    @FXML
    private TextField txtLegalRepPhone2;

    @FXML
    private TextArea txtMedications;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtProgram;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtWeight;



}