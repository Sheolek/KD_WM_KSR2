package com.example.KSR2.view.controller;

import com.example.KSR2.logic.Initializer;
import com.example.KSR2.logic.model.House;
import com.example.KSR2.logic.model.Label;
import com.example.KSR2.logic.model.Quantifier;
import com.example.KSR2.logic.model.Summary;
import com.example.KSR2.logic.service.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class MainWindowController implements Initializable {


    private final HouseService houseService;
    private final LinguisticVariableService linguisticVariableService;
    private final QuantifierService quantifierService;
    private final SummarizerService summarizerService;
    private final SummaryService summaryService;

    @FXML
    public AnchorPane listAnchorPane;

    @FXML
    public TableView<House> houseTable;
    public TreeView<Quantifier> quantifiersTree;
    public TreeView<Label> qualifiersTree;
    public TreeView<Label> summarizersTree;
    public Button generateButton;
    public Button resetButton;
    public TextField t1Weight;
    public TextField t2Weight;
    public TextField t3Weight;
    public TextField t4Weight;
    public TextField t5Weight;
    public TextField t6Weight;
    public TextField t7Weight;
    public TextField t8Weight;
    public TextField t9Weight;
    public TextField t10Weight;
    public TextField t11Weight;
    public Tab summary1results;
    public TableView<Summary> houseTable1;

    @Autowired
    public MainWindowController(HouseService houseService, LinguisticVariableService linguisticVariableService,
                                QuantifierService quantifierService, SummarizerService summarizerService,
                                SummaryService summaryService) {

        this.houseService = houseService;
        this.linguisticVariableService = linguisticVariableService;
        this.quantifierService = quantifierService;
        this.summarizerService = summarizerService;
        this.summaryService = summaryService;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeHouseTable();
        Initializer initializer = new Initializer();
        linguisticVariableService.getLinguisticVariableRepository().setVariables(initializer.getVariables());
        quantifierService.getQuantifierRepository().setQuantifiers(initializer.getQuantifiers());
        summarizerService.getSummarizerRepository().setSummarizers(initializer.getSummarizers());
    }

    private void initializeHouseTable() {
        List<House> houses = houseService.getHouses();

        TableColumn<House, String> col0 = new TableColumn<>("ID");
        TableColumn<House, String> col1 = new TableColumn<>("ADDRESS");
        TableColumn<House, String> col2 = new TableColumn<>("SUBURB");
        TableColumn<House, String> col3 = new TableColumn<>("PRICE");
        TableColumn<House, String> col4 = new TableColumn<>("LAND_AREA");
        TableColumn<House, String> col5 = new TableColumn<>("FLOOR_AREA");
        TableColumn<House, String> col6 = new TableColumn<>("BUILD_YEAR");
        TableColumn<House, String> col7 = new TableColumn<>("CBD_DIST");
        TableColumn<House, String> col8 = new TableColumn<>("NEAREST_STN_DIST");
        TableColumn<House, String> col9 = new TableColumn<>("LATITUDE");
        TableColumn<House, String> col10 = new TableColumn<>("LONGITUDE");
        TableColumn<House, String> col11 = new TableColumn<>("NEAREST_SCH_DIST");
        TableColumn<House, String> col12 = new TableColumn<>("NEAREST_SCH_RANK");
        TableColumn<House, String> col13 = new TableColumn<>("LAST_SOLD_TIME");

        col0.setCellValueFactory(new PropertyValueFactory<>("id"));
        col1.setCellValueFactory(new PropertyValueFactory<>("address"));
        col2.setCellValueFactory(new PropertyValueFactory<>("suburb"));
        col3.setCellValueFactory(new PropertyValueFactory<>("price"));
        col4.setCellValueFactory(new PropertyValueFactory<>("landArea"));
        col5.setCellValueFactory(new PropertyValueFactory<>("floorArea"));
        col6.setCellValueFactory(new PropertyValueFactory<>("buildYear"));
        col7.setCellValueFactory(new PropertyValueFactory<>("cbdDist"));
        col8.setCellValueFactory(new PropertyValueFactory<>("nearestStationDist"));
        col9.setCellValueFactory(new PropertyValueFactory<>("latitude"));
        col10.setCellValueFactory(new PropertyValueFactory<>("longitude"));
        col11.setCellValueFactory(new PropertyValueFactory<>("nearestSchoolDist"));
        col12.setCellValueFactory(new PropertyValueFactory<>("nearestSchoolRank"));
        col13.setCellValueFactory(new PropertyValueFactory<>("lastSoldTime"));

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
        houseTable.getColumns().add(col12);
        houseTable.getColumns().add(col13);

        for (House house : houses) {
            houseTable.getItems().add(house);
        }
    }
}
