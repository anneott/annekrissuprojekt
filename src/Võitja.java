import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Võitja {


    public String leiaVõitja(Mängija mängija1, Mängija mängija2, int summa1, int summa2) {

        String tekst;
        String võitja;

        Group juur6 = new Group();
        Stage võitjaStage = new Stage();

        if (summa1 > summa2){
            võitja = mängija1.getNimi();
        }

        else if (summa2 > summa1) {
            võitja = mängija2.getNimi();
        }

        else {
            võitja = "viik";
        }
        tekst = mängija1.getNimi() + " punktisumma : " + summa1 + "  \n " +mängija2 + " punktisumma : " + summa2 +"  \n Võitja on : " + võitja;

        Text võitjaTekst = MänguAlgus.teeTekstIlusaks(new Text(tekst), 80, 80);

        Scene võitjaStseen = new Scene(juur6, 400, 200, Color.AQUAMARINE);

        võitjaStseen.setRoot(juur6);
        juur6.getChildren().add(võitjaTekst);

        võitjaStage.setScene(võitjaStseen);
        võitjaStage.show();
        return võitja;
    }

}
