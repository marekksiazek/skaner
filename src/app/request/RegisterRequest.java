package app.request;

import app.dataHandler.CheckRegisterKey;
import app.dataHandler.DataReader;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RegisterRequest {

    private int status;
    private String responseMSG;

    public RegisterRequest() throws IOException {
        setStatus(getStatusCode());
        setResponseMSG(getResponseMSG());
    }

    public int getStatusCode() throws IOException {
        URL url = new URL("https://stad-by.pl/barcode/register.php?vol" + CheckRegisterKey.getDiskSerialNumber() + "&key=" + new DataReader().getAppKay());
        HttpURLConnection con;


        con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);


        String outputString = "https://stad-by.pl/barcode/register.php?vol" + CheckRegisterKey.getDiskSerialNumber() + "&key=" + new DataReader().getAppKay();
        OutputStream os = con.getOutputStream();
        byte[] input = outputString.getBytes("utf-8");
        os.write(input, 0, input.length);


        int status = con.getResponseCode();


        return status;
    }


    public String getResponseMSG() throws IOException {
        URL url = new URL("https://stad-by.pl/barcode/register.php?vol" + CheckRegisterKey.getDiskSerialNumber() + "&key=" + new DataReader().getAppKay());
        HttpURLConnection con;

        con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);


        String outputString = "https://stad-by.pl/barcode/register.php?vol" + CheckRegisterKey.getDiskSerialNumber() + "&key=" + new DataReader().getAppKay();
        OutputStream os = con.getOutputStream();
        byte[] input = outputString.getBytes("utf-8");
        os.write(input, 0, input.length);


        String responseMSG = con.getResponseMessage();

        return responseMSG;
    }


    public void setStatus(int status) {
        this.status = status;
    }

    public void setResponseMSG(String responseMSG) {
        this.responseMSG = responseMSG;
    }
}