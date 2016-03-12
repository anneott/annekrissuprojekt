public class Punktid {

    private int vise1;
    private int vise2;
    private String nimi;
    private int punktisumma;

    public Punktid(Mängija mängija, Täring mängijatäringud) {
        vise1 = mängijatäringud.getTäring1();
        vise2 = mängijatäringud.getTäring2();
        nimi = mängija.getNimi();
    }

    public int yatzy(){
        if (vise1 == vise2){
            punktisumma += 10;
        }
        return punktisumma;
    }

    //kui summa paaris liidab punkte, kui paaritu siis lahutab
    public int summaOnPaarisVõiPaaritu(){
        if ((vise1 + vise2) % 2 == 0){
            punktisumma += 5;
        }
        else{
            punktisumma -= 4;
        }
        return punktisumma;
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
