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

        tegevusEkraanil(grid, peaLava);
    }

    public static Node tegevusEkraanil(GridPane grid, Stage peaLava) {
        // Esimese mängija nime kast
        final TextField mängijanr1 = new TextField();
        mängijanr1.setPromptText("Esimese mängija nimi");
        GridPane.setConstraints(mängijanr1, 0, 0);
        grid.getChildren().add(mängijanr1);

        Mängija mängija1 = new Mängija(mängijanr1.getText());

        // Teise mängija nime kast
        final TextField mängijanr2 = new TextField();
        mängijanr2.setPromptText("Teise mängija nimi");
        GridPane.setConstraints(mängijanr2, 0, 1);
        grid.getChildren().add(mängijanr2);

        Mängija mängija2 = new Mängija(mängijanr2.getText());

        // Visete arvu määramiseks kast
        final TextField viskeArvMängus = new TextField();
        // Hetke on visete arv piiramatu, peab veel kontrollima, et ei oleks
        // NULL !!!!
        viskeArvMängus.setPromptText("Visete arv");
        GridPane.setConstraints(viskeArvMängus, 0, 2);
        grid.getChildren().add(viskeArvMängus);

        // Alusta nupp
        Button submit = new Button("Alusta");
        GridPane.setConstraints(submit, 1, 0);
        grid.getChildren().add(submit);

        // Puhasta nupp
        Button clear = new Button("Puhasta");
        GridPane.setConstraints(clear, 1, 1);
        grid.getChildren().add(clear);

        // Cancel nupp
        Button cancel = new Button("Lahku");
        GridPane.setConstraints(cancel, 1, 2);
        grid.getChildren().add(cancel);

        submit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                // kui kõik väljad pole täidetud, siis ei luba mängu alustada
                if (mängijanr1.getText().trim().isEmpty() || mängijanr2.getText().trim().isEmpty()
                        || viskeArvMängus.getText().trim().isEmpty()) {
                    peaLava.hide();
                    Stage uus = new Stage();
                    String tekst = null;
                    tekst = "Kõik väljad pole täidetud !";
                    Button ok = new Button("Proovi uuesti"); // luuakse nupp
                    // nupu paigutus annab soovida !!!
                    Label label = new Label(tekst, ok);
                    Scene stseen2 = new Scene(label, 300, 100, Color.AQUAMARINE);
                    uus.setScene(stseen2);
                    uus.show();
                    ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        public void handle(MouseEvent me) {
                            uus.hide();
                            peaLava.show();
                        }
                    });
                }

                try { // Proovib kas sisestatud numbrit on võimalik int tüüpi
                    // muutujaks muuta.
                    int viseteArvInt = Integer.parseInt(viskeArvMängus.getText());
                    //OTSIB MILLEGI PÄRAST ENNE VÕITJAT, KUI ALUSATB MÄNGUGA !!
                    MänguKäik uusMäng = new MänguKäik(0,viseteArvInt,0);
                    uusMäng.alustaMänguga(mängija1, mängija2, grid, peaLava);

                    int summa1 = uusMäng.getPunktideSumma1();
                    int summa2 = uusMäng.getPunktideSumma2();

                    Võitja võitja = new Võitja();

                    String võitjanimi = võitja.leiaVõitja(mängija1, mängija2,summa1, summa2);

                    Stage uus = new Stage();
                    String tekst = mängija1 + " punktisumma : " + summa1 + " ja " +mängija2 + " punktisumma : " + summa2 +". Võitja on : " + võitjanimi;
                    Label label = new Label(tekst);
                    Scene stseen2 = new Scene(label, 300, 100, Color.AQUAMARINE);
                    uus.setScene(stseen2);
                    uus.show();

                } // kui visete arv sobib vahemikku, alustab mänguga.
                catch (NumberFormatException e) { // Püüab vea kinni ja läheb
                    // peaakanasse tagasi
                    peaLava.hide();
                    Stage uus = new Stage();
                    String tekst = null;
                    tekst = "Visete arv peab olema täisarv";
                    Button ok = new Button("Proovi uuesti"); // luuakse nupp
                    // nupu paigutus annab soovida !!!
                    Label label = new Label(tekst, ok);
                    Scene stseen2 = new Scene(label, 300, 100, Color.AQUAMARINE);
                    uus.setScene(stseen2);
                    uus.show();
                    ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        public void handle(MouseEvent me) {
                            uus.hide();
                            peaLava.show();
                        }

                    });

                }

            }
        });

        clear.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                mängijanr1.clear();
                mängijanr2.clear();
                viskeArvMängus.clear();
            }
        });
        cancel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                peaLava.hide();
                return; // peaks katkestama programmi siis
            }
        });



        return grid;

    }

    public static void main(String[] args) {
        launch(args);
    }
}