import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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

        Text võitjaTekst = new Text(tekst);

        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0f);
        ds.setColor(Color.color(0.4f, 0.4f, 0.4f));


        võitjaTekst.setEffect(ds);
        võitjaTekst.setCache(true);

        võitjaTekst.setLayoutX(50);
        võitjaTekst.setLayoutY(30);
        võitjaTekst.setFont(Font.font("Papyrus", 30));
        võitjaTekst.setFill(Color.DARKRED);

        Scene võitjaStseen = new Scene(juur6, 400, 200, Color.AQUAMARINE);

        võitjaStseen.setRoot(juur6);
        juur6.getChildren().add(võitjaTekst);

        võitjaStage.setScene(võitjaStseen);
        võitjaStage.show();
        return võitja;
    }

}
