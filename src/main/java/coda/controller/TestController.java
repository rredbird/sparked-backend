package coda.controller;

import org.apache.commons.logging.*;

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
import coda.shared.logging.ILogging;
import coda.shared.properties.Properties;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@RestController
@Component
@CrossOrigin(origins = Properties.CorsOriginAdress)
public class TestController {
    @Autowired
    private DataLayer dataLayer;

    @Autowired
    private ILogging log;

    public TestController() {
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

        return "done";
    }

    @GetMapping("/hateoas")
    public HttpEntity<String> heatoas() {
        Log logger = LogFactory.getLog(TestController.class);

        logger.error("LOOOOL");
        log.debug("HATEOAS");
        return new ResponseEntity<>("Hateoas", HttpStatus.OK);
    }

}
