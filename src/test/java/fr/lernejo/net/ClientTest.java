package fr.lernejo.net;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    void TestStart() throws IOException, InterruptedException {
        Server serv = new Server(9200, "localhost");
        serv.init();
        Client client = new Client(9874, "http://localhost:9200");
        int resp = client.start();
        Assertions.assertEquals(resp, 202);
    }
}
