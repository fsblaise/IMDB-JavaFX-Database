package kotprog;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class DijakLayout {
    private final String[] propertyName = {"szineszid","szinesznev", "dij", "darab"};
    private final String[] propertyLabel = {"Színész ID-je", "Színész neve", "Díj", "Darab"};
    private final DijakDAO contact = new DijakDAO();
    private final GridPane gridPane = new GridPane();
    private final Label lblName = new Label("Keresés díj szerint");
    private final TextField searchField = new TextField();
    private FilteredList<Dijak> filteredData;
    TableView<Dijak> contactTableView =
            new TableView<>();
    private final HBox funPane = new HBox(10);

    public BorderPane layout(){
        BorderPane ret = new BorderPane();
        gridPane.getChildren().clear();
        ret.getChildren().clear();
        funPane.getChildren().clear();
        contactTableView.getColumns().clear();

        lblName.setTextFill(Color.web("#0076a3"));
        ObservableList<Dijak> observableNames = FXCollections.observableArrayList(contact.getDijak());
        filteredData = new FilteredList<>(observableNames, p -> true);
        SortedList<Dijak> sortedData = new SortedList<>(filteredData);
        ListView<Dijak> listView1 = new ListView<>(sortedData);

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
                            return str.getDij().toLowerCase().contains
                                    (newValue.toLowerCase());
                        }));
        listView1.getSelectionModel().setSelectionMode
                (SelectionMode.SINGLE);
        listView1.setPrefHeight(Integer.MAX_VALUE);

        listView1.setCellFactory(listView -> {
            Tooltip tooltip = new Tooltip();
            return new
                    ListCell<Dijak>() {
                        @Override
                        protected void updateItem(Dijak Dijak, boolean empty) {
                            super.updateItem(Dijak, empty);
                            if (Dijak != null) {
                                setText(Dijak.getDij());
                                tooltip.setText(Dijak.getDij() + Integer.toString(Dijak.getDarab()) + "db van.");
                                setTooltip(tooltip);
                            } else
                                setText(null);
                        }
                    };
        });
        gridPane.add(listView1, 0, 2);
        // TableView
        ObservableList<Dijak> contactPeopleList = FXCollections.observableArrayList();
        contactTableView.setItems(contactPeopleList);
        contactTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        for (int i = 0; i < propertyLabel.length; i++) {
            TableColumn<Dijak, Object> col = new TableColumn<>(propertyLabel[i]);
            col.setCellValueFactory(new PropertyValueFactory<>(propertyName[i]));
            contactTableView.getColumns().add(col);
        }

        Button admin = new Button("Adminisztráció");
        admin.setOnAction(e -> {
            AlertBox.display(propertyLabel, propertyName, "dijak");
        });
        funPane.getChildren().add(admin);
        funPane.setAlignment(Pos.BOTTOM_CENTER);
        ret.setBottom(funPane);

        ret.setCenter(contactTableView);
        ret.setLeft(gridPane);

        // TableView feltöltése a listview-ból, rákattintunk, és betölti
        listView1.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (observable != null && observable.getValue() != null) {
                        contactPeopleList.clear();
                        contactPeopleList.addAll(contact.getDijakForNev(newValue.getDij()));
                    }
                });

        return ret;
    }
}
