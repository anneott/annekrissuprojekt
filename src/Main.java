import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {
    public void start(Stage peaLava) {

        // GridPane loomine (sarnane nagu Group)
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        //Teise ekraani suurus
        peaLava.setTitle("Täringumäng"); // lava tiitelribale pannakse tekst
        Scene stseen1 = new Scene(grid, 450, 300, Color.AQUAMARINE);
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
