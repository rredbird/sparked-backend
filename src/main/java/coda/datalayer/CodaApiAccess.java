package coda.datalayer;

import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.MongoClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.Map.Entry;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.bson.conversions.Bson;

import coda.shared.properties.Properties;
import coda.shared.logging.ILogging;

public class CodaApiAccess {
    @Autowired
    private Properties properties;

    @Autowired
    private static ILogging log;

    public CodaApiAccess() {
    }

    public static String fileCall(String urlPath) {
        String fileData = "";
        try {
            fileData = new String(Files.readAllBytes(Paths.get("./resources/" + urlPath)));
        } catch (Exception e) {
            log.exception(e);
        }
        return fileData;
    }

    public static String get(String urlPath) throws Exception {
        return get(urlPath, new LinkedList<String[]>());
    }

    public static String get(String urlPath, List<String[]> parameters) throws Exception {
        StringBuilder result = new StringBuilder();
        String urlString = "http://10.0.2.55:5000/evaluation/" + urlPath;
//        String urlString = "http://10.0.2.64:8080/evaluation/" + urlPath;
        Boolean firstParameter = true;
        for(String[] parameter : parameters) {
            if(parameter.length != 2) {
                log.error("Expected 2 (Key, Value) but got " + parameter.length + " values as parameters.");
            }
            if(firstParameter) {
                firstParameter = false;
                urlString += "?" + parameter[0] + "=" + parameter[1];
            } else {
                urlString += "&" + parameter[0] + "=" + parameter[1];
            }
        }

        URL url = new URL(urlString);
        HttpURLConnection.setFollowRedirects(false);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(1000);
        connection.setRequestMethod("GET");
        
        BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }
}
