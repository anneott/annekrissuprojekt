import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main extends Application {
    public void start(Stage peaLava) {

        // GridPane loomine (sarnane nagu Group)
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        peaLava.setTitle("Täringumäng"); // lava tiitelribale pannakse tekst
        Scene stseen1 = new Scene(grid, 300, 300, Color.WHITE);
        peaLava.setScene(stseen1);
        peaLava.show(); // lava tehakse nähtavaks

        //Alustab mänguga (mängu tutvustus ja mängijate nimede sisestuse aken)
        MänguAlgus m = new MänguAlgus(grid, peaLava);
        m.tegevusEkraanil();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
