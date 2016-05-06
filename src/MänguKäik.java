import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;

public class MänguKäik {
    private int viseteArvInt;
    private int punktideSumma1 = 0;
    private int punktideSumma2 = 0;
    private int clicks = 0;
    private boolean aegAvaldadaVõitjat;
    private Fail fail;
    private String failinimi;

    public MänguKäik(int punktideSumma1, int viseteArvInt, int punktideSumma2, String failinimi) {
        this.punktideSumma1 = punktideSumma1;
        this.viseteArvInt = viseteArvInt;
        this.punktideSumma2 = punktideSumma2;
        this.failinimi = failinimi;
        fail = new Fail(failinimi);
    }


    public void alustaMänguga(Mängija mängija1, Mängija mängija2, GridPane grid, Stage peaLava){
        for (int i = 0; i <= viseteArvInt; i++)
            peaLava.hide();
        Stage uus = new Stage(); // stage Veeretuste jaoks

        String tekst;
        tekst = "MÄNG KÄIB!!";
        Button nupp = new Button("Veereta"); // luuakse nupp
        // nupu paigutus annab soovida !!!
        Label label = new Label(tekst, nupp);//ilma tekstita ei saa
        Scene stseen2 = new Scene(label, 300, 100, Color.AQUAMARINE);
        uus.setScene(stseen2);
        uus.show();


        for (int j = 1; j <= viseteArvInt; j++) { //Mäng käib seni, kuni sisestatud visete arv saab otsa.
            nupp.setOnMouseClicked(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent me) {
                    clicks++;

                    Stage punktiLava = new Stage();
                    String punktiTekst;
                    Button nuppJätka = new Button("Jätka Mänguga");

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
                        System.out.println("Tekkis viga faili kirjutamisel! ");
                    }

                    Label punktiLabel = new Label(punktiTekst, nuppJätka);
                    Scene punktiStseen = new Scene(punktiLabel, 500, 200, Color.ALICEBLUE);

                    //kui vajutad jätka, siis paneb akna kinni, et veeretuste aken lahti jääks
                    nuppJätka.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        public void handle(MouseEvent me) {
                            punktiLava.hide();
                        }
                    });


                    punktiLava.setScene(punktiStseen);
                    punktiLava.show();

                    //kui nupule on klikitud nii mitu korda, kui alguses määratud, siis kuulutab välja võitja
                    if (clicks == viseteArvInt) {
                        uus.close();
                        aegAvaldadaVõitjat = true; //nüüd on aeg võitja avaldada
                        //probleemne koht, nüüd ta ei lähe enam peameetodis aegAvaldaVõitjat tõeväärtust küsida

                        //KAS VÄGA HALB? et kuulutab võitja siin välja mitte peaklassis vms?
                        Võitja võitja = new Võitja();
                        String võitjanimi = võitja.leiaVõitja(mängija1, mängija2, getPunktideSumma1(), getPunktideSumma2());
                        System.out.println(võitjanimi);

                        //kirjutan võitja faili
                        try {
                            fail.kirjutaPunktidFaili(võitjanimi, failinimi);
                        } catch (IOException e) {
                            System.out.println("ei saanud võitjat faili kirjutada");
                        }
                    }
                }
            });

            System.out.println(j + " ring läbi");
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