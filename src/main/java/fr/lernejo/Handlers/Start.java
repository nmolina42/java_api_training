package fr.lernejo.Handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;


public class Start implements HttpHandler {

    @Override
    public void handle(HttpExchange exg) throws IOException {
        if (exg.getRequestMethod().equals("POST")) {
           if (JsonIsValide(exg)){
               StartRespond(exg);
           } else {
              BadRequestRespond(exg);
           }
        } else {
            WrongMethodeResponse(exg);
        }
    }

    private void BadRequestRespond(HttpExchange exg) throws IOException {
        exg.sendResponseHeaders(400, "BAD REQUEST".length());
        try (OutputStream os = exg.getResponseBody()) {
            os.write("BAD REQUEST".getBytes());
        }
    }
    private boolean JsonIsValide(HttpExchange exg) {
        InputStreamReader isr = new InputStreamReader(exg.getRequestBody());
        JSONObject jsonData = new JSONObject(new JSONTokener(isr));
        try {
            File schemaFile = new File("src/main/Json/JsonStart.json");
            JSONTokener schemaData = new JSONTokener(new FileInputStream(schemaFile));
            JSONObject jsonSchema = new JSONObject(schemaData);
            Schema schemaValidator = SchemaLoader.load(jsonSchema);
            schemaValidator.validate(jsonData);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    private void StartRespond(HttpExchange exg) throws IOException{
        String json = "{\"id\":\"1\", \"url\":\"http://localhost:" + exg.getLocalAddress().getPort() + "\", \"message\":\"May the god of war with you !\"}";
        exg.sendResponseHeaders(202, json.length());
        try (OutputStream os = exg.getResponseBody()) {
            os.write(json.getBytes());
        }
        System.out.println("Client connected");
    }

    private void WrongMethodeResponse(HttpExchange exg) throws IOException {
        exg.sendResponseHeaders(404, "NOT FOUND".length());
        try (OutputStream os = exg.getResponseBody()) {
            os.write("NOT FOUND".getBytes());
        }
    }
}

