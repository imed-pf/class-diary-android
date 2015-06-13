package br.edu.imed.classdiary.sync;

import com.google.gson.Gson;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Uzumaki on 13/06/2015.
 */
public class SyncAdapter {

    public static final String SYNC_BASE_URL = "http://192.168.46.176:8080/vraptor-template";

    public boolean checkLogin(String email, String password) {
        HttpURLConnection conn = null;
        boolean isAuthenticated = false;
        try {
            URL url = new URL(SYNC_BASE_URL + "/student/login");
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(10000);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            String body = String.format("student.email=%s&student.password=%s",email,password);
            OutputStream output = new BufferedOutputStream(conn.getOutputStream());
            output.write(body.getBytes());
            output.flush();
            isAuthenticated = conn.getResponseCode() == HttpURLConnection.HTTP_OK;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            conn.disconnect();
        }
        System.out.println(isAuthenticated);
        return isAuthenticated;
    }

    private String get(String uri) {

        HttpURLConnection httpClient = null;
        StringBuilder response = new StringBuilder();
        try {

            URL url = new URL(String.format("%s/%s",SYNC_BASE_URL, uri));
            httpClient = (HttpURLConnection) url.openConnection();
            httpClient.setReadTimeout(10000);
            httpClient.setConnectTimeout(15000);
            httpClient.setRequestMethod("GET");
            httpClient.setRequestProperty("content-type", "application/json");
            httpClient.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpClient.getInputStream()));

            String line = null;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            System.out.println("Response:" + response.toString());
            if (httpClient.getResponseCode() != 200)
                return null;


        } catch (Exception e) {
            System.out.println("Erro ao realizar GET");
            e.printStackTrace();
        } finally {
            httpClient.disconnect();
        }
        return response.toString();
    }

    public Student loadPerson(String email) {
        String uri = String.format("student/email/%s",email);
        return new Gson().fromJson(get(uri),Student.class);

    }
}
