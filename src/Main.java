public class Main {
    public static void main(String[] args) {
        Mängija mängija1 = new Mängija("Kristiina");

        Täring mängija1täring = new Täring(0,0); //täringu algväärtus on 0

        System.out.println("Esimese mängija nimi: " + mängija1);

        System.out.println("Tema kahe täringu summa: " + mängija1täring.täringuteSumma());

    }
}
