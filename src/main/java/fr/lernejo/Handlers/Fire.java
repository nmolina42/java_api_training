package fr.lernejo.Handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import fr.lernejo.net.Server;
import org.json.JSONObject;


import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class Fire implements HttpHandler {

    final Server srv;

    public Fire(Server srv) {
        this.srv = srv;
    }

    public Map<String, String> queryToMap(String query) {
        if(query == null) {
            return null;
        }
        Map<String, String> result = new HashMap<>();
        for (String param : query.split("&")) {
            String[] entry = param.split("=");
            if (entry.length > 1) {
                result.put(entry[0], entry[1]);
            }else{
                result.put(entry[0], "");
            }
        }
        return result;
    }

    private void BadRequestRespond(HttpExchange exg) throws IOException {
        exg.sendResponseHeaders(400, "BAD REQUEST".length());
        try (OutputStream os = exg.getResponseBody()) {
            os.write("BAD REQUEST".getBytes());
        }
    }

    public void sendResponse(HttpExchange exg, String payload) throws IOException {
        exg.getResponseHeaders().add("Content-Type", "application/json");
        exg.sendResponseHeaders(200, payload.length());
        exg.getResponseBody().write(payload.getBytes());
        exg.close();
    }


    @Override
    public void handle(HttpExchange exg) throws IOException {
        if ("GET".contentEquals(exg.getRequestMethod())) {
            Map<String, String> params = queryToMap(exg.getRequestURI().getQuery());
            String cell = params.get("cell");
            if (!params.isEmpty() && !cell.equals("")) {
                JSONObject payload = new JSONObject("{\"consequence\": \"sunk\",  \"shipLeft\":true}");
                sendResponse(exg, payload.toString());
            }
        }
        BadRequestRespond(exg);
    }
}
