package coda.controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import coda.database.DataLayer;
import coda.order.Order;
import coda.shared.dto.Greeting;
import coda.shared.logging.ILogging;
import coda.shared.properties.Properties;

@RestController
@Component
@CrossOrigin(origins = Properties.CorsOriginAdress)
public class TestController {
    @Autowired
    private DataLayer dataLayer;

    @Autowired
    private Properties properties;

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

    @GetMapping("selfTest")
    public HttpEntity<String> selfTest() {
        log.info("Selftest is starting...");
        String propertiesInfo = "";
        
        propertiesInfo += properties.getMongoDatabaseIP() + "\n";
        propertiesInfo += properties.getMongoDatabaseName() + "\n";
        propertiesInfo += properties.getMongoDatabasePort() + "\n";

        Order testorder = new Order();
        String testorderName = "testorder";
        testorder.setName(testorderName);

        UUID testorderID = testorder.getId();

        dataLayer.saveOrder(testorder);
        int orderCount = dataLayer.getOrders().size();
        if(orderCount < 1) {
            return new ResponseEntity<>("DataLayer access not working as expected, orders size error.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info("Orders in dataLayer: " + orderCount);

        Order loadedOrder = dataLayer.getOrder(testorderID);
        if(loadedOrder.getId().equals(testorderID) || !loadedOrder.getName().equals(testorderName)) {
            return new ResponseEntity<>("DataLayer access not working as expected, testorder error.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info("Testorder successfully saved and loaded");

        return new ResponseEntity<>("Selftest succeeded", HttpStatus.OK);
    }

    @GetMapping("/testDataLayer")
    public HttpEntity<Greeting> testDataLayer() {
        return new ResponseEntity<>(dataLayer.readGreeting(0), HttpStatus.OK);
    }

    @GetMapping("/hateoas")
    public HttpEntity<String> heatoas() {
        return new ResponseEntity<>("Hateoas", HttpStatus.OK);
    }

    private void exceptionThrower() throws Exception {
        throw new Exception("This exception is expected.");
    }

    @GetMapping("/exception")
    public HttpEntity<String> exception()  throws Exception {
        try{
            exceptionThrower();
        } catch(Exception exception) {
            throw exception;
        }
        return new ResponseEntity<>("exception", HttpStatus.OK);
    }
}
