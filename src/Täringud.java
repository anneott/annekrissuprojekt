public class Täringud{

    private int täring1;
    private int täring2;

    //ühe viske suvaline tulemus
    public int üksVise(){
        int viskeTulemus = (int) (Math.random() * 6 + 1);
        return viskeTulemus;
    }

    //visatud täringute väärtused
    public void viska(){
        täring1 = üksVise();
        täring2 = üksVise();
        System.out.println("t1 = " + täring1 + " t2 = " + täring2);
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

//    public int getTäring1() {
//        return täring1;
//    }
//
//    public int getTäring2() {
//        return täring2;
//    }
}
