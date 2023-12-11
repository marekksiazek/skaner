package src.main.request;



import src.main.dataHandler.CheckRegisterKey;
import src.main.dataHandler.DataReader;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class RegisterRequest {




    public RegisterRequest() throws IOException {
    }

    public String getRegisterMSG() throws IOException {
        URL url = new URL("https://stand-by.pl/barcode/register.php?vol=" + CheckRegisterKey.getDiskSerialNumber() + "&key=" + new DataReader().getAppKay());
        HttpURLConnection con;


        con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);


        String outputString = "https://stand-by.pl/barcode/register.php?vol=" + CheckRegisterKey.getDiskSerialNumber() + "&key=" + new DataReader().getAppKay();
        OutputStream os = con.getOutputStream();
        byte[] input = outputString.getBytes("utf-8");
        os.write(input, 0, input.length);

        InputStream content = con.getInputStream();
        String result = new BufferedReader(new InputStreamReader(content)).lines().parallel().collect(Collectors.joining("\n"));

        String trimResult = result.trim();
        trimResult = "4609733220f8de810e1e8866d6d92ec9a073f3a569a517caee405e11e4f72949";
        return trimResult;
    }


}