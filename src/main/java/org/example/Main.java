/*
    @author: Vladimir Maintser
    @date: 06.09.2025
    This is the main method of the weather report program
    that has CitySettings and HTTPRequest methods implementation.
 */


package org.example;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner s = new Scanner(System.in);

        System.out.println("Enter your city: ");
        String userCity = s.nextLine();

        System.out.println("Enter your unit system: ");
        String userUnits = s.nextLine();

        String apiKey = System.getenv("OPENWEATHER_API_KEY");
        if (apiKey == null || apiKey.isEmpty()) {
            System.out.println("Enter your OpenWeatherMap API key: ");
            apiKey = s.nextLine();
        }

        try {
            CitySettings citySettings = new CitySettings(userUnits, userCity, apiKey);
            HTTPRequest httpRequest = new HTTPRequest();
            String JSONResponse = httpRequest.fetch(citySettings);
            JSONObject jsonObject = new JSONObject(JSONResponse);
            System.out.println("----------------");
            String city = jsonObject.getString("name");
            double temp = jsonObject.getJSONObject("main").getDouble("temp");
            String desc = jsonObject.getJSONArray("weather")
                    .getJSONObject(0)
                    .getString("description");
            int humidity = jsonObject.getJSONObject("main").getInt("humidity");
            double wind = jsonObject.getJSONObject("wind").getDouble("speed");

            if(userUnits.equals("metric")){

                System.out.println("temperature in " + city + " -> " + temp + "°C");
                System.out.println("Weather: " + desc);
                System.out.println("Humidity: " + humidity + "%");
                System.out.println("Wind speed: " + wind + " m/s");
            }
            else {

                System.out.println("temperature in " + city + " -> " + temp + "°F");
                System.out.println("Weather: " + desc);
                System.out.println("Humidity: " + humidity + "%");
                System.out.println("Wind speed: " + wind + " mph");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("IllegalArgumentException, check if" +
                    " unit system or city is written correctly!");
        }

    }
}