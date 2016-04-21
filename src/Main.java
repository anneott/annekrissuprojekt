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


        // õpetus
        System.out.println("Mängu eesmärgiks on võimalikult palju punkte koguda veeretades kahte täringut!");
        System.out.println("Voorude arvu saab ise valida");
        System.out.println("Kõige rohkem saab punkte visatest kaks täringut nii, et nende silmade arv oleks sama (+30)");
        System.out.println("Punkte teenib ka siis kui täringute korrutis jagub mõne kolme astemga (vastavalt +3, +12, +20)");
        System.out.println("Või siis kui summa on paaris (+8)");
        System.out.println("Või summa/ korrutis lõppeb nulliga (+15)");
        System.out.println("Punkte kaotab paaritu arvu viskamise eest (-4)");
        System.out.println("Või siis kui korrutis ei jagu mõne kolme astmega (-1)");
        System.out.println();





        String viskeArvMängus;
        do {
            viskeArvMängus = JOptionPane.showInputDialog(null, "Sisestage visete arv (1-10) :", "Täringu mäng",
                    JOptionPane.QUESTION_MESSAGE); //viseteArv on String tüüpi, sest ei osanud nii, et saaks kasti sisestada int tüüpi argumenti.
            if (viskeArvMängus == null || viskeArvMängus.equals("")) //Stringe võib võrrelda ainult equaliga.
                return; //katkestab programmi, kui vajutatakse cancel või ei sisestata midagi.
            try { // Proovib kas sisestatud numbrit on võimalik int tüüpi muutujaks muuta.
                int viseteArvInt = Integer.parseInt(viskeArvMängus);
                if (viseteArvInt <= 10 && viseteArvInt > 0)
                    break; //kui visete arv sobib vahemikku, katkestame programmi.
            } catch (NumberFormatException e) { // Püüab vea kinni ja läheb uuesti küsima tänu while tsüklile.
                 }

        } while (true);


        MänguKäik uusMäng = new MänguKäik(0,Integer.parseInt(viskeArvMängus),0);

        uusMäng.alustaMänguga(mängija1, mängija2);



        int summa1 = uusMäng.getPunktideSumma1();
        int summa2 = uusMäng.getPunktideSumma2();
        //leiame võitja
        if (summa1 > summa2){
            System.out.println("MÄNG ON LÄBI ! " + mängija1 + " võitis.");
        }
        else if (summa2 > summa1){
            System.out.println("MÄNG ON LÄBI ! " + mängija2 + " võitis.");
        }
        else
            System.out.println("MÄNG ON LÄBI ! Jäite viiki. ");

        System.out.println(mängija1 + " punktisumma : " + summa1);
        System.out.println(mängija2 + " punktisumma : " + summa2);
    }
}
