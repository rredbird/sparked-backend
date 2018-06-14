package coda.controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
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

import coda.shared.dto.Classifier;
import coda.database.DataLayer;
import coda.evaluationService.EvaluationService;
import coda.shared.logging.Logging;

@RestController
@Component
public class EvaluationController {
    @Autowired
    private DataLayer dataLayer;

    @Autowired
    private EvaluationService evaluationService;

    @Autowired
    private Logging log;

    public EvaluationController() {
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/classifiers")
    public Classifier[] classifiers(@RequestParam(value="name", defaultValue="World") String name) {
    }
}

