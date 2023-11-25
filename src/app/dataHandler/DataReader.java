package app.dataHandler;

import java.io.*;
import java.util.ArrayList;

public class DataReader {
    File file = new File("C:\\Users\\marek.ksiazek\\AppData\\Local\\Barcode\\setup.ini");
    private String firstLine;
    private String secondLine;
    private String thirdLine;

    public DataReader() throws IOException {
        setFirstLine(getAppKay());
        setSecondLine(getSecondLine());
        setThirdLine(getURLBarcode());
    }

    public String getAppKay() throws IOException {
        String line = System.getProperty("line.separator");
        ArrayList<String> strArr = new ArrayList<>();

        String firstLine;
        BufferedReader br = new BufferedReader(new FileReader(file));
        firstLine = br.readLine();

        return  firstLine;
    }

    public String getSecretKey() throws IOException {
        String line = System.getProperty("line.separator");
        ArrayList<String> strArr = new ArrayList<>();

        String secondLine;
        BufferedReader br = new BufferedReader(new FileReader(file));
        br.readLine();
        secondLine = br.readLine();

        return  secondLine;

    }

    public String getURLBarcode() throws IOException {
        String line = System.getProperty("line.separator");
        ArrayList<String> strArr = new ArrayList<>();

        String thirdLine;
        BufferedReader br = new BufferedReader(new FileReader(file));
        br.readLine();
        br.readLine();
        thirdLine = br.readLine();

        return  thirdLine;

    }



    public String getFirstLine() {
        return firstLine;
    }

    public void setFirstLine(String firstLine) {
        this.firstLine = firstLine;
    }

    public String getSecondLine() {
        return secondLine;
    }

    public void setSecondLine(String secondLine) {
        this.secondLine = secondLine;
    }

    public String getThirdLine() {
        return thirdLine;
    }

    public void setThirdLine(String thirdLine) {
        this.thirdLine = thirdLine;
    }
}
