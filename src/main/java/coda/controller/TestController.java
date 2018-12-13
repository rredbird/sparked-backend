package coda.controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

import coda.shared.dto.Greeting;
import coda.database.DataLayer;
import coda.kafka.KafkaConnector;
import coda.shared.logging.ILogging;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@RestController
@Component
@CrossOrigin(origins = "http://localhost:4200")
public class TestController {
    @Autowired
    private DataLayer dataLayer;

    @Autowired
    private ILogging log;

    @Autowired
    private KafkaConnector kafkaConnector;

    public TestController() {
    }

    @GetMapping("/greeting")
    public HttpEntity<Greeting> greeting(@RequestParam(value="name", defaultValue="World") String name) {
        log.debug("Send greeting to: " + name);
        dataLayer.writeGreeting(new Greeting("Hello Coda", 0));
        Greeting greeting = dataLayer.readGreeting(0);
        greeting.add(linkTo(methodOn(TestController.class).greeting(name)).withSelfRel());
        greeting.add(linkTo(methodOn(TestController.class).heatoas()).withRel("Hateoas"));
        
        return new ResponseEntity<>(greeting, HttpStatus.OK);
    }

    @PostMapping("/upload")
    public void upload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        log.debug(file.getOriginalFilename());
    }

    @GetMapping("/kafkaTest")
    public String kafkaTest() {
        log.debug("KAFKATEST");
        log.debug("KAFKATEST");
        log.debug("KAFKATEST");
        log.debug("KAFKATEST");
        kafkaConnector.initialize();
        kafkaConnector.runProducerTestmessage();

        return "done";
    }

    @GetMapping("/hateoas")
    public HttpEntity<String> heatoas() {
        return new ResponseEntity<>("Hateoas", HttpStatus.OK);
    }

}
