import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MänguKäik {
    private String viseteArv = "";
    private int viseteArvInt;
    private int punktideSumma1 = 0;
    private int punktideSumma2 = 0;
    private int clicks = 0;

    public MänguKäik(int punktideSumma1, int viseteArvInt, int punktideSumma2) {
        this.punktideSumma1 = punktideSumma1;
        this.viseteArvInt = viseteArvInt;
        this.punktideSumma2 = punktideSumma2;
    }

    public void alustaMänguga(Mängija mängija1, Mängija mängija2, GridPane grid, Stage peaLava){
        for (int i = 0; i <= viseteArvInt; i++)
            peaLava.hide();
        Stage uus = new Stage();
        String tekst = null;
        tekst = "MÄNG KÄIB !!";
        Button nupp = new Button("Veereta"); // luuakse nupp
        // nupu paigutus annab soovida !!!
        Label label = new Label(tekst, nupp);//ilma tekstita ei saa
        Scene stseen2 = new Scene(label, 300, 100, Color.AQUAMARINE);
        uus.setScene(stseen2);
        uus.show();
        for (int i = 1; i <= viseteArvInt; i++) { //Mäng käib seni, kuni sisestatud visete arv saab otsa.
            nupp.setOnMouseClicked(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent me) {
                    clicks++;
                    System.out.println("Esimese mängija " + mängija1 + " viskab: ");
                    mängija1.getTäringud().viska(); //kõigepealt võtame mängija alt täringu ja sealt suunatakse edasi klassi Täring, kus sooritatakse meetod viska()
                    //System.out.println("Tema kahe täringu summa: " + mängija1.getTäringud().täringuteSumma());
                    //System.out.println("Esimese mängija täringute korrutis " + mängija1.getTäringud().täringuteKorrutis());


                    System.out.println("Teine mängija " + mängija2 + " viskab: ");
                    mängija2.getTäringud().viska();

                    //System.out.println("Tema kahe täringu summa: " + mängija2.getTäringud().täringuteSumma());
                    //System.out.println("Teise mängija täringute korrutis " + mängija2.getTäringud().täringuteKorrutis());

                    System.out.println();

                    Punktid punktisumma = new Punktid(mängija1.getTäringud()); //loome klassi punktid punktisumma isendi, mis saab ette täringu väärtused
                    System.out.println("Vaheseis: " + mängija1 + " punktisumma: " + punktisumma.arvutaPunktid()); //väljastab vaheseisu (palju punkte kellelgi on)
                    punktideSumma1 += (punktisumma.arvutaPunktid()) / 2; //pean jagama kahegi, et tuleks õige vastus, millegi pärast ??

                    punktisumma = new Punktid(mängija2.getTäringud());
                    System.out.println("Vaheseis: " + mängija2 + " punktisumma: " + punktisumma.arvutaPunktid());
                    punktideSumma2 += (punktisumma.arvutaPunktid()) / 2;
                    if (clicks==viseteArvInt)
                        uus.close();
                }
            });
            System.out.println(i + " ring läbi");
            System.out.println();
        }
    }

    public String getViseteArv() {
        return viseteArv;
    }

    public int getViseteArvInt() {
        return viseteArvInt;
    }

    public int getPunktideSumma1() {
        return punktideSumma1;
    }

    public int getPunktideSumma2() {
        return punktideSumma2;
    }
}