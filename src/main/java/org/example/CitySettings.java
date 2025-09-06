/*
    @author: Vladimir Maintser
    @date: 06.09.2025
    CitySettings class that allow the program to define city, units and apiKey
    and add further implementation to Main and HTTPRequest classes.
 */


package org.example;

public class CitySettings {
    private String city;
    private String units;
    private final String apiKey;

    public CitySettings(String units, String city, String apiKey) {
        this.units = units;
        this.city = city;
        this.apiKey = apiKey;
    }

    public String getBaseUrl() {
        return "https://api.openweathermap.org/data/2.5/weather?q="
                + city + "&appid=" + apiKey + "&units=" + units;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }
}
