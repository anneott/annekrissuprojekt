public class Täring{

    private int täring1;
    private int täring2;

    public Täring(int täring1, int täring2){
        this.täring1 = täring1;
        this.täring2 = täring2;
    }

    //ühe viske suvaline tulemus
    public int üksvise(){
        int viskeTulemus = (int) (Math.random() * 6 + 1);
        return viskeTulemus;
    }

    public int täringuteSumma(){
        int esimeneVise = üksvise();
        int teineVise = üksvise();
        return esimeneVise + teineVise;

    }


}
