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

public class FilmstudioLayout {
    private final String[] propertyName = {"studioid", "nev", "alapitas", "oscarszam"};
    private final String[] propertyLabel = {"Stúdió ID", "Név", "Alapítás", "Oscar-díjak száma"};
    private final FilmstudioDAO contact = new FilmstudioDAO();
    private final GridPane gridPane = new GridPane();
    private final Label lblName = new Label("Keresés név szerint");
    private final TextField searchField = new TextField();
    private FilteredList<Filmstudio> filteredData;
    TableView<Filmstudio> contactTableView =
            new TableView<>();
    private final HBox funPane = new HBox(10);

    public BorderPane layout(){
        BorderPane ret = new BorderPane();
        gridPane.getChildren().clear();
        ret.getChildren().clear();
        funPane.getChildren().clear();
        contactTableView.getColumns().clear();

        lblName.setTextFill(Color.web("#0076a3"));
        ObservableList<Filmstudio> observableNames = FXCollections.observableArrayList(contact.getFilmstudio());
        filteredData = new FilteredList<>(observableNames, p -> true);
        SortedList<Filmstudio> sortedData = new SortedList<>(filteredData);
        ListView<Filmstudio> listView1 = new ListView<>(sortedData);

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
                    ListCell<Filmstudio>() {
                        @Override
                        protected void updateItem(Filmstudio Filmstudio, boolean empty) {
                            super.updateItem(Filmstudio, empty);
                            if (Filmstudio != null) {
                                setText(Filmstudio.getNev());
                                tooltip.setText(Filmstudio.getNev() + Integer.toString(Filmstudio.getOscarszam()) + " Oscar-díj");
                                setTooltip(tooltip);
                            } else
                                setText(null);
                        }
                    };
        });
        gridPane.add(listView1, 0, 2);
        // TableView
        ObservableList<Filmstudio> contactPeopleList = FXCollections.observableArrayList();
        contactTableView.setItems(contactPeopleList);
        contactTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        for (int i = 0; i < propertyLabel.length; i++) {
            TableColumn<Filmstudio, Object> col = new TableColumn<>(propertyLabel[i]);
            col.setCellValueFactory(new PropertyValueFactory<>(propertyName[i]));
            contactTableView.getColumns().add(col);
        }

        Button admin = new Button("Adminisztráció");
        admin.setOnAction(e -> {
            AlertBox.display(propertyLabel, propertyName, "filmstudio");
        });
        funPane.getChildren().add(admin);
        funPane.setAlignment(Pos.BOTTOM_CENTER);
        ret.setBottom(funPane);

        ret.setCenter(contactTableView);
        ret.setLeft(gridPane);

        //TableView feltöltése a listview-ból, rákattintunk, és betölti
        listView1.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (observable != null && observable.getValue() != null) {
                        contactPeopleList.clear();
                        contactPeopleList.addAll(contact.getFilmstudioForNev(newValue.getNev()));
                    }
                });

        return ret;
    }
}
