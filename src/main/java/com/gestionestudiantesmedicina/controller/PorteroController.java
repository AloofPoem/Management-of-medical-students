package com.gestionestudiantesmedicina.controller;

import java.util.List;

import com.gestionestudiantesmedicina.daos.RecordDAO;
import com.gestionestudiantesmedicina.entities.Record;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PorteroController {

    @FXML
    private TableView<Record> tableRecords;

    @FXML
    private TableColumn<Record, Long> colRecordId;

    @FXML
    private TableColumn<Record, String> colDate;

    @FXML
    private TableColumn<Record, String> colTimeIn;

    @FXML
    private TableColumn<Record, String> colTimeOut;

    private RecordDAO recordDao= new RecordDAO();

    private ObservableList<Record> recordList =
            FXCollections.observableArrayList();

    @FXML
    private void initialize() {

        colRecordId.setCellValueFactory(
                new PropertyValueFactory<>("idRecord"));

        colDate.setCellValueFactory(
                new PropertyValueFactory<>("date"));

        colTimeIn.setCellValueFactory(
                new PropertyValueFactory<>("timeIn"));

        colTimeOut.setCellValueFactory(
                new PropertyValueFactory<>("timeOut"));

        loadRecordList();
    }

    private void loadRecordList() {

        recordList.clear();

        List<Record> records = recordDao.findAll();

        recordList.addAll(records);

        tableRecords.setItems(recordList);
    }
}