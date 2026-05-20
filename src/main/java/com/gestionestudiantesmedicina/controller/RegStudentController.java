package com.gestionestudiantesmedicina.controller;

import java.time.LocalDate;

import com.gestionestudiantesmedicina.App;
import com.gestionestudiantesmedicina.daos.StudentDAO;
import com.gestionestudiantesmedicina.daos.StudentTypeDAO;
import com.gestionestudiantesmedicina.daos.UniversityDAO;
import com.gestionestudiantesmedicina.entities.AcademicData;
import com.gestionestudiantesmedicina.entities.HealthData;
import com.gestionestudiantesmedicina.entities.LegalRepresentative;
import com.gestionestudiantesmedicina.entities.Student;
import com.gestionestudiantesmedicina.entities.StudentType;
import com.gestionestudiantesmedicina.entities.University;
import com.gestionestudiantesmedicina.enumeration.BloodType;
import com.gestionestudiantesmedicina.enumeration.MaritalStatus;
import com.gestionestudiantesmedicina.enumeration.RelationShip;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;

public class RegStudentController {
    @FXML
    private ComboBox<BloodType> cbBloodType;

    @FXML
    private ComboBox<MaritalStatus> cbMaritalStatus;

    @FXML
    private ComboBox<RelationShip> cbLegalRepRelationship;

    @FXML
    private DatePicker dpBirthDate;

    @FXML
    private DatePicker dpLegalRepBirthDate;

    @FXML
    private Spinner<Integer> spSemester;

    @FXML
    private Spinner<Integer> spRoomies;

    @FXML
    private Spinner<Integer> spFamilyCoreTunja;

    @FXML
    private TextField txtAddressTunja;

    @FXML
    private DatePicker dpEntryDate;

    @FXML
    private TextField txtIdUni;

    @FXML
    private TextField txtPermanentAddress;

    @FXML
    private TextArea txtAllergies;

    @FXML
    private TextField txtAverage;

    @FXML
    private TextField txtBirthPlace;

    @FXML
    private TextArea txtDiseases;

    @FXML
    private TextArea txtIllness;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtSecondLanguage;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtId;

    // password no tiene col para que no se pueda ver asi como asi
    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtLegalRepAddress;

    @FXML
    private TextField txtLegalRepName;

    @FXML
    private TextField txtLegalRepPhone;

    @FXML
    private TextField txtLegalRepCity;

    @FXML
    private TextArea txtMedications;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtProgram;

    @FXML
    private TextField txtWeight;

    @FXML
    private TextField txtSize;

    @FXML
    private TextField txtBmi;

    @FXML
    private TextField txtStudentTypeId;

    private StudentDAO studentDAO = new StudentDAO();

    @FXML
    private void initialize() {

        cbMaritalStatus.getItems().setAll(MaritalStatus.values());
        cbBloodType.getItems().setAll(BloodType.values());
        cbLegalRepRelationship.getItems().setAll(RelationShip.values());

        // Inicialización de Spinners
        spSemester.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12, 1));
        spRoomies.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0));
        spFamilyCoreTunja.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 15, 0));

    }

    @FXML
    private void handleCreate(ActionEvent event) {
        
        if (isInvalid()) {
            showAlert(AlertType.ERROR, "Error de Validación", "Todos los campos son obligatorios.");
            return;
        }

        try {

            Long identity = Long.parseLong(txtId.getText().trim());

            String password = txtPassword.getText();

            String name = txtName.getText();
            String lastName = txtLastName.getText();
            LocalDate birthDate = dpBirthDate.getValue();
            String birthPlace = txtBirthPlace.getText();
            MaritalStatus maritalStatus = cbMaritalStatus.getValue();
            String addressTunja = txtAddressTunja.getText();
            String phone = txtPhone.getText();
            String permanentAddress = txtPermanentAddress.getText();
            String email = txtEmail.getText();
            String secondLanguage = txtSecondLanguage.getText();
            LocalDate entryDate = dpEntryDate.getValue();
            Integer roomies = spRoomies.getValue();
            Integer familyCore = spFamilyCoreTunja.getValue();
            Long studentTypeId = Long.parseLong(txtStudentTypeId.getText().trim());
            StudentTypeDAO studentTypeDAO = new StudentTypeDAO();
            StudentType studentType = studentTypeDAO.findById(studentTypeId);

            UniversityDAO universityDAO = new UniversityDAO();

            Long academicDataId = identity;
            Long universityId = Long.parseLong(txtIdUni.getText().trim());
            University university = universityDAO.findById(universityId);
            String program = txtProgram.getText();
            Integer semester = spSemester.getValue();
            double average = Double.parseDouble(txtAverage.getText());

            Long healthDataId = identity;
            String diseases = txtDiseases.getText();
            String illness = txtIllness.getText();
            String medications = txtMedications.getText();
            String allergies = txtAllergies.getText();
            double weight = Double.parseDouble(txtWeight.getText().trim());
            double size = Double.parseDouble(txtSize.getText().trim());
            double bmi = weight / Math.pow(size, 2);
            BloodType bloodType = cbBloodType.getValue();

            String legalRepName = txtLegalRepName.getText();
            String legalRepPhone = txtLegalRepPhone.getText();
            String legalRepAddress = txtLegalRepAddress.getText();
            String legalRepCity = txtLegalRepCity.getText();
            LocalDate legalRepBirthDate = dpLegalRepBirthDate.getValue();
            RelationShip legalRepRel = cbLegalRepRelationship.getValue();

            AcademicData academicData = new AcademicData(academicDataId, program, semester, average, university);
            HealthData healthData = new HealthData(healthDataId, diseases, illness, medications, allergies, weight,size, bmi, bloodType);
            LegalRepresentative legalRepresentative = new LegalRepresentative(legalRepName, legalRepPhone,legalRepAddress, legalRepBirthDate, legalRepCity, legalRepRel);

            Student student = new Student(identity, name, lastName, birthDate, password, maritalStatus, birthPlace,addressTunja,permanentAddress, phone, email, secondLanguage, roomies, familyCore, entryDate,healthData, academicData, null, studentType, null, null, legalRepresentative);

            //AcademicDataDAO academicDataDAO = new AcademicDataDAO();
            //HealhDataDAO healhDataDAO = new HealhDataDAO();
            //LegalRepresentativeDAO legalRepresentativeDAO = new LegalRepresentativeDAO();

            studentDAO.save(student);
            //academicDataDAO.save(academicData);
            //healhDataDAO.save(healthData);
            //legalRepresentativeDAO.save(legalRepresentative);

            showAlert(AlertType.INFORMATION, "Estudiante creado", "El estudiante se registro de forma correcta");

            App.setRoot("LoginView");
            
        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Error de Formato", "Algunos campos deben ser numeros");
        } catch (Exception e) {
            System.out.println(e);
            showAlert(AlertType.ERROR, "Error de Creacion", "No se pudo crear el estudiante: " + e.getMessage());
        }
    }

    private boolean isInvalid() {
        if (dpBirthDate.getValue() == null || dpEntryDate.getValue() == null || dpLegalRepBirthDate.getValue() == null)
            return true;

        // revisar <= 0 es necesario
        if (spSemester.getValue() == null || spSemester.getValue() <= 0 || spRoomies.getValue() == null
                || spFamilyCoreTunja.getValue() == null)
            return true;

        ComboBox<?>[] allCombos = { cbMaritalStatus, cbBloodType, cbLegalRepRelationship };

        for (ComboBox<?> combo : allCombos) {
            if (combo == null || combo.getValue() == null) {
                return true;
            }
        }

        TextInputControl[] fields = {
                txtId, txtName, txtLastName, txtBirthPlace,
                txtAddressTunja, txtPermanentAddress, txtPhone,
                txtEmail, txtSecondLanguage, txtPassword,
                txtProgram, txtIdUni, txtAverage,
                txtWeight, txtSize, txtDiseases, txtIllness,
                txtMedications, txtAllergies,
                txtLegalRepName, txtLegalRepPhone,
                txtLegalRepAddress, txtLegalRepCity, txtStudentTypeId
        };

        for (TextInputControl field : fields) {
            if (field == null || field.getText() == null || field.getText().trim().isEmpty()) {
                return true;
            }
        }

        return false;
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
