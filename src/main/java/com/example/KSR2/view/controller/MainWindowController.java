package com.example.KSR2.view.controller;

import com.example.KSR2.logic.Initializer;
import com.example.KSR2.logic.SummaryTableRecord;
import com.example.KSR2.logic.model.Label;
import com.example.KSR2.logic.model.*;
import com.example.KSR2.logic.service.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class MainWindowController implements Initializable {


    private final HouseService houseService;
    private final LinguisticVariableService linguisticVariableService;
    private final QuantifierService quantifierService;
    private final SummarizerService summarizerService;
    private final SummaryService summaryService;
    private List<LinguisticVariable> quantifierVariable;

    @FXML
    public AnchorPane listAnchorPane;

    @FXML
    public TableView<House> houseTable;
    public TreeView<String> quantifiersTree;
    public TreeView<String> qualifiersTree;
    public TreeView<String> summarizersTree;
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
    public TableView<SummaryTableRecord> houseTable1;

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
        initializeSummaryTable();
        Initializer initializer = new Initializer();
        linguisticVariableService.getLinguisticVariableRepository().setVariables(initializer.getVariables());
        quantifierService.getQuantifierRepository().setQuantifiers(initializer.getQuantifiers());
        summarizerService.getSummarizerRepository().setSummarizers(initializer.getSummarizers());
        quantifierVariable = initializer.getQuantifiersVariable();
        initializeTrees();
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

    private void initializeTrees() {
        CheckBoxTreeItem<String> root = new CheckBoxTreeItem<>("Kwalifikatory");
        for (LinguisticVariable variable : linguisticVariableService.getVariables()) {
            CheckBoxTreeItem<String> variableItem = new CheckBoxTreeItem<>(variable.getName());
            variableItem.setExpanded(true);
            for (Label label : variable.getLabels()) {
                CheckBoxTreeItem<String> labelItem = new CheckBoxTreeItem<>(label.getName());
                variableItem.getChildren().add(labelItem);
            }
            root.getChildren().add(variableItem);
        }
        qualifiersTree.setRoot(root);
        qualifiersTree.setCellFactory(CheckBoxTreeCell.forTreeView());

        root = new CheckBoxTreeItem<>("Sumaryzatory");
        for (LinguisticVariable variable : linguisticVariableService.getVariables()) {
            CheckBoxTreeItem<String> variableItem = new CheckBoxTreeItem<>(variable.getName());
            variableItem.setExpanded(true);
            for (Label label : variable.getLabels()) {
                CheckBoxTreeItem<String> labelItem = new CheckBoxTreeItem<>(label.getName());
                variableItem.getChildren().add(labelItem);
            }
            root.getChildren().add(variableItem);
        }
        summarizersTree.setRoot(root);
        summarizersTree.setCellFactory(CheckBoxTreeCell.forTreeView());

        root = new CheckBoxTreeItem<>("Kwantyfikatory");
        for (LinguisticVariable variable : quantifierVariable) {
            CheckBoxTreeItem<String> variableItem = new CheckBoxTreeItem<>(variable.getName());
            variableItem.setExpanded(true);
            for (Label label : variable.getLabels()) {
                CheckBoxTreeItem<String> labelItem = new CheckBoxTreeItem<>(label.getName());
                variableItem.getChildren().add(labelItem);
            }
            root.getChildren().add(variableItem);
        }
        quantifiersTree.setRoot(root);
        quantifiersTree.setCellFactory(CheckBoxTreeCell.forTreeView());
    }


    private void findCheckedItems(CheckBoxTreeItem<?> item, List<String> checkedItems) {
        if (item.isSelected()) {
            if (item.getParent() != null && item.getChildren().size() == 0) {
                checkedItems.add(item.getParent().getValue().toString() + ";" + item.getValue().toString());
            }
        }
        for (TreeItem<?> child : item.getChildren()) {
            findCheckedItems((CheckBoxTreeItem<?>) child, checkedItems);
        }
    }

    public void generateSummaries(ActionEvent actionEvent) {
        List<String> checkedQualifiers = new ArrayList<>();
        List<String> checkedSummarizers = new ArrayList<>();
        List<String> checkedQuantifiers = new ArrayList<>();
        findCheckedItems((CheckBoxTreeItem<?>) qualifiersTree.getRoot(), checkedQualifiers);
        findCheckedItems((CheckBoxTreeItem<?>) summarizersTree.getRoot(), checkedSummarizers);
        findCheckedItems((CheckBoxTreeItem<?>) quantifiersTree.getRoot(), checkedQuantifiers);

        List<Label> summarizers = new ArrayList<>();
        List<Label> qualifiers = new ArrayList<>();
        List<Quantifier> quantifiers = new ArrayList<>();

        for (String checkedSummarizer : checkedSummarizers) {
            String[] names = checkedSummarizer.split(";");
            summarizers.add(summarizerService.getSummarizerRepository().getLabelByName(names[1], names[0]));
        }

        for (String checkedQualifier : checkedQualifiers) {
            String[] names = checkedQualifier.split(";");
            qualifiers.add(summarizerService.getSummarizerRepository().getLabelByName(names[1], names[0]));
        }

        for (String checkedQuantifier : checkedQuantifiers) {
            String[] names = checkedQuantifier.split(";");
            quantifiers.add(quantifierService.getQuantifierRepository().getQuantifierByName(names[1]));
        }

        List<List<Label>> summarizersCombinations = new ArrayList<>();
        for (int i = 1; i <= summarizers.size(); i++) {
            combinations(summarizers, i, 0, Arrays.asList(new Label[i]), summarizersCombinations);
        }

        List<List<Label>> qualifiersCombinations = new ArrayList<>();
        for (int i = 0; i <= qualifiers.size(); i++) {
            combinations(qualifiers, i, 0, Arrays.asList(new Label[i]), qualifiersCombinations);
        }

        double[] weights = getWeights();
        for (Quantifier quantifier : quantifiers) {
            for (List<Label> tempQualifiers : qualifiersCombinations) {
                for (List<Label> tempSummarizers : summarizersCombinations) {
                    summaryService.createSummary(quantifier, tempQualifiers, tempSummarizers, getWeights());
                }
            }
        }

        fillSummaryTable();
    }

    static void combinations(List<Label> arr, int len, int startPosition, List<Label> result, List<List<Label>> labels) {
        if (len == 0) {
            labels.add(result);
            return;
        }
        for (int i = startPosition; i <= arr.size() - len; i++) {
            result.set(result.size() - len, arr.get(i));
            List<Label> next = new ArrayList<>(result);
            combinations(arr, len - 1, i + 1, next, labels);
        }
    }

    public void resetSummaries(ActionEvent actionEvent) {
        summaryService.reset();
        clearSummaryTable();
    }

    public void initializeSummaryTable() {
        TableColumn<SummaryTableRecord, String> podsumowanie = new TableColumn<>("Podsumowanie");
        TableColumn<SummaryTableRecord, String> col0 = new TableColumn<>("T");
        TableColumn<SummaryTableRecord, String> col1 = new TableColumn<>("T1");
        TableColumn<SummaryTableRecord, String> col2 = new TableColumn<>("T2");
        TableColumn<SummaryTableRecord, String> col3 = new TableColumn<>("T3");
        TableColumn<SummaryTableRecord, String> col4 = new TableColumn<>("T4");
        TableColumn<SummaryTableRecord, String> col5 = new TableColumn<>("T5");
        TableColumn<SummaryTableRecord, String> col6 = new TableColumn<>("T6");
        TableColumn<SummaryTableRecord, String> col7 = new TableColumn<>("T7");
        TableColumn<SummaryTableRecord, String> col8 = new TableColumn<>("T8");
        TableColumn<SummaryTableRecord, String> col9 = new TableColumn<>("T9");
        TableColumn<SummaryTableRecord, String> col10 = new TableColumn<>("T10");
        TableColumn<SummaryTableRecord, String> col11 = new TableColumn<>("T11");

        podsumowanie.setCellValueFactory(new PropertyValueFactory<>("Summary"));
        col0.setCellValueFactory(new PropertyValueFactory<>("T"));
        col1.setCellValueFactory(new PropertyValueFactory<>("T1"));
        col2.setCellValueFactory(new PropertyValueFactory<>("T2"));
        col3.setCellValueFactory(new PropertyValueFactory<>("T3"));
        col4.setCellValueFactory(new PropertyValueFactory<>("T4"));
        col5.setCellValueFactory(new PropertyValueFactory<>("T5"));
        col6.setCellValueFactory(new PropertyValueFactory<>("T6"));
        col7.setCellValueFactory(new PropertyValueFactory<>("T7"));
        col8.setCellValueFactory(new PropertyValueFactory<>("T8"));
        col9.setCellValueFactory(new PropertyValueFactory<>("T9"));
        col10.setCellValueFactory(new PropertyValueFactory<>("T10"));
        col11.setCellValueFactory(new PropertyValueFactory<>("T11"));

        houseTable1.getColumns().add(podsumowanie);
        houseTable1.getColumns().add(col0);
        houseTable1.getColumns().add(col1);
        houseTable1.getColumns().add(col2);
        houseTable1.getColumns().add(col3);
        houseTable1.getColumns().add(col4);
        houseTable1.getColumns().add(col5);
        houseTable1.getColumns().add(col6);
        houseTable1.getColumns().add(col7);
        houseTable1.getColumns().add(col8);
        houseTable1.getColumns().add(col9);
        houseTable1.getColumns().add(col10);
        houseTable1.getColumns().add(col11);
    }

    public void fillSummaryTable() {
        List<Summary> summaries = summaryService.getSummaries();

        for (Summary summary : summaries) {
            Measures measures = summary.getMeasures();
            houseTable1.getItems().add(
                    new SummaryTableRecord(
                            summary.toString(),
                            round(measures.getGoodnessOfSummary()),
                            round(measures.getT1degreeOfTruth()),
                            round(measures.getT2degreeOfImprecision()),
                            round(measures.getT3degreeOfCovering()),
                            round(measures.getT4degreeOfAppropriateness()),
                            round(measures.getT5lengthOfSummary()),
                            round(measures.getT6degreeOfQuantifierImprecision()),
                            round(measures.getT7degreeOfQuantifierCardinality()),
                            round(measures.getT8degreeOfSummarizerCardinality()),
                            round(measures.getT9degreeOfQualifierImprecision()),
                            round(measures.getT10degreeOfQualifierCardinality()),
                            round(measures.getT11lengthOfQualifier())
                    )
            );
        }
    }

    private double round(double val) {
        return Math.round(val * 100.0) / 100.0;
    }

    public void clearSummaryTable() {
        houseTable1.getItems().clear();
    }

    private double[] getWeights() {
        return new double[]{
                Double.parseDouble(t1Weight.getText()),
                Double.parseDouble(t2Weight.getText()),
                Double.parseDouble(t3Weight.getText()),
                Double.parseDouble(t4Weight.getText()),
                Double.parseDouble(t5Weight.getText()),
                Double.parseDouble(t6Weight.getText()),
                Double.parseDouble(t7Weight.getText()),
                Double.parseDouble(t8Weight.getText()),
                Double.parseDouble(t9Weight.getText()),
                Double.parseDouble(t10Weight.getText())
        };
    }
}
