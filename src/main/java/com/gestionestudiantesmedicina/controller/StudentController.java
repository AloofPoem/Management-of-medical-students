package com.gestionestudiantesmedicina.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
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
    private TableColumn<Student, String> colMedications;

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
    private TextField txtId;
    
    //password no tiene col para que no se pueda ver asi como asi
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
    private ObservableList<Student> studentList = javafx.collections.FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // completo 33/33
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
        colMedications.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getHealthData().getMedications()));

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
        colLegalRepPhone.setCellValueFactory(
                cellData -> new SimpleObjectProperty<>(cellData.getValue().getLegalRepresentative().getPhoneNu()));

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
                studentDisplay = String.valueOf(student.getStudentType().getNameStuType());
                // si se cambia por .getIdStuType se entrega el numero, como se ve en id
                // university
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
        // completo 33
        txtPassword.setText(s.getPassword());
        txtId.setText(String.valueOf(s.getId()));
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
        // podria mostrarse el nombre en vez del id ?

        spRoomies.getValueFactory().setValue(s.getRoomies());
        spFamilyCoreTunja.getValueFactory().setValue(s.getFamilyCoreTunja());

        // if para los datos anidados
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
        txtLegalRepName.setText(lr.getNameLegalRe());
        txtLegalRepPhone.setText(lr.getPhoneNu());
        txtLegalRepAddress.setText(lr.getAddress());
        dpLegalRepBirthDate.setValue(lr.getBirthDate());
        txtLegalRepCity.setText(lr.getCity());
        cbLegalRepRelationship.setValue(lr.getRelationship());

    }

    @FXML
    private void handleClear(ActionEvent e) {
        // completo 33
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
        txtStudentTypeId.clear();

        txtIdUni.clear();
        txtProgram.clear();
        txtAverage.clear();
        spSemester.getValueFactory().setValue(1);

        txtWeight.clear();
        txtSize.clear();
        txtBmi.clear();
        txtDiseases.clear(); // TextArea
        txtIllness.clear(); // TextArea
        txtMedications.clear(); // TextArea
        txtAllergies.clear(); // TextArea
        cbBloodType.setValue(null);

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
    private void handleCreate(ActionEvent event) {
        // completo 33
        try {
            if (isInvalid()) {
                showAlert(AlertType.ERROR, "Error de Validación", "Todos los campos son obligatorios.");
                return;
            }

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
            HealthData healthData = new HealthData(healthDataId, diseases, illness, medications, allergies, weight,
                    size, bmi, bloodType);
            LegalRepresentative legalRepresentative = new LegalRepresentative(legalRepName, legalRepPhone,
                    legalRepAddress, legalRepBirthDate, legalRepCity, legalRepRel);

            Student student = new Student(identity, name, lastName, birthDate, password, maritalStatus, birthPlace, addressTunja, permanentAddress, phone, email, secondLanguage, roomies, familyCore, entryDate, healthData, academicData, null, studentType, null, null, legalRepresentative);

            studentDAO.save(student);

            loadStudentList();
            handleClear(null);
        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Error de Formato", "Algunos campos deben ser numeros");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            showAlert(AlertType.ERROR, "Error de Creacion", "No se pudo crear el estudiante: " + e.getMessage());
        }
    }

    @FXML
    private void handleUpdate(ActionEvent event) {
        // completo 33
        if (isInvalid()) {
            showAlert(AlertType.ERROR, "Error de Validación", "Todos los campos deben tener datos");
            return;
        }
        try {
            Long idStudent = Long.parseLong(txtId.getText().trim());

            String password = txtPassword.getText();

            Student student = studentDAO.findById(idStudent);
            AcademicData academicData = student.getAcademicData();
            HealthData healthData = student.getHealthData();
            LegalRepresentative legalRepresentative = student.getLegalRepresentative();

            if (student == null || academicData == null || healthData == null || legalRepresentative == null) {
                showAlert(AlertType.ERROR, "Error", "Estudiante no encontrado con ID: " + idStudent);
                return;
            }

            student.setId(Long.parseLong(txtId.getText().trim()));
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

            loadStudentList();
            handleClear(null);

        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Error de Formato", "Algunos campos deben ser numeros");
        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Error de Actualización", "No se pudo actualizar: " + e.getMessage());
        }
    }

    @FXML
    private void handleDelete(ActionEvent event) {
        try {
            Long id = Long.parseLong(txtId.getText());

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmar Eliminación");
            alert.setHeaderText("¿Está seguro de que desea eliminar al Estudiante con ID " + id + "?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                studentDAO.delete(id);
                loadStudentList();
                handleClear(null);
            }

        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Error de Formato", "El ID debe ser un número.");
        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Error de Eliminación", "No se pudo eliminar: " + e.getMessage());
        }
    }

    @FXML
    private void handleSearch(ActionEvent event) {
        try {
            Long id = Long.parseLong(txtId.getText());
            Student student = studentDAO.findById(id);

            if (student != null) {
                populateForm(student);
                tableStudents.getItems().setAll(student);
                tableStudents.getSelectionModel().select(student);
            } else {
                showAlert(AlertType.INFORMATION, "Búsqueda", "Estudiante no encontrado con ID: " + id);
            }

        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Error de Formato", "El ID debe ser un número.");
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

    @FXML
    private void handleEnviar(ActionEvent event) {
        String fxmlName = "viewRelative";

        try {
            // tambien se podria intentar enviar de una vez el estudiante, pero al final el
            // id hace lo mismo
            Long id = Long.parseLong(txtId.getText().trim());
            if (studentDAO.findById(id) == null) {
                showAlert(AlertType.ERROR, "Error", "Debe seleccionar un estudiante");
                return;
            }
            FXMLLoader loader = new FXMLLoader(App.class.getResource(fxmlName + ".fxml"));
            Parent view = loader.load();

            RelativeController relativeController = new RelativeController();
            relativeController.setStudentId(id);
            /*
             * No se si sea necesario o si se pueda hacer asi
             * MainController mainController = new MainController();
             * mainMenuPane.setCenter(view);
             */

        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Error de formato", "El ID debe ser un número.");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(AlertType.INFORMATION, "Error al Cargar", "No se pudo cargar la vista: " + fxmlName + ".fxml");
        }

    }
}