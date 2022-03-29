package fr.lernejo.navy_battle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LauncherTest {

    @Test
    void LauncherOneArg() throws IOException, InterruptedException {
        Launcher launch = new Launcher();
        String[] port = {"9000"};
        launch.main(port);
    }
    @Test
    void LauncherTwoArg() throws IOException, InterruptedException {
        Launcher launch = new Launcher();
        String[] port = {"9001","http://localhost:9000"};
        launch.main(port);
    }
}
