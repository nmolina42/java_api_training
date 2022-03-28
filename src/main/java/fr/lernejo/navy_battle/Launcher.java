package fr.lernejo.navy_battle;

import fr.lernejo.net.Server;

import java.io.IOException;

public class Launcher {
    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length == 1) {
            Server server = new Server(Integer.parseInt(args[0]), "localhost");
            server.init();
        }else if (args.length == 2) {
            Server server = new Server(Integer.parseInt(args[0]), "localhost",args[1]);
            server.init();
        }
        else {
            System.out.println("Usage : <Server> port | <Client> port serverPort");
        }
    }

}
