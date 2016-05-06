import java.io.*;

public class Fail implements AutoCloseable{
    private String failinimi;
    BufferedWriter bw;

    public Fail(String failinimi) {
        this.failinimi = failinimi;
    }

    public void kirjutaPunktidFaili(String rida, String failinimi) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(failinimi, true));
        pw.println(rida);
        pw.close();
    }

    @Override
    public void close() throws Exception {
        System.out.println("sulgen faili");
    }

}
