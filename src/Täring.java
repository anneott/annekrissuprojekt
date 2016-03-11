public class Täring{

    private int täring1;
    private int täring2;

    public Täring(int täring1, int täring2){
        this.täring1 = täring1;
        this.täring2 = täring2;
    }

    //ühe viske suvaline tulemus
    public int üksVise(){
        int viskeTulemus = (int) (Math.random() * 6 + 1);
        return viskeTulemus;
    }

    public int täringuteSumma(){
        int esimeneVise = üksVise();
        int teineVise = üksVise();
        return esimeneVise + teineVise;

    }


}
