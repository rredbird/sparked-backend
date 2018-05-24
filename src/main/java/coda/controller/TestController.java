package coda.controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import coda.shared.dto.Greeting;
import coda.database.DataLayer;
import coda.shared.logging.Logging;

@RestController
@Component
public class TestController {
    @Autowired
    private DataLayer dataLayer;

    @Autowired
    private Logging log;

    public TestController() {
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        log.debug("Send greeting to: " + name);
        dataLayer.writeGreeting(new Greeting(0, "Hello Coda"));
        return dataLayer.readGreeting(0);
    }
}
