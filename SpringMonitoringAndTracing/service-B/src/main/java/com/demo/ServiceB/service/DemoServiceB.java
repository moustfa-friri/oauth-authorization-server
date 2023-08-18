package com.demo.ServiceB.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DemoServiceB {
    private final static Logger log = LogManager.getLogger(DemoServiceB.class);

    public List<String> getList(){
            log.info("fetch list of items");
        return Stream.of("A","B","C","D").collect(Collectors.toList());
    }
}
