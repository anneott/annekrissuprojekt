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

        //õpetus
        System.out.println("Mängu eesmärgiks on võimalikult palju punkte koguda veeretades kahte täringut!");
        System.out.println("Voorude arvu saab ise valida");
        System.out.println("Kõige rohkem saab punkte visatest kaks täringut nii, et nende silmade arv oleks sama (+30)");
        System.out.println("Punkte teenib ka siis kui täringute korrutis jagub mõne kolme astemga (vastavalt +3, +12, +20)");
        System.out.println("Või siis kui summa on paaris (+8)");
        System.out.println("Või summa/ korrutis lõppeb nulliga (+15)");
        System.out.println("Punkte kaotab paaritu arvu viskamise eest (-4)");
        System.out.println("Või siis kui korrutis ei jagu mõne kolme astmega (-1)");
        System.out.println();

        do {
            viseteArv = JOptionPane.showInputDialog(null, "Sisestage visete arv (1-10) :", "Täringu mäng",
                    JOptionPane.QUESTION_MESSAGE); //viseteArv on String tüüpi, sest ei osanud nii, et saaks kasti sisestada in tüüpi argumenti.
            if (viseteArv == null || viseteArv.equals("")) //Stringe võib võrrelda ainult equaliga.
                return; //katkestab programmi, kui vajutatakse cancel või ei sisestata midagi.
            try { // Proovib kas sisestatud numbrit on võimalik int tüüpi muutujaks muuta.
                viseteArvInt = Integer.parseInt(viseteArv);
                if (viseteArvInt <= 10 && viseteArvInt > 0)
                    break; //kui visete arv sobib vahemikku, katkestame programmi.
            } catch (NumberFormatException e) { // Püüab vea kinni ja läheb uuesti küsima tänu while tsüklile.
            }

        } while (true);

        int punktideSumma1 = 0;
        int punktideSumma2 = 0;

        for (int i = 1; i <= viseteArvInt; i++) { //Mäng käib seni, kuni sisestatud visete arv saab otsa.
            JOptionPane.showMessageDialog(null, "Veereta", "Täringu mäng", JOptionPane.PLAIN_MESSAGE);


            System.out.println("Esimese mängija " + mängija1 + " viskab: ");
            mängija1.getTäringud().viska(); //kõigepealt võtame mängija alt täringu ja sealt suunatakse edasi klassi Täring, kus sooritatakse meetod viska()

            //System.out.println("Tema kahe täringu summa: " + mängija1.getTäringud().täringuteSumma());
            //System.out.println("Esimese mängija täringute korrutis " + mängija1.getTäringud().täringuteKorrutis());


            System.out.println("Teine mängija " + mängija2 + " viskab: ");
            mängija2.getTäringud().viska();
            //System.out.println("Tema kahe täringu summa: " + mängija2.getTäringud().täringuteSumma());
            //System.out.println("Teise mängija täringute korrutis " + mängija2.getTäringud().täringuteKorrutis());

            System.out.println();

            Punktid punktisumma = new Punktid(mängija1.getTäringud()); //loome klassi punktid punktisumma isendi, mis saab ette täringu väärtused
            System.out.println("Vaheseis: " + mängija1 + " punktisumma: " + punktisumma.arvutaPunktid()); //väljastab vaheseisu (palju punkte kellelgi on)
            punktideSumma1 += (punktisumma.arvutaPunktid())/2; //pean jagama kahegi, et tuleks õige vastus, millegi pärast ??

            punktisumma = new Punktid(mängija2.getTäringud());
            System.out.println("Vaheseis: " + mängija2 + " punktisumma: " + punktisumma.arvutaPunktid());
            punktideSumma2 += (punktisumma.arvutaPunktid())/2;

            System.out.println(i + " ring läbi");
            System.out.println();

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
