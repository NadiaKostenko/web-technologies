package org.example.lab_3.api;

import org.springframework.web.bind.annotation.GetMapping;

public class Controller {
    @GetMapping("/")
    public String index()
    {
        return "index";
    }
}
