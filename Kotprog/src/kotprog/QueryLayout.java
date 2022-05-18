package kotprog;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class QueryLayout {
    private final String[] propertyName = {"mufaj","ertekeles"};
    private final String[] propertyLabel = {"Műfaj", "Átlagértékelés"};
    private final String[] propertyName2 = {"szinesznev","darab"};
    private final String[] propertyLabel2 = {"Színész neve", "Díjak száma"};
    private final String[] propertyName3 = {"szinesznev"};
    private final String[] propertyLabel3 = {"2017 legmagasabb értékelésű filmjének főszereplője"};

    private final QueryDAO contact = new QueryDAO();
    private final GridPane gridPane = new GridPane();
    private final Label lblName = new Label("Keresés lekérdezés szerint");
    private final TextField searchField = new TextField();
    private FilteredList<Query> filteredData;
    TableView<Query> contactTableView =
            new TableView<>();

    public BorderPane layout(){
        BorderPane ret = new BorderPane();
        gridPane.getChildren().clear();
        ret.getChildren().clear();
        contactTableView.getColumns().clear();

        lblName.setTextFill(Color.web("#0076a3"));
        ObservableList<Query> observableNames = FXCollections.observableArrayList(contact.getQuery());
        observableNames.removeIf(q -> q.getNev() == null); //Kiszed minden olyan Queryt, ahol a név null (lényegében egy GROUP BY javában megírva manuálisan :D)
        filteredData = new FilteredList<>(observableNames, p -> true);
        SortedList<Query> sortedData = new SortedList<>(filteredData);
        ListView<Query> listView1 = new ListView<>(sortedData);

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
                    ListCell<Query>() {
                        @Override
                        protected void updateItem(Query query, boolean empty) {
                            super.updateItem(query, empty);
                            if (query != null && query.getNev() != null) {
                                setText(query.getNev());
                                tooltip.setText(query.getNev());
                                setTooltip(tooltip);
                            } else
                                setText(null);
                        }
                    };
        });
        gridPane.add(listView1, 0, 2);
        // TableView
        ObservableList<Query> contactPeopleList = FXCollections.observableArrayList();

        ret.setCenter(contactTableView);
        ret.setLeft(gridPane);
        // TableView feltöltése a listview-ból, rákattintunk, és betölti, külön lekezeli a lekérdezéseket, mivel nem ugyanannyi attribútumuk van.
        listView1.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (observable != null && observable.getValue() != null) {
                        contactPeopleList.clear();
                        contactPeopleList.addAll(contact.getQueryForNev(newValue.getNev()));
                        contactTableView.setItems(contactPeopleList);
                        contactTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                        switch (newValue.getNev()) {
                            case "1. lekérdezés" -> {
                                contactTableView.getColumns().clear();
                                for (int i = 0; i < propertyLabel.length; i++) {
                                    TableColumn<Query, Object> col = new TableColumn<>(propertyLabel[i]);
                                    col.setCellValueFactory(new PropertyValueFactory<>(propertyName[i]));
                                    contactTableView.getColumns().add(col);
                                }
                            }
                            case "2. lekérdezés" -> {
                                contactTableView.getColumns().clear();
                                for (int i = 0; i < propertyLabel2.length; i++) {
                                    TableColumn<Query, Object> col = new TableColumn<>(propertyLabel2[i]);
                                    col.setCellValueFactory(new PropertyValueFactory<>(propertyName2[i]));
                                    contactTableView.getColumns().add(col);
                                }
                            }
                            case "3. lekérdezés" -> {
                                contactTableView.getColumns().clear();
                                for (int i = 0; i < propertyLabel3.length; i++) {
                                    TableColumn<Query, Object> col = new TableColumn<>(propertyLabel3[i]);
                                    col.setCellValueFactory(new PropertyValueFactory<>(propertyName3[i]));
                                    contactTableView.getColumns().add(col);
                                }
                            }
                        }
                    }
                });

        return ret;
    }
}
