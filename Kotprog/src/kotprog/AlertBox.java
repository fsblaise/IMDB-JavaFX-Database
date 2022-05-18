package kotprog;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class AlertBox implements DAO{
    public static void display(String[] labels, String[] names, String tableName){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Adminisztráció");
        window.setMinWidth(350);
        window.setMinHeight(150);

        VBox layout = new VBox(10);
        HBox buttons = new HBox(50);
        HBox inputs = new HBox(10);
        VBox[] vBoxes = new VBox[labels.length];
        TextField[] textFields = new TextField[labels.length];
        for (int i = 0; i < labels.length; i++) {
            textFields[i] = new TextField();
            Label name = new Label(labels[i]);
            vBoxes[i] = new VBox();
            vBoxes[i].getChildren().addAll(textFields[i],name);
        }
        //Ellenorzo box, ide lehet beírni a módosítandó elem id-jét
        VBox idBox = new VBox();
        TextField idField = new TextField();
        Label idLabel = new Label("Módosítandó ID");
        idBox.getChildren().addAll(idField,idLabel);
        idBox.setAlignment(Pos.CENTER);
        inputs.getChildren().add(idBox);
        //inputok hozzáadása
        for (VBox vb:vBoxes) {
            vb.setAlignment(Pos.CENTER);
            inputs.getChildren().add(vb);
        }
        inputs.setAlignment(Pos.CENTER);

        Button updateButton = new Button("Szerkesztés");
        updateButton.setPrefWidth(100);
        updateButton.setOnAction(e -> {
            String[] values = new String[textFields.length];
            for (int i = 0; i < textFields.length; i++) {
                values[i] = textFields[i].getText();
            }
            update(idField.getText(), values, names, tableName);
        });

        Button deleteButton = new Button("Törlés");
        deleteButton.setPrefWidth(100);
        deleteButton.setOnAction(e -> delete(idField.getText(), tableName, names));

        Button insertButton = new Button("Hozzáadás");
        insertButton.setPrefWidth(100);
        insertButton.setOnAction(e -> {
            String[] values = new String[textFields.length];
            for (int i = 0; i < textFields.length; i++) {
                values[i] = textFields[i].getText();
            }
            insert(values, tableName);
        });

        Text text = new Text("A változtatások után frissítsd a táblát, úgy hogy rányomsz a tábla gombjára!");

        buttons.getChildren().addAll(updateButton,deleteButton,insertButton);
        buttons.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(buttons, inputs, text);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
    //it might work
    private static void insert(String[] values, String table){
        String sql = "Insert into " + table + " values (";
        try {
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = con.createStatement();
            for (int i = 0; i < values.length; i++) {
                if (i != values.length-1){
                    sql += "'" + values[i] + "', ";
                }
                else{
                    sql += "'" + values[i] + "')";
                }
            }
            stmt.executeUpdate(sql);
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void delete(String id, String table, String[] names){
        String sql = "DELETE FROM " + table + " WHERE " + names[0] + " = " + id;
        try {
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void update(String id, String[] values, String[] names, String table){
        try {
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = con.createStatement();
            for (int i = 0; i < values.length; i++) {
                if (!Objects.equals(values[i], "")){
                    try{ //ha intet adunk hozzá
                        int n = Integer.parseInt(values[i]);
                        String sql = "UPDATE " + table + " SET " + names[i] + " = " + values[i] + " WHERE " + names[0] + " = " + id;
                        stmt.executeUpdate(sql);
                    }catch(NumberFormatException e){ //ha stringet adunk hozzá
                        String sql = "UPDATE " + table + " SET " + names[i] + " = '" + values[i] + "' WHERE " + names[0] + " = " + id;
                        stmt.executeUpdate(sql);
                    }
                }
            }
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }
}
