package facades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Fetch {
    public static String fetchData(String apiUrl) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json"); // Add this line
        try {
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return response.toString();

            }
        } catch (Exception e) {
            System.out.println("Error in fetchData");
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        return null;
    }

    public static String fetchLOTRData(String apiUrl) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
//        connection.setRequestProperty("Accept", "application/json"); // Add this line
        connection.setRequestProperty("Authorization", "Bearer 9tkBUH0uH05rx9rpT3n9");
        try {
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return response.toString();

            }
        } catch (Exception e) {
            System.out.println("Error in fetchData");
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            System.out.println(fetchLOTRData("https://the-one-api.dev/v2/character"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
