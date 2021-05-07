package fr.launcher.nugarium.ddb;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.sun.jmx.snmp.agent.SnmpUserDataFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;

public class JsonDDB {

    public JsonDDB()
    {

    }

    private static final Gson GSON = new GsonBuilder().create();
    public Data data;

    public boolean JsonReader() throws IOException {

        final URL url = new URL("https://nugarium.com/test");
        final HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


        httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0");

        final int responseCode = httpURLConnection.getResponseCode();

        if (responseCode == 404) {
            System.out.println("Impossible de joindre le site.");
            return true;
        }


        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        final StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = bufferedReader.readLine()) != null) response.append(inputLine);

        bufferedReader.close();


        data = GSON.fromJson(response.toString(), Data.class);
        return true;
    }
}