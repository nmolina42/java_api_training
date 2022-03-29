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

class FireTest {

    @Test
    void TesthandleFireKo() throws IOException, InterruptedException {
        Server server = new Server(9101, "localhost");
        server.init();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest requetePost = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:9101/api/game/fire"))
            .setHeader("Accept", "application/json")
            .setHeader("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString("{\"consequence\": \"sunk\",  \"shipLeft\":true}"))
            .build();
        HttpResponse<String> resp = client.send(requetePost, HttpResponse.BodyHandlers.ofString());
        Assertions.assertEquals(400, resp.statusCode());
    }
    /*@Test
    void TesthandleFireOk() throws IOException, InterruptedException {
        Server server = new Server(9100, "localhost");
        server.init();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest requetePost = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:9100/api/game/fire"))
            .setHeader("Accept", "application/json")
            .setHeader("Content-Type", "application/json")
            .GET()
            .build();
        HttpResponse<String> resp = client.send(requetePost, HttpResponse.BodyHandlers.ofString());
        Assertions.assertEquals(200, resp.statusCode());
    }*/
}
