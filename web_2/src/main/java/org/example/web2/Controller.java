package org.example.web2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/name")
    public String getName() {
        return "Костенко Надія КП-22";
    }
}