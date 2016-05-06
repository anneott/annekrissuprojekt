import java.io.IOException;

public class Punktid {

    private int punktisumma;
    private Täringud täringud;
    String failinimi;
    Mängija mängija;
    Fail fail;

    public Punktid(Täringud täringud) {
        this.täringud = täringud;
    }

    private void yatzy(){
        if (täringud.täringudVõrduvad()){
            punktisumma += 30;
        }
    }

    //kui summa paaris liidab punkte, kui paaritu siis lahutab
    private void summaOnPaarisVõiPaaritu(){
        if (täringud.täringuteSumma() % 2 == 0){
            punktisumma += 8;
        }
        else{
            punktisumma -= 4;
        }
    }

    //kui korrutis jagub mõne 3 astmega, siis võidad punkte juurde vastavalt kui suure 3 astmega jagub
    private void korrutisJagubKolmeAstmega() {

        int jaguvusKolmeAstmega = täringud.täringuteKorrutis() / 3;
        switch (jaguvusKolmeAstmega) {
            case 3:
                punktisumma += 3;
                break;
            case 9:
                punktisumma += 12;
                break;
            case 27:
                punktisumma += 20;
                break;
            default:
                punktisumma -= 1;
        }

    }

    private void lõppebNulliga(){
        if(täringud.täringuteKorrutis() % 10 == 0 || täringud.täringuteSumma() == 0){
            punktisumma += 15;
        }
    }

    public int arvutaPunktid(){
        yatzy();
        summaOnPaarisVõiPaaritu();
        korrutisJagubKolmeAstmega();
        lõppebNulliga();
        return punktisumma;
    }

}