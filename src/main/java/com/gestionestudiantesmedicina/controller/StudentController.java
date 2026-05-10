package com.gestionestudiantesmedicina.controller;

import java.time.LocalDate;

import com.gestionestudiantesmedicina.daos.StudentDAO;
import com.gestionestudiantesmedicina.entities.AcademicData;
import com.gestionestudiantesmedicina.entities.HealthData;
import com.gestionestudiantesmedicina.entities.LegalRepresentative;
import com.gestionestudiantesmedicina.entities.Student;
import com.gestionestudiantesmedicina.enumeration.BloodType;
import com.gestionestudiantesmedicina.enumeration.MaritalStatus;
import com.gestionestudiantesmedicina.enumeration.RelationShip;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class StudentController {

    @FXML
    private ComboBox<BloodType> cbBloodType;

    @FXML
    private ComboBox<MaritalStatus> cbMaritalStatus;

    @FXML
    private ComboBox<RelationShip> cbRelationship;

    @FXML
    private TableView<Student> tableStudents;

    @FXML
    private TableColumn<Student, String> colAddressTunja;

    @FXML
    private TableColumn<Student, String> colPermanentAdress;

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
    private TableColumn<Student, String> colIllness;

    @FXML
    private TableColumn<Student, String> colEmail;

    @FXML
    private TableColumn<?, ?> colEnrollmentDate;

    @FXML
    private TableColumn<?, ?> colEps;

    @FXML
    private TableColumn<Student, String> colName;

    @FXML
    private TableColumn<Student, String> colLastName;

    @FXML
    private TableColumn<Student, Long> colId;
    // no se si cambiarlo a String

    @FXML
    private TableColumn<?, ?> colIdentity;
    // identity seria el mismo id

    @FXML
    private TableColumn<Student, String> colLegalRepAddress;

    @FXML
    private TableColumn<Student, LocalDate> colLegalRepBirthDate;

    @FXML
    private TableColumn<Student, Long> colLegalRepId;
    // si se cambia el id aca tambien seria el documento de identidad

    @FXML
    private TableColumn<Student, String> colSecondLanguage;

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
    // revisar creo que esta como String

    @FXML
    private TableColumn<Student, Double> colWeight;

    @FXML
    private TableColumn<Student, Double> colSize;

    @FXML
    private TableColumn<Student, Double> colBmi;

    @FXML
    private TableColumn<Student, Integer> colroomies;

    @FXML
    private TableColumn<Student, Integer> colFamilyCoreTunja;

    @FXML
    private TableColumn<Student, String> colIdUni;

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
    private Spinner<Integer> spRoomies;

    // revisar int
    @FXML
    private Spinner<Integer> spFamilyCoreTunja;

    @FXML
    private TextField txtAddressTunja;

    @FXML
    private TextField txtIdUni;

    // falta agregarlo en la vista
    @FXML
    private TextField txtPermanentAdress;

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
    private TextArea txtIllness;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtSecondLanguage;

    @FXML
    private TextField txtEps;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtLastName;

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

    @FXML
    private TextField txtSize;

    @FXML
    private TextField txtBmi;

    private StudentDAO studentDAO = new StudentDAO();

    @FXML
    private void initialize() {

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        // i am not sure
        colIdentity.setCellValueFactory(new PropertyValueFactory<>("identity"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colBirthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        colBirthPlace.setCellValueFactory(new PropertyValueFactory<>("birthPlace"));
        colMaritalStatus.setCellValueFactory(new PropertyValueFactory<>("maritalStatus"));
        colAddressTunja.setCellValueFactory(new PropertyValueFactory<>("addressTunja"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colBloodType.setCellValueFactory(new PropertyValueFactory<>("bloodType"));
        colWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        // se podria agregar
        colEps.setCellValueFactory(new PropertyValueFactory<>("eps"));
        colDiseases.setCellValueFactory(new PropertyValueFactory<>("generalDiseases"));
        colAllergies.setCellValueFactory(new PropertyValueFactory<>("allergies"));
        colProgram.setCellValueFactory(new PropertyValueFactory<>("academicProgram"));
        colSemester.setCellValueFactory(new PropertyValueFactory<>("semester"));
        colAverage.setCellValueFactory(new PropertyValueFactory<>("cumulativeAverage"));

        colPermanentAdress.setCellValueFactory(new PropertyValueFactory<>("permanentAdress"));
        colSecondLanguage.setCellValueFactory(new PropertyValueFactory<>("secondLanguage"));
        colroomies.setCellValueFactory(new PropertyValueFactory<>("roomies"));
        colFamilyCoreTunja.setCellValueFactory(new PropertyValueFactory<>("familyCoreTunja"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colIllness.setCellValueFactory(new PropertyValueFactory<>("mentalIlness"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colBmi.setCellValueFactory(new PropertyValueFactory<>("bmi"));

        colIdUni.setCellValueFactory(cellData -> {
            Student student = cellData.getValue();
            AcademicData ac = student.getAcademicData();
            String studentDisplay = "";
            if (ac.getUniversity() != null) {
                studentDisplay = String.valueOf(ac.getUniversity().getIdUniversity());
            }

            return new SimpleStringProperty(studentDisplay);
        });

        cbMaritalStatus.getItems().setAll(MaritalStatus.values());
        cbBloodType.getItems().setAll(BloodType.values());
        cbRelationship.getItems().setAll(RelationShip.values());

        // SpinnerValueFactory<Integer> valueFactory = new
        // SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);
        // spSemester.setValueFactory(valueFactory);
        spSemester.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1));

        spRoomies.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1));
        spFamilyCoreTunja.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1));

        tableStudents.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        populateForm(newSelection);
                    }
                });

    }

    private void populateForm(Student s){
        txtId.setText(String.valueOf(s.getIdStudent()));
        //remember
        txtIdentity.setText(s.getIdentityStudent());
        //STUDENT no extiendeeee!!!!
        //txtName.setText(s.getName());
        //txtLastName.setText(s.getLastName());
        //FALTA RECORD ????
        //dpBirthDate.setValue(s.getBirthDate() != null ? s.getBithDate().toString() : "");
        cbMaritalStatus.setValue(s.getMaritalStatus());
        txtBirthPlace.setText(s.getBirthPlace());
        txtAddressTunja.setText(s.getAddressTunja());
        txtPermanentAdress.setText(s.getPermanentAddress());
        txtPhone.setText(s.getPhoneNumber());
        txtEmail.setText(s.getEmail());
        txtSecondLanguage.setText(s.getSecondLanguage());
        //spRoomies.setText
        //spFamilyCoreTunja

        AcademicData ac = s.getAcademicData();
        txtProgram.setText(ac.getAcademicProgram());
        //spSemester
        txtAverage.setText(String.valueOf(ac.getCumulativeAverage()));
        txtIdUni.setText(ac.getUniversity() != null ? String.valueOf(ac.getUniversity().getIdUniversity()): "");
        
        HealthData hd = s.getHealthData();
        txtDiseases.setText(hd.getGeneralDiseases());
        txtIllness.setText(hd.getMentalIllness());
        txtMedications.setText(hd.getMedications());
        txtAllergies.setText(hd.getAllergies());
        txtWeight.setText(String.valueOf(hd.getWeight()));
        txtSize.setText(String.valueOf(hd.getSize()));
        txtBmi.setText(String.valueOf(hd.getBmi()));
        cbBloodType.setValue(hd.getBloodType());

        //LegalRepresentative 

    }
}