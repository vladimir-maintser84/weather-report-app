/*
    @author: Vladimir Maintser
    @date: 06.09.2025
    HTTPRequest class that request OpenWeatherMap API and get info
    about weather in user city and user metric.
 */


package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HTTPRequest {
    private final HttpClient client;

    public HTTPRequest() {
        this.client = HttpClient.newHttpClient();
    }

    public String fetch(CitySettings citySettings) throws IOException, InterruptedException {
        String URL = citySettings.getBaseUrl();

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(URL))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();

    }
}
