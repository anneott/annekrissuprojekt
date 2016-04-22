public class Täringud{

    private int täring1;
    private int täring2;

    //ühe viske suvaline tulemus
    public int üksVise(){
        return (int) (Math.random() * 6 + 1);
    }

    //visatud täringute väärtused
    public void viska(){
        täring1 = üksVise();
        täring2 = üksVise();
        System.out.println("Esimese täringu väärtus = " + täring1 + " Teise tärinug väärtus = " + täring2);
    }

    public int täringuteSumma(){
        return täring1 + täring2;
    }

    public int täringuteKorrutis(){
        return täring1 * täring2;
    }

    public boolean täringudVõrduvad(){
        return täring1 == täring2;
    }

}
