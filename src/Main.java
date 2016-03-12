import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        String sisestatakse1 = JOptionPane.showInputDialog(null, "Sisestage esimese mängija nimi : ", "Täringu mäng",
                JOptionPane.QUESTION_MESSAGE);

        String sisestatakse2 = JOptionPane.showInputDialog(null, "Sisestage teise mängija nimi : ", "Täringu mäng",
                JOptionPane.QUESTION_MESSAGE);

        Mängija mängija1 = new Mängija(sisestatakse1);
        Mängija mängija2 = new Mängija(sisestatakse2);

        Täring mängija1täring = new Täring(0,0); //täringu algväärtus on 0
        Täring mängija2täring = new Täring(0,0);

        System.out.println("Esimese mängija M1 nimi: " + mängija1);
        System.out.println("Tema kahe täringu summa: " + mängija1täring.täringuteSumma());
        System.out.println("Esimese mängija täringute korrutis " + mängija1täring.täringuteKorrutis());

        System.out.println("Teise mängija M2 nimi: " + mängija2);
        System.out.println("Tema kahe täringu summa: " + mängija2täring.täringuteSumma());
        System.out.println("Teise mängija täringute korrutis " + mängija2täring.täringuteKorrutis());

        Punktid m1punktisumma = new Punktid(mängija1, mängija1täring);
        System.out.println("Esimese mängija punktisumma: " + m1punktisumma.arvutaPunktid());

        Punktid m2punktisumma = new Punktid(mängija1, mängija1täring);
        System.out.println("Teise mängija punktisumma: " + m2punktisumma.arvutaPunktid());

    }
}
