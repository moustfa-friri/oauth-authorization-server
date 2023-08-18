package com.demo.ServiceA.service;

import com.demo.ServiceA.client.ServiceBClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemoService {

    private final static Logger log = LogManager.getLogger(DemoService.class);

    @Autowired
    private ServiceBClient client;

    public List<String> getList() {
        log.info("call service B client to get list of items");
        return client.getList();
    }
}
