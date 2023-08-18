package com.demo.ServiceA.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@FeignClient("cloud-gateway/service-B")
public interface ServiceBClient {
    @GetMapping("/")
    List<String> getList();
}
