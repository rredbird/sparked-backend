package coda.controller;

import java.util.List;

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

import coda.shared.dto.*;
import coda.database.DataLayer;
import coda.configurationService.IConfigurationService;
import coda.shared.logging.ILogging;
import coda.shared.properties.Properties;

@RestController
@Component
@CrossOrigin(origins = Properties.CorsOriginAdress)
public class ConfigurationController {
    @Autowired
    private DataLayer dataLayer;

    @Autowired
    private IConfigurationService configurationService;

    @Autowired
    private ILogging log;

    public ConfigurationController() {
    }

    @GetMapping("/classifiers")
    public List<ClassifierDto> classifiers() {
        return configurationService.getClassifiers().getClassifiers();
    }

    @GetMapping("/validationmethods")
    public List<Validator> validationMethods() {
        return configurationService.getValidationMethods().getValidators();
    }

    @GetMapping("/evaluationmetrics")
    public List<EvaluationMetric> evaluationMetrics() {
        return configurationService.getEvaluationMetrics().getMetrics();
    }

    @GetMapping("/datasets")
    public List<Dataset> datasets() {
        return configurationService.getDatasets().getDatasets();
    }
}
