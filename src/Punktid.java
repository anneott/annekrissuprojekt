/**
 * Created by user on 12.03.2016.
 */
public class Punktid {

    private int punktisumma;
    Täring mängijatäringud;

    public Punktid(Täring mängijatäringud) {
        this.mängijatäringud = mängijatäringud;
    }

    private void yatzy(){
        if (mängijatäringud.getTäring1() == mängijatäringud.getTäring2()){
            punktisumma += 10;
        }
    }

    //kui summa paaris liidab punkte, kui paaritu siis lahutab
    private void summaOnPaarisVõiPaaritu(){
        if ((mängijatäringud.getTäring1() + mängijatäringud.getTäring2()) % 2 == 0){
            punktisumma += 5;
        }
        else{
            punktisumma -= 4;
        }
    }

    public int arvutaPunktid(){
        yatzy();
        summaOnPaarisVõiPaaritu();
        return punktisumma;
    }

    public String toString(){
        return "Puntkid: " + punktisumma;
    }

}
