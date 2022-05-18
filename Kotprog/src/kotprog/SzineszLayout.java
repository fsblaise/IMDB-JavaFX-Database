package kotprog;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class SzineszLayout {
    private final String[] propertyName = {"szineszid", "nev", "szuldatum", "eletkor", "elhunyt"};
    private final String[] propertyLabel = {"Színész ID", "Név", "Születési dátum", "Életkor", "Elhunyt"};
    private final SzineszDAO contact = new SzineszDAO();
    private final GridPane gridPane = new GridPane();
    private final Label lblName = new Label("Keresés név szerint");
    private final TextField searchField = new TextField();
    private FilteredList<Szinesz> filteredData;
    TableView<Szinesz> contactTableView =
            new TableView<>();
    //funkciopanel
    private final HBox funPane = new HBox(10);

    public BorderPane layout(){
        BorderPane ret = new BorderPane();
        gridPane.getChildren().clear();
        ret.getChildren().clear();
        funPane.getChildren().clear();
        contactTableView.getColumns().clear();

        lblName.setTextFill(Color.web("#0076a3"));
        ObservableList<Szinesz> observableNames = FXCollections.observableArrayList(contact.getSzinesz());
        filteredData = new FilteredList<>(observableNames, p -> true);
        SortedList<Szinesz> sortedData = new SortedList<>(filteredData);
        ListView<Szinesz> listView1 = new ListView<>(sortedData);

        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.add(lblName, 0, 0);
        gridPane.add(searchField, 0, 1);
        // Keresőmező, keresés
        searchField.textProperty()
                .addListener((observable, oldValue, newValue) ->
                        filteredData.setPredicate(str -> {
                            if (newValue == null || newValue.isEmpty())
                                return true;
                            return str.getNev().toLowerCase().contains
                                    (newValue.toLowerCase());
                        }));
        listView1.getSelectionModel().setSelectionMode
                (SelectionMode.SINGLE);
        listView1.setPrefHeight(Integer.MAX_VALUE);

        listView1.setCellFactory(listView -> {
            Tooltip tooltip = new Tooltip();
            return new
                    ListCell<Szinesz>() {
                        @Override
                        protected void updateItem(Szinesz szinesz, boolean empty) {
                            super.updateItem(szinesz, empty);
                            if (szinesz != null) {
                                setText(szinesz.getNev());
                                tooltip.setText(szinesz.getNev() + Integer.toString(szinesz.getEletkor()) + "éves.");
                                setTooltip(tooltip);
                            } else
                                setText(null);
                        }
                    };
        });
        gridPane.add(listView1, 0, 2);

        // TableView
        ObservableList<Szinesz> contactPeopleList = FXCollections.observableArrayList();
        contactTableView.setItems(contactPeopleList);
        contactTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        for (int i = 0; i < propertyLabel.length; i++) {
            TableColumn<Szinesz, Object> col = new TableColumn<>(propertyLabel[i]);
            col.setCellValueFactory(new PropertyValueFactory<>(propertyName[i]));
            contactTableView.getColumns().add(col);
        }
        Button admin = new Button("Adminisztráció");
        admin.setOnAction(e -> {
            AlertBox.display(propertyLabel, propertyName, "szinesz");
        });
        funPane.getChildren().add(admin);
        funPane.setAlignment(Pos.BOTTOM_CENTER);

        ret.setCenter(contactTableView);
        ret.setLeft(gridPane);
        ret.setBottom(funPane);

        // TableView feltöltése a listview-ból, rákattintunk, és betölti
        listView1.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (observable != null && observable.getValue() != null) {
                        contactPeopleList.clear();
                        contactPeopleList.addAll(contact.getSzineszForNev(newValue.getNev()));
                    }
                });

        return ret;
    }
}
