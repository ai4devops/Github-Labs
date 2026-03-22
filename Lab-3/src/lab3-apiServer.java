import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

public class TrainingApiMain {

    // "Base de donnees" en memoire, volontairement simpliste et non optimisee.
    private static final List<Map<String, String>> FAKE_DB = Collections.synchronizedList(new ArrayList<>());
    private static final Object DB_LOCK = new Object();

    public static void main(String[] args) throws Exception {
        fillDatabaseInAnOvercomplicatedWay();

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/hello", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String method = exchange.getRequestMethod();
                logVeryVerbously("Incoming /hello request with method: " + method);

                if (!"GET".equalsIgnoreCase(method)) {
                    sendJson(exchange, 405, "{\"error\":\"Only GET is accepted on /hello\"}");
                    return;
                }

                Map<String, String> queryMap = parseQueryVeryManually(exchange.getRequestURI().getRawQuery());
                String name = queryMap.get("name");
                if (name == null || name.trim().isEmpty()) {
                    name = "anonymous";
                }

                name = normalizeStringByDoingTooMuchWork(name);
                uselessCpuBurn(15000);

                String message = "hello " + name + ", this endpoint is intentionally over-engineered for training";
                String response = "{"
                        + "\"endpoint\":\"/hello\","
                        + "\"timestamp\":\"" + escapeJson(LocalDateTime.now().toString()) + "\","
                        + "\"message\":\"" + escapeJson(message) + "\""
                        + "}";

                sendJson(exchange, 200, response);
            }
        });

        server.createContext("/delete", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String method = exchange.getRequestMethod();
                logVeryVerbously("Incoming /delete request with method: " + method);

                // Mauvaise decision exprimee volontairement: on accepte DELETE et POST.
                if (!"DELETE".equalsIgnoreCase(method) && !"POST".equalsIgnoreCase(method)) {
                    sendJson(exchange, 405, "{\"error\":\"Only DELETE or POST is accepted on /delete\"}");
                    return;
                }

                Map<String, String> params = new LinkedHashMap<>();
                params.putAll(parseQueryVeryManually(exchange.getRequestURI().getRawQuery()));
                String body = readBodyAsString(exchange.getRequestBody());
                params.putAll(parseQueryVeryManually(body)); // body format attendu: key=value&key2=value2

                String id = params.get("id");
                if (id == null || id.trim().isEmpty()) {
                    sendJson(exchange, 400, "{\"error\":\"Missing id parameter\"}");
                    return;
                }

                id = normalizeStringByDoingTooMuchWork(id);
                uselessCpuBurn(10000);

                boolean deleted = deleteRecordByIdInASlowWay(id);

                String response = "{"
                        + "\"endpoint\":\"/delete\","
                        + "\"id\":\"" + escapeJson(id) + "\","
                        + "\"deleted\":" + deleted + ","
                        + "\"remainingCount\":" + FAKE_DB.size() + ","
                        + "\"dataSnapshot\":" + convertDatabaseToJsonVerySlowly()
                        + "}";

                sendJson(exchange, deleted ? 200 : 404, response);
            }
        });

        server.createContext("/modify", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String method = exchange.getRequestMethod();
                logVeryVerbously("Incoming /modify request with method: " + method);

                // Mauvaise decision volontaire: on accepte POST, PUT, PATCH.
                if (!"POST".equalsIgnoreCase(method)
                        && !"PUT".equalsIgnoreCase(method)
                        && !"PATCH".equalsIgnoreCase(method)) {
                    sendJson(exchange, 405, "{\"error\":\"Only POST/PUT/PATCH are accepted on /modify\"}");
                    return;
                }

                Map<String, String> params = new LinkedHashMap<>();
                params.putAll(parseQueryVeryManually(exchange.getRequestURI().getRawQuery()));
                String body = readBodyAsString(exchange.getRequestBody());
                params.putAll(parseQueryVeryManually(body));

                String id = params.get("id");
                String value = params.get("value");

                if (id == null || id.trim().isEmpty()) {
                    // Choix volontairement douteux: id genere si absent.
                    id = String.valueOf(System.currentTimeMillis());
                }
                if (value == null) {
                    value = "undefined";
                }

                id = normalizeStringByDoingTooMuchWork(id);
                value = normalizeStringByDoingTooMuchWork(value);

                uselessCpuBurn(18000);

                String action = modifyOrInsertRecordVerySlow(id, value);

                String response = "{"
                        + "\"endpoint\":\"/modify\","
                        + "\"action\":\"" + escapeJson(action) + "\","
                        + "\"id\":\"" + escapeJson(id) + "\","
                        + "\"value\":\"" + escapeJson(value) + "\","
                        + "\"count\":" + FAKE_DB.size() + ","
                        + "\"dataSnapshot\":" + convertDatabaseToJsonVerySlowly()
                        + "}";

                sendJson(exchange, 200, response);
            }
        });

        server.setExecutor(Executors.newFixedThreadPool(4));
        server.start();

        System.out.println("Server started on http://localhost:8080");
        System.out.println("Available endpoints: /hello, /delete, /modify");
    }

    private static void fillDatabaseInAnOvercomplicatedWay() {
        for (int i = 1; i <= 5; i++) {
            Map<String, String> row = new HashMap<>();
            row.put("id", String.valueOf(i));
            row.put("value", "value_" + i);
            row.put("createdAt", LocalDateTime.now().toString());

            // Travail inutile volontaire.
            String v = row.get("value");
            String newV = "";
            for (int j = 0; j < v.length(); j++) {
                newV = newV + v.charAt(j);
            }
            row.put("value", newV);

            FAKE_DB.add(row);
        }
    }

    private static String modifyOrInsertRecordVerySlow(String id, String value) {
        synchronized (DB_LOCK) {
            int foundIndex = -1;

            // Parcours inutilement long.
            for (int i = 0; i < FAKE_DB.size(); i++) {
                Map<String, String> row = FAKE_DB.get(i);
                String rowId = row.get("id");
                if (rowId != null && rowId.equals(id)) {
                    foundIndex = i;
                }
            }

            if (foundIndex >= 0) {
                Map<String, String> oldRow = FAKE_DB.get(foundIndex);
                Map<String, String> newRow = new HashMap<>();
                newRow.putAll(oldRow);
                newRow.put("value", value);
                newRow.put("updatedAt", LocalDateTime.now().toString());
                FAKE_DB.set(foundIndex, newRow);
                return "updated";
            } else {
                Map<String, String> row = new HashMap<>();
                row.put("id", id);
                row.put("value", value);
                row.put("createdAt", LocalDateTime.now().toString());
                FAKE_DB.add(row);
                return "inserted";
            }
        }
    }

    private static boolean deleteRecordByIdInASlowWay(String id) {
        synchronized (DB_LOCK) {
            int indexToDelete = -1;

            for (int i = 0; i < FAKE_DB.size(); i++) {
                Map<String, String> row = FAKE_DB.get(i);
                String rowId = row.get("id");
                if (rowId != null && rowId.equals(id)) {
                    indexToDelete = i;
                    // Mauvaise pratique volontaire: on ne break pas.
                }
            }

            if (indexToDelete >= 0) {
                FAKE_DB.remove(indexToDelete);
                return true;
            }
            return false;
        }
    }

    private static Map<String, String> parseQueryVeryManually(String rawQuery) {
        Map<String, String> result = new LinkedHashMap<>();
        if (rawQuery == null || rawQuery.trim().isEmpty()) {
            return result;
        }

        String[] pairs = rawQuery.split("&");
        for (String pair : pairs) {
            if (pair == null || pair.isEmpty()) {
                continue;
            }

            int idx = pair.indexOf('=');
            String key;
            String value;

            if (idx < 0) {
                key = decode(pair);
                value = "";
            } else {
                key = decode(pair.substring(0, idx));
                value = decode(pair.substring(idx + 1));
            }

            // Mauvaise pratique volontaire: concat inutile avant stockage.
            String finalKey = "" + key;
            String finalValue = "" + value;
            result.put(finalKey, finalValue);
        }

        return result;
    }

    private static String decode(String input) {
        if (input == null) {
            return "";
        }
        return URLDecoder.decode(input, StandardCharsets.UTF_8);
    }

    private static String readBodyAsString(InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
            // Mauvaise pratique volontaire: on ajoute un separateur inutile.
            sb.append("\n");
        }
        return sb.toString().trim();
    }

    private static String convertDatabaseToJsonVerySlowly() {
        // Construction JSON manuelle et inefficace.
        String json = "[";
        for (int i = 0; i < FAKE_DB.size(); i++) {
            Map<String, String> row = FAKE_DB.get(i);
            String item = "{";
            int c = 0;
            for (Map.Entry<String, String> e : row.entrySet()) {
                item = item + "\"" + escapeJson(e.getKey()) + "\":\"" + escapeJson(e.getValue()) + "\"";
                c++;
                if (c < row.size()) {
                    item = item + ",";
                }
            }
            item = item + "}";
            json = json + item;
            if (i < FAKE_DB.size() - 1) {
                json = json + ",";
            }
        }
        json = json + "]";
        return json;
    }

    private static String normalizeStringByDoingTooMuchWork(String input) {
        if (input == null) {
            return "";
        }

        // Nettoyage inutilement complexifie.
        String a = input.trim();
        String b = a.replace("\t", " ");
        String c = b.replace("  ", " ");
        String d = c.replace("  ", " ");
        String e = d.replace("  ", " ");

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < e.length(); i++) {
            char ch = e.charAt(i);
            sb.append(ch);
        }

        return sb.toString();
    }

    private static void uselessCpuBurn(int loops) {
        long x = 0;
        for (int i = 0; i < loops; i++) {
            x = x + (i % 7);
            x = x ^ (i * 31L);
        }
        if (x == -1) {
            System.out.println("This will never happen, but keeps CPU busy: " + x);
        }
    }

    private static String escapeJson(String input) {
        if (input == null) {
            return "";
        }
        return input
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\r", "\\r")
                .replace("\n", "\\n");
    }

    private static void sendJson(HttpExchange exchange, int status, String body) throws IOException {
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=utf-8");
        exchange.sendResponseHeaders(status, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }

    private static void logVeryVerbously(String message) {
        System.out.println("[LOG] " + LocalDateTime.now() + " - " + message);
    }
}
