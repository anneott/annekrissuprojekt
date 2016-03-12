import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        String sisestus = JOptionPane.showInputDialog(null, "Sisestage esimese mängija nimi : ", "Täringu mäng",
                JOptionPane.QUESTION_MESSAGE);
        if (sisestus == null)
            return; // katkestab programmi
        Mängija mängija1 = new Mängija(sisestus);

        sisestus = JOptionPane.showInputDialog(null, "Sisestage teise mängija nimi : ", "Täringu mäng",
                JOptionPane.QUESTION_MESSAGE);
        if (sisestus == null)
            return;
        Mängija mängija2 = new Mängija(sisestus);

        String viseteArv = "";
        int viseteArvInt;

        do {
            viseteArv = JOptionPane.showInputDialog(null, "Sisestage visete arv (1-10) :", "Täringu mäng",
                    JOptionPane.QUESTION_MESSAGE);
            if (viseteArv == null || viseteArv.equals("")) // Stringe võib
                // võrrelda ainult
                // equaliga
                return;
            try { // Proovib kas sisestatud numbrit on võimalik int tüüpi
                // muutujaks muuta.
                viseteArvInt = Integer.parseInt(viseteArv);
                if (viseteArvInt <= 10 && viseteArvInt > 0)
                    break;
            } catch (NumberFormatException e) { // Püüab vea kinni ja läheb
                // uuesti küsima tänu while
                // tsüklile.
            }
        } while (true);

        Täring mängija1täring = new Täring();// 0, 0); // täringu algväärtus on
        // 0
        Täring mängija2täring = new Täring();// 0, 0);

        int punktideSumma1 = 0;
        int punktideSumma2 = 0;

        for (int i = 1; i <= viseteArvInt; i++) {
            JOptionPane.showMessageDialog(frame, "Veereta", "Täringu mäng", JOptionPane.PLAIN_MESSAGE);
            System.out.println("Esimese mängija M1 nimi: " + mängija1);
            System.out.println("Tema kahe täringu summa: " + mängija1täring.täringuteSumma());
            System.out.println("Esimese mängija täringute korrutis " + mängija1täring.täringuteKorrutis());

            System.out.println("Teise mängija M2 nimi: " + mängija2);
            System.out.println("Tema kahe täringu summa: " + mängija2täring.täringuteSumma());
            System.out.println("Teise mängija täringute korrutis " + mängija2täring.täringuteKorrutis());

            Punktid m1punktisumma = new Punktid(mängija1täring);
            System.out.println("Esimese mängija punktisumma: " + m1punktisumma.arvutaPunktid());
            punktideSumma1 += m1punktisumma.arvutaPunktid();

            Punktid m2punktisumma = new Punktid(mängija2täring);
            System.out.println("Teise mängija punktisumma: " + m2punktisumma.arvutaPunktid());
            punktideSumma2 += m2punktisumma.arvutaPunktid();

        }
        System.out.println("MÄNG ON LÄBI !");
        System.out.println(mängija1.getNimi()+ " punktisumma : " + punktideSumma1);
        System.out.println(mängija2.getNimi()+ " punktisumma : " + punktideSumma2);
        if (punktideSumma1 > punktideSumma2)
            System.out.println("Võitja on : " + mängija1.getNimi());
        if (punktideSumma1 < punktideSumma2)
            System.out.println("Võitja on : " + mängija2.getNimi());
    }
}
