package com.example.KSR2.view.controller;

import com.example.KSR2.logic.model.House;
import com.example.KSR2.logic.service.HouseService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class MainWindowController implements Initializable{


    private final HouseService houseService;

    @FXML
    public AnchorPane listAnchorPane;

    @FXML
    public TableView<House> houseTable;

    @Autowired
    public MainWindowController(HouseService houseService) {
        this.houseService = houseService;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeHouseTable();
    }

    private void initializeHouseTable() {
        List<House> houses = houseService.getHouses();

        TableColumn<House, String> col0 = new TableColumn<>("ID");
        TableColumn<House, String> col1 = new TableColumn<>("House age");
        TableColumn<House, String> col2 = new TableColumn<>("Attrition_Flag");
        TableColumn<House, String> col3 = new TableColumn<>("Months_on_book");
        TableColumn<House, String> col4 = new TableColumn<>("Credit_Limit");
        TableColumn<House, String> col5 = new TableColumn<>("Total_Revolving_Bal");
        TableColumn<House, String> col6 = new TableColumn<>("Avg_Open_To_Buy");
        TableColumn<House, String> col7 = new TableColumn<>("Total_Trans_Amt");
        TableColumn<House, String> col8 = new TableColumn<>("Total_Amt_Chng_Q4_Q1");
        TableColumn<House, String> col9 = new TableColumn<>("Total_Trans_Ct");
        TableColumn<House, String> col10 = new TableColumn<>("Total_Ct_Chng_Q4_Q1");
        TableColumn<House, String> col11 = new TableColumn<>("Avg_Utilization_Ratio");

        col0.setCellValueFactory(new PropertyValueFactory<>("id"));
        col1.setCellValueFactory(new PropertyValueFactory<>("address"));
        col2.setCellValueFactory(new PropertyValueFactory<>("attritionFlag"));
        col3.setCellValueFactory(new PropertyValueFactory<>("monthsOnBook"));
        col4.setCellValueFactory(new PropertyValueFactory<>("creditLimit"));
        col5.setCellValueFactory(new PropertyValueFactory<>("totalRevolvingBal"));
        col6.setCellValueFactory(new PropertyValueFactory<>("avgOpenToBuy"));
        col7.setCellValueFactory(new PropertyValueFactory<>("totalTransAmt"));
        col8.setCellValueFactory(new PropertyValueFactory<>("totalAmtChngQ4Q1"));
        col9.setCellValueFactory(new PropertyValueFactory<>("totalTransCt"));
        col10.setCellValueFactory(new PropertyValueFactory<>("totalCtChngQ4Q1"));
        col11.setCellValueFactory(new PropertyValueFactory<>("avgUtilizationRatio"));

        houseTable.getColumns().add(col0);
        houseTable.getColumns().add(col1);
        houseTable.getColumns().add(col2);
        houseTable.getColumns().add(col3);
        houseTable.getColumns().add(col4);
        houseTable.getColumns().add(col5);
        houseTable.getColumns().add(col6);
        houseTable.getColumns().add(col7);
        houseTable.getColumns().add(col8);
        houseTable.getColumns().add(col9);
        houseTable.getColumns().add(col10);
        houseTable.getColumns().add(col11);

        for (House house : houses) {
            houseTable.getItems().add(house);
        }
    }
}
