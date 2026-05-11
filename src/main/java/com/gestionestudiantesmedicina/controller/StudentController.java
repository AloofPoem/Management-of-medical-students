package com.gestionestudiantesmedicina.controller;

import java.time.LocalDate;
import java.util.List;

import com.gestionestudiantesmedicina.daos.StudentDAO;
import com.gestionestudiantesmedicina.daos.UniversityDAO;
import com.gestionestudiantesmedicina.entities.AcademicData;
import com.gestionestudiantesmedicina.entities.HealthData;
import com.gestionestudiantesmedicina.entities.LegalRepresentative;
import com.gestionestudiantesmedicina.entities.Student;
import com.gestionestudiantesmedicina.entities.University;
import com.gestionestudiantesmedicina.enumeration.BloodType;
import com.gestionestudiantesmedicina.enumeration.MaritalStatus;
import com.gestionestudiantesmedicina.enumeration.RelationShip;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
//import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;

public class StudentController {

    @FXML
    private ComboBox<BloodType> cbBloodType;

    @FXML
    private ComboBox<MaritalStatus> cbMaritalStatus;

    @FXML
    private ComboBox<RelationShip> cbLegalRepRelationship;

    @FXML
    private TableView<Student> tableStudents;

    @FXML
    private TableColumn<Student, String> colAddressTunja;

    @FXML
    private TableColumn<Student, String> colPermanentAddress;

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
    private TableColumn<Student, String> colDiseases;

    @FXML
    private TableColumn<Student, String> colIllness;

    @FXML
    private TableColumn<Student, String> colEmail;

    @FXML
    private TableColumn<Student, String> colName;

    @FXML
    private TableColumn<Student, String> colLastName;

    @FXML
    private TableColumn<Student, Long> colIdentity;
    // identity seria el mismo id

    @FXML
    private TableColumn<Student, String> colLegalRepAddress;

    @FXML
    private TableColumn<Student, LocalDate> colLegalRepBirthDate;

    @FXML
    private TableColumn<Student, Long> colLegalRepId;

    @FXML
    private TableColumn<Student, String> colLegalRepCity;

    @FXML
    private TableColumn<Student, RelationShip> colLegalRepRelationship;

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
    private TableColumn<Student, Integer> colSemester;
    // revisar creo que esta como String

    @FXML
    private TableColumn<Student, Double> colWeight;

    @FXML
    private TableColumn<Student, Double> colSize;

    @FXML
    private TableColumn<Student, Double> colBmi;

    @FXML
    private TableColumn<Student, Integer> colRoomies;

    @FXML
    private TableColumn<Student, Integer> colFamilyCoreTunja;

    @FXML
    private TableColumn<Student, String> colIdUni;

    @FXML
    private TableColumn<Student, LocalDate> colEntryDate;
    
    @FXML
    private TableColumn<Student, String> colStudentTypeId;

    @FXML
    private DatePicker dpBirthDate;

    @FXML
    private DatePicker dpLegalRepBirthDate;

    /*
     * Revisar para que sirven
     * 
     * @FXML
     * private Label lblDateTime;
     * 
     * @FXML
     * private Label lblRecordCount;
     * 
     * @FXML
     * private Label lblStatus;
     * 
     * @FXML
     * private Label lblUser;
     */
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
    private DatePicker dpEntryDate;

    @FXML
    private TextField txtIdUni;

    // falta agregarlo en la vista
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
    private ObservableList<Student> studentList = javafx.collections.FXCollections.observableArrayList();

    @FXML
    private void initialize() {

        // Mapeo de datos directos de la entidad Student
        colIdentity.setCellValueFactory(new PropertyValueFactory<>("identityStudent"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colBirthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        colBirthPlace.setCellValueFactory(new PropertyValueFactory<>("birthPlace"));
        colMaritalStatus.setCellValueFactory(new PropertyValueFactory<>("maritalStatus"));
        colAddressTunja.setCellValueFactory(new PropertyValueFactory<>("addressTunja"));
        colPermanentAddress.setCellValueFactory(new PropertyValueFactory<>("permanentAddress"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colSecondLanguage.setCellValueFactory(new PropertyValueFactory<>("secondLanguage"));
        colRoomies.setCellValueFactory(new PropertyValueFactory<>("roomies"));
        colFamilyCoreTunja.setCellValueFactory(new PropertyValueFactory<>("familyCoreTunja"));
        colEntryDate.setCellValueFactory(new PropertyValueFactory<>("entryDate"));

        // Mapeo de datos anidados (HealthData)
        colBloodType.setCellValueFactory(
                cellData -> new SimpleObjectProperty<>(cellData.getValue().getHealthData().getBloodType()));
        colWeight.setCellValueFactory(
                cellData -> new SimpleObjectProperty<>(cellData.getValue().getHealthData().getWeight()));
        colSize.setCellValueFactory(
                cellData -> new SimpleObjectProperty<>(cellData.getValue().getHealthData().getSize()));
        colBmi.setCellValueFactory(
                cellData -> new SimpleObjectProperty<>(cellData.getValue().getHealthData().getBmi()));
        colDiseases.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getHealthData().getGeneralDiseases()));
        colIllness.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getHealthData().getMentalIllness()));
        colAllergies.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getHealthData().getAllergies()));

        // Mapeo de datos anidados (AcademicData)
        colProgram.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getAcademicData().getAcademicProgram()));

       colSemester.setCellValueFactory(cellData -> {
            AcademicData ad = cellData.getValue().getAcademicData();
            Integer semesterValue = (ad != null) ? ad.getSemester() : 0;
            
            return new SimpleObjectProperty<Integer>(semesterValue);
        });
                
        colAverage.setCellValueFactory(
                cellData -> new SimpleObjectProperty<>(cellData.getValue().getAcademicData().getCumulativeAverage()));

        // Mapeo de datos anidados (LegalRepresentative)
        colLegalRepId.setCellValueFactory(
                cellData -> new SimpleObjectProperty<>(cellData.getValue().getLegalRepresentative().getIdLegalRe()));
        colLegalRepName.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getLegalRepresentative().getNameLegalRe()));
        colLegalRepAddress.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getLegalRepresentative().getAddress()));
        colLegalRepBirthDate.setCellValueFactory(
                cellData -> new SimpleObjectProperty<>(cellData.getValue().getLegalRepresentative().getBirthDate()));
        colLegalRepCity.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getLegalRepresentative().getCity()));
        colLegalRepRelationship.setCellValueFactory(
                cellData -> new SimpleObjectProperty<>(cellData.getValue().getLegalRepresentative().getRelationship()));

        colIdUni.setCellValueFactory(cellData -> {
            Student student = cellData.getValue();
            AcademicData ac = student.getAcademicData();
            String studentDisplay = "";
            if (ac.getUniversity() != null) {
                studentDisplay = String.valueOf(ac.getUniversity().getIdUniversity());
            }

            return new SimpleStringProperty(studentDisplay);
        });

        colStudentTypeId.setCellValueFactory(cellData -> {
            Student student = cellData.getValue();
            String studentDisplay = "";
            if (student.getStudentType() != null) {
                studentDisplay = String.valueOf(student.getStudentType().getIdStuType());
            }

            return new SimpleStringProperty(studentDisplay);
        });

        cbMaritalStatus.getItems().setAll(MaritalStatus.values());
        cbBloodType.getItems().setAll(BloodType.values());
        cbLegalRepRelationship.getItems().setAll(RelationShip.values());

        // Inicialización de Spinners
        spSemester.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12, 1));
        spRoomies.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0));
        spFamilyCoreTunja.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 15, 1));

        loadStudentList();

        tableStudents.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        populateForm(newSelection);
                    }
                });

    }

    private void loadStudentList() {
        studentList.clear();
        List<Student> students = studentDAO.findAll();
        studentList.addAll(students);
        tableStudents.setItems(studentList);
    }

    private void populateForm(Student s) {
        // no teacher
        txtId.setText(String.valueOf(s.getIdentityStudent()));
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

        

        spRoomies.getValueFactory().setValue(s.getRoomies());
        spFamilyCoreTunja.getValueFactory().setValue(s.getFamilyCoreTunja());

        //if para los datos anidados
        AcademicData ac = s.getAcademicData();
        txtProgram.setText(ac.getAcademicProgram());
        // try_catch
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
        txtLegalRepId.setText(String.valueOf(lr.getIdLegalRe()));
        txtLegalRepName.setText(lr.getNameLegalRe());
        txtLegalRepPhone.setText(lr.getPhoneNu());
        txtLegalRepAddress.setText(lr.getAddress());
        dpLegalRepBirthDate.setValue(lr.getBirthDate());
        txtLegalRepCity.setText(lr.getCity());
        cbLegalRepRelationship.setValue(lr.getRelationship());

    }

    @FXML
    private void handleClear(ActionEvent e){
        txtId.clear();
        txtName.clear();
        txtLastName.clear();
        txtBirthPlace.clear();
        txtAddressTunja.clear();
        txtPermanentAddress.clear();
        txtPhone.clear();
        txtEmail.clear();
        txtSecondLanguage.clear();
        dpBirthDate.setValue(null);
        dpEntryDate.setValue(null);
        cbMaritalStatus.setValue(null);
        spRoomies.getValueFactory().setValue(0);
        spFamilyCoreTunja.getValueFactory().setValue(0);
        
        txtIdUni.clear();
        txtProgram.clear();
        txtAverage.clear();
        spSemester.getValueFactory().setValue(1);
        
        txtWeight.clear();
        txtSize.clear();
        txtBmi.clear();
        txtDiseases.clear(); // TextArea
        txtIllness.clear();  // TextArea
        txtMedications.clear(); // TextArea
        txtAllergies.clear();   // TextArea
        cbBloodType.setValue(null);
        
        txtLegalRepId.clear();
        txtLegalRepName.clear();
        txtLegalRepPhone.clear();
        txtLegalRepAddress.clear();
        txtLegalRepCity.clear();
        dpLegalRepBirthDate.setValue(null);
        cbLegalRepRelationship.setValue(null);
        
        tableStudents.getSelectionModel().clearSelection();
        loadStudentList();
    }

    @FXML
    private void handleRegister(ActionEvent e){

        Long identity = Long.parseLong(txtId.getText().trim()); 
        String name = txtName.getText();
        String lastName = txtLastName.getText();
        LocalDate birthDate = dpBirthDate.getValue();
        String birthPlace = txtBirthPlace.getText();
        MaritalStatus maritalStatus = cbMaritalStatus.getValue();
        String addressTunja = txtAddressTunja.getText();
        String permanentAddress = txtPermanentAddress.getText();
        String phone = txtPhone.getText();
        String email = txtEmail.getText();
        String secondLanguage = txtSecondLanguage.getText();
        LocalDate entryDate = dpEntryDate.getValue();
        Integer roomies = spRoomies.getValue();
        Integer familyCore = spFamilyCoreTunja.getValue();

        UniversityDAO uDAO = new UniversityDAO();

        Long academicDataId = identity;
        Long universityId = Long.parseLong(txtIdUni.getText().trim());
        University university = uDAO.findById(universityId);
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
        double bmi = Double.parseDouble(txtBmi.getText().trim());
        BloodType bloodType = cbBloodType.getValue();
        
        Long legalRepId = Long.parseLong(txtLegalRepId.getText().trim());
        String legalRepName = txtLegalRepName.getText();
        String legalRepPhone = txtLegalRepPhone.getText();
        String legalRepAddress = txtLegalRepAddress.getText();
        String legalRepCity = txtLegalRepCity.getText();
        LocalDate legalRepBirthDate = dpLegalRepBirthDate.getValue();
        RelationShip legalRepRel = cbLegalRepRelationship.getValue();

        if (isInvalid()) {
            showAlert(AlertType.ERROR, "Error de Validación", "Todos los campos son obligatorios.");
            return;
        }
        
        AcademicData academicData = new AcademicData(identity, program, semester, average, university);
        HealthData healthData = new HealthData(identity, diseases, illness, medications, allergies, weight, size, bmi, bloodType);
        LegalRepresentative legalRepresentative = new LegalRepresentative(legalRepName, legalRepPhone, legalRepAddress, legalRepBirthDate, legalRepCity, legalRepRel);

        Student student = new Student(identity, maritalStatus, birthPlace, addressTunja, permanentAddress, phone, email, secondLanguage, roomies, familyCore, entryDate, healthData, academicData, null, null, null, null, legalRepresentative);
    }

    private boolean isInvalid() {
        if (dpBirthDate.getValue() == null || dpEntryDate.getValue() == null || dpLegalRepBirthDate.getValue() == null) return true;

        ComboBox<?>[] allCombos = {cbMaritalStatus, cbBloodType, cbLegalRepRelationship};

        for (ComboBox<?> combo : allCombos) {
           if (combo == null || combo.getValue() == null) {
                return true;
            }
        }

        TextInputControl[] fields ={
            txtId, txtName, txtLastName, txtBirthPlace, 
            txtAddressTunja, txtPermanentAddress, txtPhone, 
            txtEmail, txtSecondLanguage, txtBmi,
            txtProgram, txtIdUni, txtAverage,
            txtWeight, txtSize, txtDiseases, txtIllness, 
            txtMedications, txtAllergies,
            txtLegalRepId, txtLegalRepName, txtLegalRepPhone, 
            txtLegalRepAddress, txtLegalRepCity
        };

        for (TextInputControl field : fields) {
            //field == null se debe quitar
            if (field == null || field.getText() == null || field.getText().trim() == null) {
                return true;
            }
        }

        try {
            Long.parseLong(txtId.getText().trim());
            Long.parseLong(txtLegalRepId.getText().trim());
            Long.parseLong(txtIdUni.getText().trim());
            Double.parseDouble(txtWeight.getText().trim());
            Double.parseDouble(txtSize.getText().trim());
            Double.parseDouble(txtAverage.getText().trim());
        } catch (NumberFormatException e) {
            return true; 
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