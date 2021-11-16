package api.utils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class Utils {
    public static String httpGetUrl(URI uri) throws IOException, InterruptedException {
        final var client = HttpClient
                .newBuilder()
                .connectTimeout(Duration.ofSeconds(30))
                .build();

        final var request = HttpRequest
                .newBuilder()
                .uri(uri)
                .build();

        final var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
