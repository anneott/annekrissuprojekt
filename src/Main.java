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

        tegevusEkraanil(grid, peaLava);
    }

    public static Node tegevusEkraanil(GridPane grid, Stage peaLava) {
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
        failinimi.setPromptText("Fail kuhu kirjutatakse tulemused");
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



                MänguKäik uusMäng;

                //eeldame et kasutaja annab faili nime .txt-ga
                //kontrollib, kas sisestatud fail on ikka .txt ja kas ta on ikkka tühi


                    try {
                        File fail = new File(failinimi.getText());
                        fail.isFile();
                        Scanner input = new Scanner(fail);

                        if(!input.hasNext()){
                            System.out.println("fail on tühi");
                        }
                    }
                    catch (FileNotFoundException fnfe){

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


                try { // Proovib kas sisestatud numbrit on võimalik int tüüpi
                    // muutujaks muuta.
                    Mängija mängija1 = new Mängija(mängijanr1.getText());
                    Mängija mängija2 = new Mängija(mängijanr2.getText());

                    int viseteArvInt = Integer.parseInt(viskeArvMängus.getText()) * 2;


                    uusMäng = new MänguKäik(0,viseteArvInt,0);

                    uusMäng.alustaMänguga(mängija1, mängija2, grid, peaLava);


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



        return grid;

    }

    public static void main(String[] args) {
        launch(args);
        }
    }
