import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
        Group juur4 = new Group();

        for (int i = 0; i <= viseteArvInt; i++)
            peaLava.hide();
        Stage uus = new Stage(); // stage Veeretuste jaoks

        Text tekst = teeTekstIlusaks(new Text("Mäng käib!"), 10 , 30);
        Button nupp = teeNuppIlusaks("Veereta",10,40); // luuakse nupp

        Scene stseen2 = new Scene(nupp, 300, 100, Color.AQUAMARINE);

        stseen2.setRoot(juur4);
        juur4.getChildren().add(tekst);
        juur4.getChildren().add(nupp);

        uus.setScene(stseen2);
        uus.show();


        for (int j = 1; j <= viseteArvInt; j++) { //Mäng käib seni, kuni sisestatud visete arv saab otsa.
            nupp.setOnMouseClicked(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent me) {
                    clicks++;

                    Stage punktiLava = new Stage();
                    Group juur5 = new Group();

                    String punktiTekst;
                    Button nuppJätka = teeNuppIlusaks("Jätka Mänguga", 10, 80);

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


                    Text tekstVaheseis = teeTekstIlusaks(new Text(punktiTekst), 10, 20);

                    Scene punktiStseen = new Scene(nuppJätka, 500, 200, Color.AQUAMARINE);
                    punktiStseen.setRoot(juur5);
                    juur5.getChildren().add(tekstVaheseis);
                    juur5.getChildren().add(nuppJätka);

                    punktiLava.setScene(punktiStseen);
                    punktiLava.show();

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


    private Text teeTekstIlusaks(Text tekst, int xasukoht, int yasukoht){
        InnerShadow is = new InnerShadow();
        is.setOffsetX(0.9f);
        is.setOffsetY(0.5f);
        tekst.setEffect(is);
        tekst.setLayoutX(xasukoht);
        tekst.setLayoutY(yasukoht);
        tekst.setFill(Color.DARKRED);
        tekst.setFont(Font.font("Papyrus", 16));

        return tekst;
    }

    private Button teeNuppIlusaks(String tekst, int xasukoht, int yasukoht){
        Button nupp = new Button(tekst);
        nupp.setAlignment(Pos.BOTTOM_LEFT);
        nupp.setLayoutX(xasukoht);
        nupp.setLayoutY(yasukoht);
        nupp.setTextFill(Color.FLORALWHITE);
        nupp.setBackground(new Background(new BackgroundFill(Color.DARKRED, CornerRadii.EMPTY, Insets.EMPTY)));
        nupp.setFont(Font.font ("Papyrus", 20));

        return nupp;
    }
}