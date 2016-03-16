/**
 * Created by user on 12.03.2016.
 */
public class Punktid {

    private int punktisumma;
    Täringud täringud;

    public Punktid(Täringud täringud) {
        this.täringud = täringud;
    }

    private void yatzy(){
        if (täringud.täringudVõrduvad()){
            punktisumma += 10;
        }
    }

    //kui summa paaris liidab punkte, kui paaritu siis lahutab
    private void summaOnPaarisVõiPaaritu(){
        if (täringud.täringuteSumma() % 2 == 0){
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

//    public String toString(){
//        return "Puntkid: " + punktisumma;
//    }

}