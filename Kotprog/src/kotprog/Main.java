// Original: https://www.developer.com/java/data/working-with-the-javafx-ui-and-jdbc-applications.html
// Mintaként fel lett használva: http://www.inf.u-szeged.hu/~gnemeth/kurzusok/adatbgyak/exe/AdatbazisokGyakorlat2020/javafx_pldaprogram.html

package kotprog;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {
    //Menusor, tablavalto gombok
    private final HBox gombBox = new HBox(10);
    private final Button szineszButton = new Button("Színész");
    private final Button filmButton = new Button("Film");
    private final Button studioButton = new Button("Filmstúdió");
    private final Button szereplesekButton = new Button("Szereplések");
    private final Button mufajokButton = new Button("Műfajok");
    private final Button dijakButton = new Button("Díjak");
    private final Button queryButton = new Button("Lekérdezések");
    //Layoutok
    private final SzineszLayout szinesz = new SzineszLayout();
    private final FilmLayout film = new FilmLayout();
    private final DijakLayout dijak = new DijakLayout();
    private final FilmstudioLayout filmstudio = new FilmstudioLayout();
    private final MufajokLayout mufajok = new MufajokLayout();
    private final SzereplesekLayout szereplesek = new SzereplesekLayout();
    private final QueryLayout query = new QueryLayout();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("IMDB");
        primaryStage.setMaximized(true);
        BorderPane layout = new BorderPane();
        Scene scene = new Scene(layout,650,400,true);

        //felso menusor, itt lehet majd valtogatni a sceneket, azaz a tablakat
        gombBox.getChildren().addAll(szineszButton,filmButton,studioButton,szereplesekButton,mufajokButton,dijakButton,queryButton);
        gombBox.setPadding(new Insets(0,20,0,20));
        gombBox.setAlignment(Pos.TOP_CENTER);
        for (Node n:
             gombBox.getChildren()) {
            Button b = (Button) n;
            b.setMinWidth(80);
            b.setPrefWidth(150);
            b.setMaxWidth(200);
        }

        layout.setTop(gombBox);
        layout.setCenter(szinesz.layout());

        szineszButton.setOnAction(e -> {
            layout.setCenter(szinesz.layout());
        });
        filmButton.setOnAction(e -> {
            layout.setCenter(film.layout());
        });
        dijakButton.setOnAction(e -> {
            layout.setCenter(dijak.layout());
        });
        studioButton.setOnAction(e -> {
            layout.setCenter(filmstudio.layout());
        });
        mufajokButton.setOnAction(e -> {
            layout.setCenter(mufajok.layout());
        });
        szereplesekButton.setOnAction(e -> {
            layout.setCenter(szereplesek.layout());
        });
        queryButton.setOnAction(e -> {
            layout.setCenter(query.layout());
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}