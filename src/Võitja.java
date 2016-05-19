import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Võitja {

    public void võitjaVälja(String tekst, Scene võitjaStseen, Stage võitjaStage) {
        Group juur6 = new Group();

        int x = ((int) Math.round(võitjaStseen.getHeight()))-((int) Math.round(võitjaStseen.getHeight()))/2;
        int y = ((int) Math.round(võitjaStseen.getHeight()))-((int) Math.round(võitjaStseen.getHeight()))/2;

        Text võitjaTekst = MänguAlgus.teeTekstIlusaks(new Text(tekst), x, y);
        võitjaTekst.setFill(Color.DARKRED);
        võitjaTekst.setFont(Font.font("Segoe Print", FontWeight.EXTRA_BOLD, 21));

        võitjaStseen.setRoot(juur6);
        juur6.getChildren().add(võitjaTekst);

    }

    public String leiaVõitja(Mängija mängija1, Mängija mängija2, int summa1, int summa2) {

        String tekst;
        String võitja;

        Group juur6 = new Group();
        Stage võitjaStage = new Stage();

        if (summa1 > summa2) {
            võitja = mängija1.getNimi();
        }

        else if (summa2 > summa1) {
            võitja = mängija2.getNimi();
        }

        else {
            võitja = "viik";
        }
        tekst = mängija1.getNimi() + " punktisumma : " + summa1 + "  \n " + mängija2 + " punktisumma : " + summa2
                + "  \n Võitja on : " + võitja;

        Scene võitjaStseen = new Scene(juur6, 400, 200, Color.AQUAMARINE);

        võitjaVälja(tekst, võitjaStseen, võitjaStage);

        võitjaStseen.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(final ObservableValue<? extends Number> observable, final Number oldValue,
                                final Number newValue) {
                võitjaVälja(tekst, võitjaStseen, võitjaStage);
            }
        });



        võitjaStage.setScene(võitjaStseen);
        võitjaStage.show();

        return võitja;
    }

}
