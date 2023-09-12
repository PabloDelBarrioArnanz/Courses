import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Lesson2Example {

    private static final String INPUT_FILE = "./Part3/resources/war_and_peace.txt";
    private static final int NUMBER_OF_THREADS = 4;

    public static void main(String[] args) throws IOException {

        String txt = new String(Files.readAllBytes(Paths.get(INPUT_FILE)));
        startServer(txt);

    }

    public static void startServer(String txt) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0); //backlog = queue
        server.createContext("/search", new WordCountHandler(txt));
        /*
        Custom ThreadPoolExecutor creation, has number of threads cores, max of threads, keep alive time and queue for task
        new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        */
        Executor executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        server.setExecutor(executor);
        server.start();
    }

    public static class WordCountHandler implements HttpHandler {

        private final String text;

        public WordCountHandler(String text) {
            this.text = text;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String query = exchange.getRequestURI().getQuery();
            String[] keyValue = query.split("=");
            String action = keyValue[0];
            String word = keyValue[1];

            if (!action.equals("word")) {
                exchange.sendResponseHeaders(400, 0);
            } else {
                long count = countWord(word);
                byte[] response = Long.toString(count).getBytes();
                exchange.sendResponseHeaders(200, response.length);
                OutputStream outputStream = exchange.getResponseBody();
                outputStream.write(response);
                outputStream.close();
            }
        }

        private long countWord(String word) {
            long count = 0;
            int index = 0;
            while (index >= 0) {
                index = text.indexOf(word, index);
                if (index >= 0) {
                    count++;
                    index++;
                }
            }
            return count;
        }
    }

}