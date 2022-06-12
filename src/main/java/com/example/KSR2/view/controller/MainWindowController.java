package com.example.KSR2.view.controller;

import com.example.KSR2.logic.Initializer;
import com.example.KSR2.logic.Summary2TableRecord;
import com.example.KSR2.logic.SummaryTableRecord;
import com.example.KSR2.logic.model.Label;
import com.example.KSR2.logic.model.*;
import com.example.KSR2.logic.model.membershipFunction.Gauss;
import com.example.KSR2.logic.model.membershipFunction.Trapezoidal;
import com.example.KSR2.logic.model.membershipFunction.Triangular;
import com.example.KSR2.logic.service.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
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
    private final Summary2Service summary2Service;
    private List<LinguisticVariable> quantifierVariable;

    @FXML
    public AnchorPane listAnchorPane;
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

    // 2 podmioty
    @FXML
    public TreeView<String> quantifiersTree2;
    public TreeView<String> qualifiersTree2;
    public TreeView<String> summarizersTree2;
    public TableView<Summary2TableRecord> houseTable2;
    public Button generateButton2;
    public Button resetButton2;

    // zaawansowane generowanie
    @FXML
    public TextField nameArg;
    public TextField arg1;
    public TextField arg2;
    public TextField arg3;
    public TextField arg4;
    public javafx.scene.control.Label arg1txt;
    public javafx.scene.control.Label arg2txt;
    public javafx.scene.control.Label arg3txt;
    public javafx.scene.control.Label arg4txt;
    public ChoiceBox<String> chooseType;
    public ChoiceBox<String> chooseVariable;
    public ChoiceBox<String> chooseFuncType;
    public Button createPreview;
    public Button createLabel;
    public StackPane stackPane;
    public LineChart<Number, Number> chart;
    public XYChart.Series<Number, Number> series;

    @Autowired
    public MainWindowController(HouseService houseService, LinguisticVariableService linguisticVariableService,
                                QuantifierService quantifierService, SummarizerService summarizerService,
                                SummaryService summaryService, Summary2Service summary2Service) {

        this.houseService = houseService;
        this.linguisticVariableService = linguisticVariableService;
        this.quantifierService = quantifierService;
        this.summarizerService = summarizerService;
        this.summaryService = summaryService;
        this.summary2Service = summary2Service;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeHouseTable();
        initializeSummaryTable();
        initializeSummaryTable2();
        Initializer initializer = new Initializer();
        linguisticVariableService.getLinguisticVariableRepository().setVariables(initializer.getVariables());
        quantifierService.getQuantifierRepository().setQuantifiers(initializer.getQuantifiers());
        summarizerService.getSummarizerRepository().setSummarizers(initializer.getSummarizers());
        quantifierVariable = initializer.getQuantifiersVariable();
        initializeTrees();
        initializeTrees2();
        initializeGenLists();
    }

    private void initializeHouseTable() {
        List<House> houses = houseService.getHouses();

        TableColumn<House, String> col0 = new TableColumn<>("ID");
        TableColumn<House, String> col1 = new TableColumn<>("ADDRESS");
        TableColumn<House, String> col2 = new TableColumn<>("SUBURB");
        TableColumn<House, String> col3 = new TableColumn<>("BEDROOMS");
        TableColumn<House, String> col4 = new TableColumn<>("PRICE");
        TableColumn<House, String> col5 = new TableColumn<>("LAND_AREA");
        TableColumn<House, String> col6 = new TableColumn<>("FLOOR_AREA");
        TableColumn<House, String> col7 = new TableColumn<>("BUILD_YEAR");
        TableColumn<House, String> col8 = new TableColumn<>("CBD_DIST");
        TableColumn<House, String> col9 = new TableColumn<>("NEAREST_STN_DIST");
        TableColumn<House, String> col10 = new TableColumn<>("LATITUDE");
        TableColumn<House, String> col11 = new TableColumn<>("LONGITUDE");
        TableColumn<House, String> col12 = new TableColumn<>("NEAREST_SCH_DIST");
        TableColumn<House, String> col13 = new TableColumn<>("NEAREST_SCH_RANK");
        TableColumn<House, String> col14 = new TableColumn<>("LAST_SOLD_TIME");

        col0.setCellValueFactory(new PropertyValueFactory<>("id"));
        col1.setCellValueFactory(new PropertyValueFactory<>("address"));
        col2.setCellValueFactory(new PropertyValueFactory<>("suburb"));
        col3.setCellValueFactory(new PropertyValueFactory<>("bedrooms"));
        col4.setCellValueFactory(new PropertyValueFactory<>("price"));
        col5.setCellValueFactory(new PropertyValueFactory<>("landArea"));
        col6.setCellValueFactory(new PropertyValueFactory<>("floorArea"));
        col7.setCellValueFactory(new PropertyValueFactory<>("buildYear"));
        col8.setCellValueFactory(new PropertyValueFactory<>("cbdDist"));
        col9.setCellValueFactory(new PropertyValueFactory<>("nearestStationDist"));
        col10.setCellValueFactory(new PropertyValueFactory<>("latitude"));
        col11.setCellValueFactory(new PropertyValueFactory<>("longitude"));
        col12.setCellValueFactory(new PropertyValueFactory<>("nearestSchoolDist"));
        col13.setCellValueFactory(new PropertyValueFactory<>("nearestSchoolRank"));
        col14.setCellValueFactory(new PropertyValueFactory<>("lastSoldTime"));

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
        houseTable.getColumns().add(col14);

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
        for (int i = 1; i <= summarizers.size() || i <= 3; i++) {
            combinations(summarizers, i, 0, Arrays.asList(new Label[i]), summarizersCombinations);
        }

        List<List<Label>> qualifiersCombinations = new ArrayList<>();
        for (int i = 0; i <= qualifiers.size() || i <= 3; i++) {
            combinations(qualifiers, i, 0, Arrays.asList(new Label[i]), qualifiersCombinations);
        }

        double[] weights = getWeights();
        for (Quantifier quantifier : quantifiers) {
            if (quantifier.getIsRelative()) {
                for (List<Label> tempQualifiers : qualifiersCombinations) {
                    for (List<Label> tempSummarizers : summarizersCombinations) {
                        summaryService.createSummary(quantifier, tempQualifiers, tempSummarizers, weights, houseService.getHouses());
                    }
                }
            } else {
                for (List<Label> tempSummarizers : summarizersCombinations) {
                    summaryService.createSummary(quantifier, new ArrayList<Label>(), tempSummarizers, weights, houseService.getHouses());
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
                Double.parseDouble(t10Weight.getText()),
                Double.parseDouble(t11Weight.getText())
        };
    }


    // 2 podmiotowe
    private void initializeTrees2() {
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
        qualifiersTree2.setRoot(root);
        qualifiersTree2.setCellFactory(CheckBoxTreeCell.forTreeView());

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
        summarizersTree2.setRoot(root);
        summarizersTree2.setCellFactory(CheckBoxTreeCell.forTreeView());

        root = new CheckBoxTreeItem<>("Kwantyfikatory");
        CheckBoxTreeItem<String> variableItem = new CheckBoxTreeItem<>(quantifierVariable.get(0).getName());
        variableItem.setExpanded(true);
        for (Label label : quantifierVariable.get(0).getLabels()) {
            CheckBoxTreeItem<String> labelItem = new CheckBoxTreeItem<>(label.getName());
            variableItem.getChildren().add(labelItem);
        }
        root.getChildren().add(variableItem);
        quantifiersTree2.setRoot(root);
        quantifiersTree2.setCellFactory(CheckBoxTreeCell.forTreeView());
    }

    public void generateSummaries2(ActionEvent actionEvent) {
        List<String> checkedQualifiers = new ArrayList<>();
        List<String> checkedSummarizers = new ArrayList<>();
        List<String> checkedQuantifiers = new ArrayList<>();
        findCheckedItems((CheckBoxTreeItem<?>) qualifiersTree2.getRoot(), checkedQualifiers);
        findCheckedItems((CheckBoxTreeItem<?>) summarizersTree2.getRoot(), checkedSummarizers);
        findCheckedItems((CheckBoxTreeItem<?>) quantifiersTree2.getRoot(), checkedQuantifiers);

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
        for (int i = 1; i <= summarizers.size() || i <= 1; i++) {
            combinations(summarizers, i, 0, Arrays.asList(new Label[i]), summarizersCombinations);
        }

        List<List<Label>> qualifiersCombinations = new ArrayList<>();
        for (int i = 0; i <= qualifiers.size() || i <= 3; i++) {
            combinations(qualifiers, i, 0, Arrays.asList(new Label[i]), qualifiersCombinations);
        }

        List<House> obj1 = houseService.getHouses().stream().filter(x -> x.getBedrooms() <= 3).toList();
        List<House> obj2 = houseService.getHouses().stream().filter(x -> x.getBedrooms() > 3).toList();
        for (List<Label> tempSummarizers : summarizersCombinations) {
            for (Quantifier quantifier : quantifiers) {
                for (List<Label> tempQualifiers : qualifiersCombinations) {
                    if (!tempSummarizers.isEmpty()) {
                        summary2Service.createSummary(quantifier, new ArrayList<Label>(), tempQualifiers, tempSummarizers, obj1, obj2);
                        summary2Service.createSummary(quantifier, new ArrayList<Label>(), tempQualifiers, tempSummarizers, obj2, obj1);
                    }
                    summary2Service.createSummary(quantifier, tempQualifiers, new ArrayList<Label>(), tempSummarizers, obj1, obj2);
                    summary2Service.createSummary(quantifier, tempQualifiers, new ArrayList<Label>(), tempSummarizers, obj2, obj1);
                }
            }
            summary2Service.createSummary(null, new ArrayList<Label>(), new ArrayList<Label>(), tempSummarizers, obj1, obj2);
            summary2Service.createSummary(null, new ArrayList<Label>(), new ArrayList<Label>(), tempSummarizers, obj2, obj1);
        }

        fillSummaryTable2();
    }

    public void resetSummaries2(ActionEvent actionEvent) {
        summary2Service.reset();
        houseTable2.getItems().clear();
    }

    public void fillSummaryTable2() {
        List<Summary2> summaries = summary2Service.getSummaries();

        for (Summary2 summary : summaries) {
            houseTable2.getItems().add(new Summary2TableRecord(summary));
        }
    }

    public void initializeSummaryTable2() {
        TableColumn<Summary2TableRecord, String> col0 = new TableColumn<>("Podsumowanie");
        TableColumn<Summary2TableRecord, String> col1 = new TableColumn<>("T");

        col0.setCellValueFactory(new PropertyValueFactory<>("Summary2"));
        col1.setCellValueFactory(new PropertyValueFactory<>("T"));

        houseTable2.getColumns().add(col0);
        houseTable2.getColumns().add(col1);
    }

    // generating
    public void initializeGenLists() {
        chooseType.getItems().add("Kwantyfikator");
        chooseType.getItems().add("Summaryzator");
        chooseType.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            if (t1.equals("Kwantyfikator")) {
                List<String> names = quantifierVariable.stream().map(LinguisticVariable::getName).toList();
                chooseVariable.setItems(FXCollections.observableList(names));
            }
            if (t1.equals("Summaryzator")) {
                List<String> names = linguisticVariableService.getVariables().stream().map(LinguisticVariable::getName).toList();
                chooseVariable.setItems(FXCollections.observableList(names));
            }
        });
        chooseFuncType.getItems().add("Trapezoidalna");
        chooseFuncType.getItems().add("Trójkątna");
        chooseFuncType.getItems().add("Gaussowska");
        chooseFuncType.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            switch (t1) {
                case "Trapezoidalna" -> {
                    arg1.setDisable(false);
                    arg2.setDisable(false);
                    arg3.setDisable(false);
                    arg4.setDisable(false);
                    arg1txt.setText("Lewa krawędź:");
                    arg2txt.setText("Lewa granica 1:");
                    arg3txt.setText("Prawa granica 1:");
                    arg4txt.setText("Prawa krawędź:");
                }
                case "Trójkątna" -> {
                    arg1.setDisable(false);
                    arg2.setDisable(false);
                    arg3.setDisable(false);
                    arg4.setDisable(true);
                    arg1txt.setText("Lewa krawędź:");
                    arg2txt.setText("Czubek:");
                    arg3txt.setText("Prawa granica:");
                    arg4txt.setText("Argument 4:");
                }
                case "Gaussowska" -> {
                    arg1.setDisable(false);
                    arg2.setDisable(false);
                    arg3.setDisable(true);
                    arg4.setDisable(true);
                    arg1txt.setText("Szerokość dzwona:");
                    arg2txt.setText("Czubek:");
                    arg3txt.setText("Argument 3:");
                    arg4txt.setText("Argument 4:");
                }
            }
        });
        arg1.setDisable(true);
        arg2.setDisable(true);
        arg3.setDisable(true);
        arg4.setDisable(true);
    }


    public void createPreview(ActionEvent actionEvent) {
        String funcType = chooseFuncType.getValue();
        String variableName = chooseVariable.getValue();
        List<LinguisticVariable> variables = linguisticVariableService.getVariables();
        double min = 0;
        double max = 1;
        if (variableName.equals("Względne")) {
            min = 0;
            max = 1;
        } else if (variableName.equals("Bezwględne")) {
            min = 0;
            max = houseService.getHouses().size();
        } else {
            for (LinguisticVariable variable : variables) {
                if (variable.getName().equals(variableName)) {
                    min = variable.getUniverse().getBottom();
                    max = variable.getUniverse().getTop();
                    break;
                }
            }
        }
        NumberAxis x = new NumberAxis();
        NumberAxis y = new NumberAxis();
        chart = new LineChart<>(x, y);
        chart.setTitle("Wykres funkcji przynależności");
        x.setLabel(variableName);
        series = new XYChart.Series<>();
        series.setName("Funkcja " + funcType);
        switch (funcType) {
            case "Trapezoidalna" -> {
                double a = Double.parseDouble(arg1.getText());
                double b = Double.parseDouble(arg2.getText());
                double c = Double.parseDouble(arg3.getText());
                double d = Double.parseDouble(arg4.getText());
                series.getData().add(new XYChart.Data<>(fit(min, max, a), 0));
                series.getData().add(new XYChart.Data<>(fit(min, max, b), 1));
                series.getData().add(new XYChart.Data<>(fit(min, max, c), 1));
                series.getData().add(new XYChart.Data<>(fit(min, max, d), 0));
            }
            case "Trójkątna" -> {
                double a = Double.parseDouble(arg1.getText());
                double b = Double.parseDouble(arg2.getText());
                double c = Double.parseDouble(arg3.getText());
                series.getData().add(new XYChart.Data<>(fit(min, max, a), 0));
                series.getData().add(new XYChart.Data<>(fit(min, max, b), 1));
                series.getData().add(new XYChart.Data<>(fit(min, max, c), 0));
            }
            case "Gaussowska" -> {
                double a = Double.parseDouble(arg1.getText());
                double b = Double.parseDouble(arg2.getText());
                chart.setCreateSymbols(false);
                double x1 = min;
                boolean flag = false;
                boolean loop = true;
                double step;
                if (max <= 1) {
                    step = 0.001;
                } else if (max <= 10) {
                    step = 0.01;
                } else if (max <= 100) {
                    step = 0.1;
                } else {
                    step = 1;
                }
                while (loop) {
                    double val = round(gauss(x1, a, b));
                    if (val > 0) {
                        series.getData().add(new XYChart.Data<>(x1, val));
                        flag = true;
                    }
                    if ((val == 0.0 && flag) || x1 >= max) {
                        loop = false;
                    }
                    x1 += step;
                }
            }
        }
        chart.getData().add(series);
        stackPane.getChildren().clear();
        stackPane.getChildren().add(chart);
        StackPane.setAlignment(chart, Pos.CENTER);
    }

    public void createLabel(ActionEvent actionEvent) {
        String type = chooseType.getValue();
        String funcType = chooseFuncType.getValue();
        String variableName = chooseVariable.getValue();
        List<LinguisticVariable> variables = linguisticVariableService.getVariables();
        double min = 0;
        double max = 1;
        if (type.equals("Kwantyfikator")) {
            LinguisticVariable quantifierType = quantifierVariable.get(0);
            if (variableName.equals("Względne")) {
                min = 0;
                max = 1;
                quantifierType = quantifierVariable.get(0);
            } else if (variableName.equals("Bezwględne")) {
                min = 0;
                max = houseService.getHouses().size();
                quantifierType = quantifierVariable.get(1);
            }
            switch (funcType) {
                case "Trapezoidalna" -> quantifierService.getQuantifierRepository().add(new Quantifier(
                        new Label(nameArg.getText() + " domów", quantifierType, new Trapezoidal(
                                fit(min, max, Double.parseDouble(arg1.getText())),
                                fit(min, max, Double.parseDouble(arg2.getText())),
                                fit(min, max, Double.parseDouble(arg3.getText())),
                                fit(min, max, Double.parseDouble(arg4.getText()))
                        ))
                        , Boolean.TRUE));
                case "Trójkątna" -> quantifierService.getQuantifierRepository().add(new Quantifier(
                        new Label(nameArg.getText() + " domów", quantifierType, new Triangular(
                                fit(min, max, Double.parseDouble(arg1.getText())),
                                fit(min, max, Double.parseDouble(arg2.getText())),
                                fit(min, max, Double.parseDouble(arg3.getText()))
                        ))
                        , Boolean.TRUE));
                case "Gaussowska" -> quantifierService.getQuantifierRepository().add(new Quantifier(
                        new Label(nameArg.getText() + " domów", quantifierType, new Gauss(
                                Double.parseDouble(arg2.getText()),
                                Double.parseDouble(arg1.getText())
                        ))
                        , Boolean.TRUE));
            }
        } else if (type.equals("Summaryzator")) {
            LinguisticVariable summarizerType = variables.get(0);

            for (LinguisticVariable variable : variables) {
                if (variable.getName().equals(variableName)) {
                    min = variable.getUniverse().getBottom();
                    max = variable.getUniverse().getTop();
                    summarizerType = variable;
                    break;
                }
            }
            switch (funcType) {
                case "Trapezoidalna" -> summarizerService.getSummarizerRepository().add(new Label(nameArg.getText(), summarizerType,
                        new Trapezoidal(
                                fit(min, max, Double.parseDouble(arg1.getText())),
                                fit(min, max, Double.parseDouble(arg2.getText())),
                                fit(min, max, Double.parseDouble(arg3.getText())),
                                fit(min, max, Double.parseDouble(arg4.getText()))
                        )));
                case "Trójkątna" -> summarizerService.getSummarizerRepository().add(new Label(nameArg.getText(), summarizerType,
                        new Triangular(
                                fit(min, max, Double.parseDouble(arg1.getText())),
                                fit(min, max, Double.parseDouble(arg2.getText())),
                                fit(min, max, Double.parseDouble(arg3.getText()))
                        )));
                case "Gaussowska" -> summarizerService.getSummarizerRepository().add(new Label(nameArg.getText(), summarizerType,
                        new Gauss(
                                Double.parseDouble(arg2.getText()),
                                Double.parseDouble(arg1.getText())
                        )));
            }
        }
        initializeTrees();
        initializeTrees2();
    }

    private double fit(double min, double max, double fit) {
        if (fit < min) {
            fit = min;
        }
        if (fit > max) {
            fit = max;
        }
        return fit;
    }

    private double gauss(double value, double stdev, double top) {
        double val = Math.pow(1/(stdev * Math.sqrt(2 * Math.PI)),(-0.5 * ((value - top) * (value - top))/(stdev*stdev)));
        if (stdev > 1) {
            val = 1/val;
        }
        return val;
    }
}
