package coda.controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import coda.shared.dto.Greeting;

@RestController
public class TestController {
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(1, "Hello Coda");
    }
}