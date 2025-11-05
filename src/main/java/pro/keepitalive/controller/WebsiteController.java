package pro.keepitalive.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.keepitalive.Website;
import pro.keepitalive.service.WebsiteService;

import java.util.List;

@RestController
@RequestMapping("/api/websites")
@RequiredArgsConstructor
public class WebsiteController {

    private final WebsiteService websiteService;

    @GetMapping
    public ResponseEntity<List<Website>> getAllWebsites() {
        var websites = websiteService.getAllWebsites();
        return ResponseEntity.ok(websites);
    }

    @PostMapping
    public ResponseEntity<Website> addWebsite(@RequestParam String url) {
        if (url == null || url.isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        var newWebsite = websiteService.addWebsite(url);
        websiteService.checkWebsite(newWebsite);
        return new ResponseEntity<>(newWebsite, HttpStatus.CREATED);
    }
}