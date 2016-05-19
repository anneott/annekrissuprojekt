import java.io.*;

public class Fail{
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

}
