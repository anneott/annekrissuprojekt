import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        String sisestus = JOptionPane.showInputDialog(null, "Sisestage esimese mängija nimi : ", "Täringu mäng",
                JOptionPane.QUESTION_MESSAGE); //Sisestuskast
        if (sisestus == null)
            return; // katkestab programmi, kui vajutatakse cancel
        Mängija mängija1 = new Mängija(sisestus); //loome klassi Mängija isendi mängija1, mis saab ette siestatud nime.

        sisestus = JOptionPane.showInputDialog(null, "Sisestage teise mängija nimi : ", "Täringu mäng",
                JOptionPane.QUESTION_MESSAGE);
        if (sisestus == null)
            return;
        Mängija mängija2 = new Mängija(sisestus);

        String viseteArv = "";
        int viseteArvInt;

        do {
            viseteArv = JOptionPane.showInputDialog(null, "Sisestage visete arv (1-10) :", "Täringu mäng",
                    JOptionPane.QUESTION_MESSAGE); //viseteArv on String tüüpi, sest ei osanud nii, et saaks kasti sisestada in tüüpi argumenti.
            if (viseteArv == null || viseteArv.equals("")) //Stringe võib võrrelda ainult equaliga.
                return; //katkestab programmi, kui vajutatakse cancel või ei sisestata midagi.
            try { // Proovib kas sisestatud numbrit on võimalik int tüüpi muutujaks muuta.
                viseteArvInt = Integer.parseInt(viseteArv);
                if (viseteArvInt <= 10 && viseteArvInt > 0)
                    break; //kui visete arv sobib vahemikku, katkestame programmi.
            } catch (NumberFormatException e) { // Püüab vea kinni ja läheb
                // uuesti küsima tänu while
                // tsüklile.
            }
        } while (true);

//		Täring mängija1täring = new Täring();// 0, 0); // täringu algväärtus on
//												// 0
//		Täring mängija2täring = new Täring();// 0, 0);

        int punktideSumma1 = 0;
        int punktideSumma2 = 0;

        for (int i = 1; i <= viseteArvInt; i++) { //Mäng käib seni, kuni sisestatud visete arv saab otsa.
            JOptionPane.showMessageDialog(null, "Veereta", "Täringu mäng", JOptionPane.PLAIN_MESSAGE);
            mängija1.getTäringud().viska(); //kõigepealt võtame mängija alt täringu ja sealt suunatakse edasi klassi Täring, kus sooritatakse meetod viska()
            System.out.println("Esimese mängija M1 nimi: " + mängija1);
            System.out.println("Tema kahe täringu summa: " + mängija1.getTäringud().täringuteSumma());
            System.out.println("Esimese mängija täringute korrutis " + mängija1.getTäringud().täringuteKorrutis());

            mängija2.getTäringud().viska();
            System.out.println("Teise mängija M2 nimi: " + mängija2);
            System.out.println("Tema kahe täringu summa: " + mängija2.getTäringud().täringuteSumma());
            System.out.println("Teise mängija täringute korrutis " + mängija2.getTäringud().täringuteKorrutis());

            Punktid punktisumma = new Punktid(mängija1.getTäringud()); //loome klassi punktid punktisumma isendi, mis saab ette täringu väärtused
            System.out.println("Esimese mängija punktisumma: " + punktisumma.arvutaPunktid()); //väljastab vaheseisu (palju punkte kellelgi on)
            punktideSumma1 += (punktisumma.arvutaPunktid())/2; //pean jagama kahegi, et tuleks õige vastus, millegi pärast ??

            punktisumma = new Punktid(mängija2.getTäringud());
            System.out.println("Teise mängija punktisumma: " + punktisumma.arvutaPunktid());
            punktideSumma2 += (punktisumma.arvutaPunktid())/2;

        }
        if (punktideSumma1 > punktideSumma2){
            System.out.println("MÄNG ON LÄBI ! " + mängija1 + " võitis.");
        }
        else if (punktideSumma2 > punktideSumma1){
            System.out.println("MÄNG ON LÄBI ! " + mängija2 + " võitis.");
        }
        else
            System.out.println("MÄNG ON LÄBI ! Jäite viiki. ");
        System.out.println(mängija1 + " punktisumma : " + punktideSumma1);
        System.out.println(mängija2 + " punktisumma : " + punktideSumma2);

    }
}
