public class Võitja {
    public String leiaVõitja(Mängija mängija1, Mängija mängija2, int summa1, int summa2){
        if (summa1 > summa2){
            //System.out.println("MÄNG ON LÄBI ! " + mängija1 + " võitis.");
            return "MÄNG ON LÄBI ! " + mängija1 + " võitis.";
    }
        else if (summa2 > summa1){
            //System.out.println("MÄNG ON LÄBI ! " + mängija2 + " võitis.");
            return "MÄNG ON LÄBI ! " + mängija2 + " võitis.";
    }
        else
                //System.out.println("MÄNG ON LÄBI ! Jäite viiki. ");
        return "jäite viiki";
    }
}