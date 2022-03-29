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

class StartTest {

    @Test
    void TesthandleStartOk() throws IOException, InterruptedException {
        Server server = new Server(7777, "localhost");
        server.init();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest requetePost = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:7777/api/game/start"))
            .setHeader("Accept", "application/json")
            .setHeader("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString("{\"id\":\"1\", \"url\":\"http://localhost:8765\", \"message\":\"May the god of war with you !\"}"))
            .build();
        HttpResponse<String> resp = client.send(requetePost, HttpResponse.BodyHandlers.ofString());
        Assertions.assertEquals(202, resp.statusCode());
    }

    @Test
    void TesthandleStartKo() throws IOException, InterruptedException {
        Server server = new Server(7171, "localhost");
        server.init();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest requetePost = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:7171/api/game/fire"))
            .setHeader("Accept", "application/json")
            .setHeader("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString("{\"id\":\"1\", \"url\":\"http://localhost:7772\", \"msg\":\"May the bear be with you !\"}"))
            .build();
        HttpResponse<String> resp = client.send(requetePost, HttpResponse.BodyHandlers.ofString());
        Assertions.assertEquals(400, resp.statusCode());
    }

    /*@Test
    void TesthandleWrong() throws IOException, InterruptedException {
        Server server = new Server(6632, "localhost");
        server.init();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest requetePost = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:6632/api/game/fire"))
            .setHeader("Accept", "application/json")
            .setHeader("Content-Type", "application/json")
            .GET()
            .build();
        HttpResponse<String> resp = client.send(requetePost, HttpResponse.BodyHandlers.ofString());
        Assertions.assertEquals(404, resp.statusCode());
    }*/
}
