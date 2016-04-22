public class Mängija {

    private String nimi;

    Täringud täringud = new Täringud(); //seome täringu ja mängija, siin loome uued täringud, mis jäetakse meelde

    public Mängija(String nimi) {
        this.nimi = nimi;
    }

    public String toString() { //ilma selleta annab nimede aseme Mängija@b2bfe1, kuigi ma toStringi peaklassis ei kasuta mängija1 järel
        return nimi;
    }

    public Täringud getTäringud() {
        return täringud;
    }

    public String getNimi() {
        return nimi;
    }
}