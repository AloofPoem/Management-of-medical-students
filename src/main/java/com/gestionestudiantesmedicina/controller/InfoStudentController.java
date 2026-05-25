package com.gestionestudiantesmedicina.controller;

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

public class InfoStudentController {

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

    @FXML
    private TextField txtTotalHours;

    private Long idStudent;
    private StudentDAO studentDAO = new StudentDAO();

    public void setId(Long idS) {
        this.idStudent = idS;
        
        Student student = studentDAO.findById(idStudent);
        populateForm(student);
    }

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

    private void populateForm(Student s) {
        txtId.setText(String.valueOf(s.getId()));
        txtPassword.setText(s.getPassword());
        txtName.setText(s.getName());
        txtLastName.setText(s.getLastName());
        dpBirthDate.setValue(s.getBirthDate());
        cbMaritalStatus.setValue(s.getMaritalStatus());
        txtBirthPlace.setText(s.getBirthPlace());
        txtAddressTunja.setText(s.getAddressTunja());
        txtPermanentAddress.setText(s.getPermanentAddress());
        txtPhone.setText(s.getPhoneNumber());
        txtEmail.setText(s.getEmail());
        txtSecondLanguage.setText(s.getSecondLanguage());
        dpEntryDate.setValue(s.getEntryDate());
        txtStudentTypeId.setText(s.getStudentType() != null ? String.valueOf(s.getStudentType().getIdStuType()) : "");
        txtTotalHours.setText(String.valueOf(s.getTotalHours()));

        spRoomies.getValueFactory().setValue(s.getRoomies());
        spFamilyCoreTunja.getValueFactory().setValue(s.getFamilyCoreTunja());

        AcademicData ac = s.getAcademicData();
        txtProgram.setText(ac.getAcademicProgram());

        spSemester.getValueFactory().setValue(ac.getSemester());
        txtAverage.setText(String.valueOf(ac.getCumulativeAverage()));
        txtIdUni.setText(ac.getUniversity() != null ? String.valueOf(ac.getUniversity().getIdUniversity()) : "");

        HealthData hd = s.getHealthData();
        txtDiseases.setText(hd.getGeneralDiseases());
        txtIllness.setText(hd.getMentalIllness());
        txtMedications.setText(hd.getMedications());
        txtAllergies.setText(hd.getAllergies());
        txtWeight.setText(String.valueOf(hd.getWeight()));
        txtSize.setText(String.valueOf(hd.getSize()));
        txtBmi.setText(String.valueOf(hd.getBmi()));
        cbBloodType.setValue(hd.getBloodType());

        LegalRepresentative lr = s.getLegalRepresentative();
        txtLegalRepName.setText(lr.getNameLegalRe());
        txtLegalRepPhone.setText(lr.getPhoneNu());
        txtLegalRepAddress.setText(lr.getAddress());
        dpLegalRepBirthDate.setValue(lr.getBirthDate());
        txtLegalRepCity.setText(lr.getCity());
        cbLegalRepRelationship.setValue(lr.getRelationship());

    }

    @FXML
    private void handleUpdate(ActionEvent event) {
        if (isInvalid()) {
            showAlert(AlertType.ERROR, "Error de Validación", "Todos los campos deben tener datos");
            return;
        }

        try {

            //Long idStudent = Long.parseLong(txtId.getText().trim());

            String password = txtPassword.getText();

            Student student = studentDAO.findById(idStudent);
            AcademicData academicData = student.getAcademicData();
            HealthData healthData = student.getHealthData();
            LegalRepresentative legalRepresentative = student.getLegalRepresentative();

            if (student == null || academicData == null || healthData == null || legalRepresentative == null) {
                showAlert(AlertType.ERROR, "Error", "Estudiante no encontrado con ID: " + idStudent);
                return;
            }

            //student.setId(Long.parseLong(txtId.getText().trim()));
            student.setPassword(password);
            student.setName(txtName.getText().trim());
            student.setLastName(txtLastName.getText().trim());
            student.setBirthDate(dpBirthDate.getValue());
            student.setBirthPlace(txtBirthPlace.getText().trim());
            student.setAddressTunja(txtAddressTunja.getText().trim());
            student.setPermanentAddress(txtPermanentAddress.getText().trim());
            student.setPhoneNumber(txtPhone.getText().trim());
            student.setEmail(txtEmail.getText().trim());
            student.setSecondLanguage(txtSecondLanguage.getText().trim());
            student.setMaritalStatus(cbMaritalStatus.getValue());
            student.setEntryDate(dpEntryDate.getValue());
            student.setRoomies(spRoomies.getValue());
            student.setFamilyCoreTunja(spFamilyCoreTunja.getValue());

            academicData.setAcademicProgram(txtProgram.getText().trim());
            academicData.setCumulativeAverage(Double.parseDouble(txtAverage.getText().trim()));
            academicData.setSemester(spSemester.getValue());

            UniversityDAO universityDAO = new UniversityDAO();
            University university = universityDAO.findById(Long.parseLong(txtIdUni.getText().trim()));
            academicData.setUniversity(university);

            StudentTypeDAO studentTypeDAO = new StudentTypeDAO();
            StudentType studentType = studentTypeDAO.findById(Long.parseLong(txtStudentTypeId.getText().trim()));
            student.setStudentType(studentType);

            healthData.setBloodType(cbBloodType.getValue());
            healthData.setWeight(Double.parseDouble(txtWeight.getText().trim()));
            healthData.setSize(Double.parseDouble(txtSize.getText().trim()));
            healthData.setBmi(healthData.getWeight() / Math.pow(healthData.getSize(), 2));
            healthData.setGeneralDiseases(txtDiseases.getText().trim());
            healthData.setMentalIllness(txtIllness.getText().trim());
            healthData.setMedications(txtMedications.getText().trim());
            healthData.setAllergies(txtAllergies.getText().trim());

            legalRepresentative.setNameLegalRe(txtLegalRepName.getText().trim());
            legalRepresentative.setPhoneNu(txtLegalRepPhone.getText().trim());
            legalRepresentative.setAddress(txtLegalRepAddress.getText().trim());
            legalRepresentative.setCity(txtLegalRepCity.getText().trim());
            legalRepresentative.setBirthDate(dpLegalRepBirthDate.getValue());
            legalRepresentative.setRelationship(cbLegalRepRelationship.getValue());

            studentDAO.update(student);

        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Error de Formato", "Algunos campos deben ser numeros");
        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Error de Actualización", "No se pudo actualizar: " + e.getMessage());
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

        //se quito txtId
        TextInputControl[] fields = {
                 txtName, txtLastName, txtBirthPlace,
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
