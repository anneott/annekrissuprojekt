import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MänguAlgus {
    private GridPane grid;
    private Stage peaLava;

    public MänguAlgus(GridPane grid, Stage peaLava) {
        super();
        this.grid = grid;
        this.peaLava = peaLava;
    }
    public void pealeAndmeteSisestamist(TextField mängijanr1, TextField mängijanr2, TextField failinimi, TextField viskeArvMängus){
        // kui kõik väljad pole täidetud, siis ei luba mängu alustada
        if (mängijanr1.getText().trim().isEmpty() || mängijanr2.getText().trim().isEmpty()) {
            peaLava.hide();
            Stage uus = new Stage();
            Group juur2 = new Group();

            Text tekst = teeTekstIlusaks(
                    new Text(
                            "Kontrolli, et kõik väljad oleksid täidetud !"),
                    10, 30);
            Button ok = teeNuppIlusaks("Proovi uuesti", 10, 40); // luuakse
            // nupp

            Scene stseen2 = new Scene(juur2, 650, 100, Color.AQUAMARINE);

            juur2.getChildren().add(ok);
            juur2.getChildren().add(tekst);
            uus.setScene(stseen2);
            uus.show();

            ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent me) {
                    uus.hide();
                    peaLava.show();
                }
            });

            // Teine ekraan tuleb ette ka "Enteri" vajutamisel
            stseen2.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    if (event.getCode() == KeyCode.ENTER) {
                        uus.hide();
                        peaLava.show();
                    }
                }
            });
        }
        else{

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
            // kui visete arv pole int tüüpi, siis carchib
            catch (NumberFormatException e) {
                // Püüab vea kinni ja läheb
                // peaakanasse tagasi

                Group juur3 = new Group();
                peaLava.hide();
                Stage uus = new Stage();
                Text tekst = teeTekstIlusaks(new Text("Visete arv peab olema täisarv!"), 10, 30);
                Button ok = teeNuppIlusaks("Proovi uuesti", 10, 40);

                Scene stseen2 = new Scene(ok, 300, 100, Color.AQUAMARINE);
                stseen2.setRoot(juur3);
                juur3.getChildren().add(ok);
                juur3.getChildren().add(tekst);
                uus.setScene(stseen2);
                uus.show();

                ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent me) {
                        uus.hide();
                        peaLava.show();
                    }

                });

                //Teine ekraan tuleb ette ka "Enteri" vajutamisel
                stseen2.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        if (event.getCode() == KeyCode.ENTER) {
                            uus.hide();
                            peaLava.show();
                        }
                    }
                });
            }

        }
    }

    public void mänguKäik(Stage uus) {
        uus.hide();

        grid.setVgap(16);
        grid.setHgap(7);
        // teise ekraani "üldisi" seadeid saab muuta grid-i kaudu (nt taustavärv
        // jne) kui ka Main klassis!!
        grid.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, CornerRadii.EMPTY, Insets.EMPTY)));
        // Esimese mängija nime kast ehk TextField
        final TextField mängijanr1 = teeTekstFieldIlusaks("Esimese mängija nimi");
        GridPane.setConstraints(mängijanr1, 0, 0);
        grid.getChildren().add(mängijanr1);

        // Teise mängija nime kast ehk TextField
        final TextField mängijanr2 = teeTekstFieldIlusaks("Teise mängija nimi");
        GridPane.setConstraints(mängijanr2, 0, 1);
        grid.getChildren().add(mängijanr2);

        // Visete arvu määramiseks kast
        final TextField viskeArvMängus = teeTekstFieldIlusaks("Visete arv");
        // TODO Hetkel on visete arv piiramatu, peab veel kontrollima, et ei
        // oleks NULL !!!!
        GridPane.setConstraints(viskeArvMängus, 0, 2);
        grid.getChildren().add(viskeArvMängus);

        // faili kuhu kirjutab tulemuse, kast
        final TextField failinimi = teeTekstFieldIlusaks("Fail, kuhu punktid kirjutatakse"); // ei
        // pea
        // olemas
        // olema,
        // ei
        // pea
        // .txt
        // lõpuga
        // olema!
        GridPane.setConstraints(failinimi, 0, 3);
        grid.getChildren().add(failinimi);

        // Alusta nupp
        Button submit = teeNuppIlusaks("Alusta", 0, 0);
        GridPane.setConstraints(submit, 3, 0);
        grid.getChildren().add(submit);

        // Puhasta nupp
        Button clear = teeNuppIlusaks("Puhasta", 0, 0);
        GridPane.setConstraints(clear, 3, 1);
        grid.getChildren().add(clear);

        // Cancel nupp
        Button cancel = teeNuppIlusaks("Lahku", 0, 0);
        GridPane.setConstraints(cancel, 3, 2);
        grid.getChildren().add(cancel);

        submit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            // TODO kui jätta tühjaks viseteArvu aken, siis annab kaks veateate
            // akent...
            public void handle(MouseEvent me) {
                pealeAndmeteSisestamist(mängijanr1, mängijanr2, failinimi, viskeArvMängus);
            }
        });
        //Teine ekraan tuleb ette ka "Enteri" vajutamisel
        grid.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    pealeAndmeteSisestamist(mängijanr1, mängijanr2, failinimi, viskeArvMängus);
                }
            }
        });

        System.out.println("1");

        clear.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                mängijanr1.clear();
                mängijanr2.clear();
                viskeArvMängus.clear();
                failinimi.clear();
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

    public Node tegevusEkraanil() {
        // Mängu alustamine //ESIMENE "EKRAAN"
        Group juur = new Group();
        Stage uus = new Stage();

        Button nupp = teeNuppIlusaks("Alusta mänguga", 80, 280);
        Text tekst = teeTekstIlusaks(new Text("\n"
                + "Mängu eesmärgiks on võimalikult palju punkte koguda veeretades kahte täringut!" + "\n"
                + "Voorude arvu saab ise valida!" + "\n"
                + "Kõige rohkem saab punkte visatest kaks täringut nii, et nende silmade arv oleks sama (+30)" + "\n"
                + "Punkte teenib ka siis kui : \n    täringute korrutis jagub mõne kolme astemga (vastavalt +3, +12, +20)"
                + "\n" + "   summa on paaris (+8)" + "\n" + "   summa/ korrutis lõppeb nulliga (+15)" + "\n"
                + "Punkte kaotab: \n     paaritu arvu viskamise eest (-4)" + "\n"
                + "     kui korrutis ei jagu mõne kolme astmega (-1)"), 80, 0);

        final Light.Distant light = new Light.Distant();
        light.setAzimuth(-135.0);
        final Lighting lighting = new Lighting();
        lighting.setLight(light);
        lighting.setSurfaceScale(9.0);
        nupp.setEffect(lighting);
        tekst.setEffect(lighting);

        DropShadow vari = new DropShadow(0, Color.DARKRED);
        tekst.setEffect(vari);

        Scene stseen2 = new Scene(juur, 800, 400, Color.AQUAMARINE);

        juur.getChildren().add(tekst);
        juur.getChildren().add(nupp);

        uus.setScene(stseen2);
        uus.show();

        // TEINE "EKRAAN"
        nupp.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                mänguKäik(uus);
            }

        });

        //Teine ekraan tuleb ette ka "Enteri" vajutamisel
        stseen2.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    mänguKäik(uus);
                }
            }
        });

        return grid;

    }

    private Button teeNuppIlusaks(String tekst, int xasukoht, int yasukoht) {
        Button nupp = new Button(tekst);
        nupp.setAlignment(Pos.BOTTOM_LEFT);
        nupp.setLayoutX(xasukoht);
        nupp.setLayoutY(yasukoht);
        nupp.setTextFill(Color.FLORALWHITE);
        nupp.setBackground(new Background(new BackgroundFill(Color.DARKRED, CornerRadii.EMPTY, Insets.EMPTY)));
        nupp.setFont(Font.font("Papyrus", 20));

        return nupp;
    }

    private Text teeTekstIlusaks(Text tekst, int xasukoht, int yasukoht) {
        InnerShadow is = new InnerShadow();
        is.setOffsetX(0.9f);
        is.setOffsetY(0.5f);

        tekst.setEffect(is);

        tekst.setLayoutX(xasukoht);
        tekst.setLayoutY(yasukoht);

        tekst.setFill(Color.DARKRED);
        tekst.setFont(Font.font("Papyrus", 17));

        return tekst;
    }

    private TextField teeTekstFieldIlusaks(String promttext) {
        TextField tf = new TextField();
        tf.setPromptText(promttext);
        tf.setFont(Font.font("Papyrus", 21));
        tf.setBackground(new Background(new BackgroundFill(Color.INDIANRED, CornerRadii.EMPTY, Insets.EMPTY)));

        return tf;
    }
}