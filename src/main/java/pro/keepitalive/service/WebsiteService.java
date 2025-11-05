package pro.keepitalive.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.keepitalive.Website;
import pro.keepitalive.repository.WebsiteRepository;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WebsiteService {

    private final WebsiteRepository websiteRepository;
    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public List<Website> getAllWebsites() {
        return websiteRepository.findAll();
    }

    public Website addWebsite(String url) {
        var website = new Website();
        website.setUrl(url);
        website.setStatus("PENDING");
        return websiteRepository.save(website);
    }

    public void checkWebsite(Website website) {
        try {
            var request = HttpRequest.newBuilder()
                    .uri(URI.create(website.getUrl()))
                    .GET()
                    .build();

            var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() >= 200 && response.statusCode() < 300) {
                website.setStatus("UP");
            } else {
                website.setStatus("DOWN - " + response.statusCode());
            }

        } catch (IOException | InterruptedException e) {
            website.setStatus("DOWN - " + e.getMessage());
        }

        website.setLastChecked(LocalDateTime.now());
        websiteRepository.save(website);
    }

    public void checkAllWebsites() {
        var websites = getAllWebsites();
        for (Website website : websites) {
            checkWebsite(website);
        }
    }
}