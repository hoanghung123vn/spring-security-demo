package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    @GetMapping(value = {"/", "/home"})
    public String homepage() {
        return "home"; // Trả về home.html
    }

    @GetMapping("/hello")
    public String hello(Model model, OAuth2AuthenticationToken authentication) {
        OAuth2AuthorizedClient client = authorizedClientService
                .loadAuthorizedClient(
                        authentication.getAuthorizedClientRegistrationId(),
                        authentication.getName());
        model.addAttribute("name", authentication.getPrincipal().getAttribute("name"));
        return "hello"; // Trả về hello.html
    }
}
