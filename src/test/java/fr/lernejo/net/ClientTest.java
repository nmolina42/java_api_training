package fr.lernejo.net;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    void TestStart() throws IOException, InterruptedException {
        Client client = new Client(9874, "http://localhost:9875");
        int resp = client.start();
        Assertions.assertEquals(resp, 202);
    }
}
