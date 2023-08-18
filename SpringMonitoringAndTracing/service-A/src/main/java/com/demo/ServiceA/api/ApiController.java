package com.demo.ServiceA.api;

import com.demo.ServiceA.service.DemoService;
import jakarta.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class ApiController {

    private final static Logger log = LogManager.getLogger(ApiController.class);

    @Autowired
    private DemoService service;

    @PostMapping("test")
    public ResponseEntity getList(@RequestBody Map<String, Object> payload) {

        return ResponseEntity.ok(service.getList());
    }

}