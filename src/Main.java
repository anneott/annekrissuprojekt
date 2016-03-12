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

        System.out.println("Esimese mängija nimi: " + mängija1);
        System.out.println("Tema kahe täringu summa: " + mängija1täring.täringuteSumma());

        System.out.println("Teise mängija nimi: " + mängija2);
        System.out.println("Tema kahe täringu summa: " + mängija2täring.täringuteSumma());

    }
}
