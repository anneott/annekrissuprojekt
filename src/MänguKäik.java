import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;

public class MänguKäik {
    private int viseteArvInt;
    private int punktideSumma1 = 0;
    private int punktideSumma2 = 0;
    private int clicks = 0;
    private Fail fail;
    private String failinimi;

    public MänguKäik(int punktideSumma1, int viseteArvInt, int punktideSumma2, String failinimi) {
        this.punktideSumma1 = punktideSumma1;
        this.viseteArvInt = viseteArvInt;
        this.punktideSumma2 = punktideSumma2;
        this.failinimi = failinimi;
        fail = new Fail(failinimi);
    }
    public void failiKirjutamiselTekkinudViga(){
        Group juur3 = new Group();
        Stage uus1 = new Stage();
        Text tekst = MänguAlgus.teeTekstIlusaks(new Text("Faili kirjutamine ebaõnnestus"), 10, 30);
        Button ok = MänguAlgus.teeNuppIlusaks("OK", 10, 40);

        Scene stseen2 = new Scene(ok, 300, 100, Color.AQUAMARINE);
        stseen2.setRoot(juur3);
        juur3.getChildren().add(ok);
        juur3.getChildren().add(tekst);
        uus1.setScene(stseen2);
        uus1.show();

        ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                uus1.hide();
            }

        });

        //Teine ekraan tuleb ette ka "Enteri" vajutamisel
        stseen2.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    uus1.hide();
                }
            }
        });
    }

    public void veeretaTegevus(Mängija mängija1, Mängija mängija2, Stage uus){
        clicks++;

        Stage punktiLava = new Stage();
        Group juur5 = new Group();

        String punktiTekst;
        Button nuppJätka = MänguAlgus.teeNuppIlusaks("Jätka Mänguga", 10, 80);

        //paaritute klikkede arvuga on mängija1
        if (clicks % 2 == 1) {
            Punktid punktisumma = new Punktid(mängija1.getTäringud()); //loome klassi punktid punktisumma isendi, mis saab ette täringu väärtused
            punktiTekst = "Esimese mängija " + mängija1 + " viskab: " + "\n" +
                    mängija1.getTäringud().viska() + "\n" +//kõigepealt võtame mängija alt täringu ja sealt suunatakse edasi klassi Täring, kus sooritatakse meetod viska()
                    "Vaheseis: " + mängija1 + " punktisumma: " + punktisumma.arvutaPunktid(); //väljastab vaheseisu (palju punkte kellelgi on)

            punktideSumma1 += (punktisumma.arvutaPunktid()) / 2; //pean jagama kahegi, et tuleks õige vastus, millegi pärast ??
            //punktiTekst.kirjutaFaili( );
        }

        else {
            Punktid punktisumma = new Punktid(mängija2.getTäringud());
            punktiTekst = "Teine mängija " + mängija2 + " viskab: " + "\n" +
                    mängija2.getTäringud().viska() + "\n" +
                    "Vaheseis: " + mängija2 + " punktisumma: " + punktisumma.arvutaPunktid();
            punktideSumma2 += (punktisumma.arvutaPunktid()) / 2;

            System.out.println();
        }


        //kirjutan vaheseisu faili ehk punktiteksti
        try {
            fail.kirjutaPunktidFaili(punktiTekst, failinimi);
        } catch (IOException e) {
            failiKirjutamiselTekkinudViga();
        }


        Text tekstVaheseis = MänguAlgus.teeTekstIlusaks(new Text(punktiTekst), 10, 20);

        Scene punktiStseen = new Scene(nuppJätka, 500, 200, Color.AQUAMARINE);
        punktiStseen.setRoot(juur5);
        juur5.getChildren().add(tekstVaheseis);
        juur5.getChildren().add(nuppJätka);

        punktiLava.setScene(punktiStseen);
        punktiLava.show();


        punktiLava.setScene(punktiStseen);
        punktiLava.show();

        //kui vajutad jätka, siis paneb akna kinni, et veeretuste aken lahti jääks
        nuppJätka.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                punktiLava.hide();
            }
        });

        //Teine ekraan tuleb ette ka "Enteri" vajutamisel
        punktiStseen.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    punktiLava.hide();
                }
            }
        });



        //kui nupule on klikitud nii mitu korda, kui alguses määratud, siis kuulutab välja võitja
        if (clicks == viseteArvInt) {
            punktiLava.hide();
            uus.close();

            //et kuulutab võitja siin välja mitte peaklassis vms?
            Võitja võitja = new Võitja();
            String võitjanimi = võitja.leiaVõitja(mängija1, mängija2, getPunktideSumma1(), getPunktideSumma2());

            //kirjutan võitja faili
            try {
                fail.kirjutaPunktidFaili(võitjanimi, failinimi);
            } catch (IOException e) {
                failiKirjutamiselTekkinudViga();
            }
        }
    }

    public void alustaMänguga(Mängija mängija1, Mängija mängija2, GridPane grid, Stage peaLava){
        Group juur4 = new Group();

        for (int i = 0; i <= viseteArvInt; i++)
            peaLava.hide();
        Stage uus = new Stage(); // stage Veeretuste jaoks

        Text tekst = MänguAlgus.teeTekstIlusaks(new Text("Mäng käib!"), 10 , 30);
        Button nupp = MänguAlgus.teeNuppIlusaks("Veereta",10,40); // luuakse nupp

        Scene stseen2 = new Scene(juur4, 300, 100, Color.AQUAMARINE);

        juur4.getChildren().add(tekst);
        juur4.getChildren().add(nupp);

        uus.setScene(stseen2);
        uus.show();


        for (int j = 1; j <= viseteArvInt; j++) { //Mäng käib seni, kuni sisestatud visete arv saab otsa.
            nupp.setOnMouseClicked(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent me) {
                    veeretaTegevus(mängija1, mängija2, uus);
                }
            });
            //Teine ekraan tuleb ette ka "Enteri" vajutamisel
            stseen2.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    if (event.getCode() == KeyCode.ENTER) {
                        veeretaTegevus(mängija1, mängija2, uus);
                    }
                }
            });

            System.out.println();
        }

    }

    public int getPunktideSumma1() {
        return punktideSumma1;
    }

    public int getPunktideSumma2() {
        return punktideSumma2;
    }
}