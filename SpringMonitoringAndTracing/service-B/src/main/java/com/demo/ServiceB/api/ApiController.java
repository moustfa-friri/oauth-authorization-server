package com.demo.ServiceB.api;

import com.demo.ServiceB.service.DemoServiceB;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ApiController {

    private final static Logger log = LogManager.getLogger(ApiController.class);

    @Autowired
    private DemoServiceB service;
    @GetMapping
    public ResponseEntity getList() {
    log.info("process list of items");
    return ResponseEntity.ok(service.getList());
    }

}
