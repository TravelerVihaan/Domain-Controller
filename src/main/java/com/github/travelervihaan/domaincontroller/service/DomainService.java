package com.github.travelervihaan.domaincontroller.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;

@Service
public class DomainService {

    public String sendRequest(JSONObject headers) throws Exception {
        try {
            //Http params
            String url = "https://api.example.pl";
            URL urlObj = new URL(url);
            HttpsURLConnection connection = (HttpsURLConnection) urlObj.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
            dataOutputStream.writeBytes("params=" + headers.toString());
            dataOutputStream.flush();
            dataOutputStream.close();
            BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = input.readLine()) != null) {
                response.append(inputLine);
            }
            input.close();
            return response.toString();
        } catch (Exception e) {
            return e.toString();
        }
    }
}
