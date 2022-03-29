package fr.lernejo.navy_battle;

import fr.lernejo.net.Server;
import fr.lernejo.net.Client;

import java.io.IOException;

public class Launcher {
    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length == 1) {
            Server server = new Server(Integer.parseInt(args[0]), "localhost");
            server.init();
        }else if (args.length == 2) {
            Client client = new Client(Integer.parseInt(args[0]), args[1]);
            client.start();
        }
        else {
            System.out.println("Usage : need one or two arguments only");
        }
    }

}
