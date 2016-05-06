import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static javafx.scene.paint.Color.*;

public class MänguAlgus {
    private GridPane grid;
    private Stage peaLava;

    public MänguAlgus(GridPane grid, Stage peaLava) {
        super();
        this.grid = grid;
        this.peaLava = peaLava;
    }

    public Node tegevusEkraanil() {
        // Mängu alustamine
Group juur = new Group();

        Stage uus = new Stage();

        Button nupp = new Button("Alusta mänguga");
        nupp.setAlignment(Pos.BOTTOM_LEFT);
        nupp.setLayoutX(100);
        nupp.setLayoutY(230);
        nupp.setTextFill(Color.FLORALWHITE);
        nupp.setBackground(new Background(new BackgroundFill(Color.DARKRED, CornerRadii.EMPTY, Insets.EMPTY)));
        nupp.setFont(Font.font ("Papyrus", 20));


        Text tekst = new Text();
        tekst.setText("\n"+"Mängu eesmärgiks on võimalikult palju punkte koguda veeretades kahte täringut!" + "\n"
                + "Voorude arvu saab ise valida" + "\n"
                + "Kõige rohkem saab punkte visatest kaks täringut nii, et nende silmade arv oleks sama (+30)" + "\n"
                + "Punkte teenib ka siis kui täringute korrutis jagub mõne kolme astemga (vastavalt +3, +12, +20)"
                + "\n" + "Või siis kui summa on paaris (+8)" + "\n" + "Või summa/ korrutis lõppeb nulliga (+15)" + "\n"
                + "Punkte kaotab paaritu arvu viskamise eest (-4)" + "\n"
                + "Või siis kui korrutis ei jagu mõne kolme astmega (-1)");


        final Light.Distant light = new Light.Distant();
        light.setAzimuth(-135.0);
        final Lighting lighting = new Lighting();
        lighting.setLight(light);
        lighting.setSurfaceScale(9.0);
        nupp.setEffect(lighting);

        InnerShadow is = new InnerShadow();
        is.setOffsetX(0.9f);
        is.setOffsetY(0.5f);
        tekst.setEffect(is);
        tekst.setEffect(lighting);

        tekst.setLayoutX(100);
        tekst.setFill(Color.DARKRED);
        tekst.setFont(Font.font("Papyrus", 16));


        Scene stseen2 = new Scene(nupp, 800, 400, Color.LIGHTSEAGREEN);

        stseen2.setRoot(juur);
        juur.getChildren().add(tekst);
        juur.getChildren().add(nupp);

        uus.setScene(stseen2);
        uus.show();


        nupp.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                uus.hide();

                // Esimese mängija nime kast
                final TextField mängijanr1 = new TextField();
                mängijanr1.setPromptText("Esimese mängija nimi");
                GridPane.setConstraints(mängijanr1, 0, 0);
                grid.getChildren().add(mängijanr1);

                // Teise mängija nime kast
                final TextField mängijanr2 = new TextField();
                mängijanr2.setPromptText("Teise mängija nimi");
                GridPane.setConstraints(mängijanr2, 0, 1);
                grid.getChildren().add(mängijanr2);

                // Visete arvu määramiseks kast
                final TextField viskeArvMängus = new TextField();
                // Hetke on visete arv piiramatu, peab veel kontrollima, et ei oleks
                // NULL !!!!
                viskeArvMängus.setPromptText("Visete arv");
                GridPane.setConstraints(viskeArvMängus, 0, 2);
                grid.getChildren().add(viskeArvMängus);

                //faili kuhu kirjutab tulemuse, kast
                final TextField failinimi = new TextField();
                failinimi.setPromptText("Tekitatava failinimi (.txt lõpuga!), kuhu punktid kirjutatakse");
                GridPane.setConstraints(failinimi, 0, 3);
                grid.getChildren().add(failinimi);

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
                            String tekst;
                            tekst = "Kõik väljad pole täidetud !";
                            Button ok = new Button("Proovi uuesti"); // luuakse nupp
                            // nupu paigutus annab soovida !!!
                            Label label = new Label(tekst, ok);
                            Scene stseen2 = new Scene(label, 300, 100, AQUAMARINE);
                            uus.setScene(stseen2);
                            uus.show();
                            ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                public void handle(MouseEvent me) {
                                    uus.hide();
                                    peaLava.show();
                                }
                            });
                        }


                        MänguKäik uusMäng;

                        try { // Proovib kas sisestatud numbrit on võimalik int tüüpi
                            // muutujaks muuta.
                            Mängija mängija1 = new Mängija(mängijanr1.getText());
                            Mängija mängija2 = new Mängija(mängijanr2.getText());
                            String failinimetus = failinimi.getText();

                            int viseteArvInt = Integer.parseInt(viskeArvMängus.getText()) * 2;


                            uusMäng = new MänguKäik(0, viseteArvInt, 0, failinimetus);

                            uusMäng.alustaMänguga(mängija1, mängija2, grid, peaLava);


                        } // kui visete arv sobib vahemikku, alustab mänguga.
                        catch (NumberFormatException e) { // Püüab vea kinni ja läheb
                            // peaakanasse tagasi
                            peaLava.hide();
                            Stage uus = new Stage();
                            String tekst;
                            tekst = "Visete arv peab olema täisarv";
                            Button ok = new Button("Proovi uuesti"); // luuakse nupp
                            // nupu paigutus annab soovida !!!
                            Label label = new Label(tekst, ok);
                            Scene stseen2 = new Scene(label, 300, 100, AQUAMARINE);
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

                System.out.println("1");

                clear.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent me) {
                        mängijanr1.clear();
                        mängijanr2.clear();
                        viskeArvMängus.clear();
                    }
                });

                System.out.println("2");
                cancel.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent me) {
                        peaLava.hide();
                        // peaks katkestama programmi siis
                    }
                });
                System.out.println("3");

            }
        });
        return grid;

    }
}
