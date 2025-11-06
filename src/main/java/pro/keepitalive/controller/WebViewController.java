package pro.keepitalive.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pro.keepitalive.service.WebsiteService;

@Controller
@RequiredArgsConstructor
public class WebViewController {

    private final WebsiteService websiteService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("websites", websiteService.getAllWebsites());
        return "index";
    }

    @PostMapping("/add")
    public String addWebsite(@RequestParam String url) {
        if (url != null && !url.isBlank()) {
            websiteService.addWebsite(url);
        }
        return "redirect:/";
    }
}