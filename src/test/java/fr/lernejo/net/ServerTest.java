package fr.lernejo.net;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ServerTest {

    @Test
    void testServer() throws IOException {
        Server serv = new Server(9876, "localhost");
        serv.init();
    }
}
