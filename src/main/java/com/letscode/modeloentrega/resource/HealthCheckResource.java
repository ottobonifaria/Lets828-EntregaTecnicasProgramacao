package com.letscode.modeloentrega.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "healthcheck")
public class HealthCheckResource {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> healthcheck() {
        return ResponseEntity.status(HttpStatus.OK).body("200 OK");
    }
}
