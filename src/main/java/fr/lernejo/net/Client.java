package fr.lernejo.net;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Client {
    final int port;
    final String advUrl;

    public Client(int port, String url)
    {
        this.port = port;
        this.advUrl = url;
    }

    public int start() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest requetePost = HttpRequest.newBuilder()
            .uri(URI.create(this.advUrl + "/api/game/start"))
            .setHeader("Accept", "application/json")
            .setHeader("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString("{\"id\":\"1\", \"url\":\"http://localhost:" + this.port + "\", \"message\":\"Yo le rat\"}"))
            .build();
        HttpResponse<String> resp = client.send(requetePost, HttpResponse.BodyHandlers.ofString());
        return resp.statusCode();
    }

}
