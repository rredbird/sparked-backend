package coda.controller;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import coda.evaluationService.IEvaluationService;
import coda.shared.logging.ILogging;
import coda.shared.properties.Properties;

@RestController
@CrossOrigin(origins = Properties.CorsOriginAdress)
public class EvaluationController {
    @Autowired
    private IEvaluationService evaluationService;

    @Autowired
    private ILogging log;

    public EvaluationController() {
    }

    @GetMapping("/evaluation")
    public String evaluation() {
        return "";
    }
}


