package fr.lernejo.Handlers;

import fr.lernejo.net.Server;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;

class PingTest {

    @Test
    void testPing() throws IOException, InterruptedException {
        Server server = new Server(9899, "localhost");
        server.init();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest requetePost = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:9899/ping"))
            .setHeader("Accept", "application/json")
            .setHeader("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString("{\"id\":\"1\", \"url\":\"http://localhost:9899\", \"message\":\"Yo le rat\"}"))
            .build();
        HttpResponse<String> resp = client.send(requetePost, HttpResponse.BodyHandlers.ofString());
        Assertions.assertEquals(200, resp.statusCode());
    }
}
