import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Võitja {


    public String leiaVõitja(Mängija mängija1, Mängija mängija2, int summa1, int summa2) {

        String tekst;
        String võitja;

        Stage võitjaStage = new Stage();
        if (summa1 > summa2){
            võitja = mängija1.getNimi();
            tekst = mängija1.getNimi() + "punktisumma : " + summa1 + " ja " +mängija2 + " punktisumma : " + summa2 +". Võitja on : " + võitja;
        }
        else if (summa2 > summa1) {
            võitja = mängija2.getNimi();
            tekst = mängija1.getNimi() + " punktisumma : " + summa1 + " ja " + mängija2 + " punktisumma : " + summa2 + ". Võitja on : " + võitja;
        }
        else {
            võitja = "puudub";
            tekst = mängija1.getNimi() + " punktisumma : " + summa1 + " ja " + mängija2 + " punktisumma : " + summa2 + ". Mäng jäi viiki";
        }

        Label võitjaLabel = new Label(tekst);

        Scene võitjaStseen = new Scene(võitjaLabel, 500, 100, Color.AQUA);
        võitjaStage.setScene(võitjaStseen);
        võitjaStage.show();
        return võitja;
    }

}
