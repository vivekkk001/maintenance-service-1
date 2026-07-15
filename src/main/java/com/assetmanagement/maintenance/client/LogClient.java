package com.assetmanagement.maintenance.client;

import com.assetmanagement.maintenance.dto.LogRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "log-service", url = "http://localhost:8085")
public interface LogClient {

    @PostMapping("/logs")
    void createLog(@RequestBody LogRequestDTO request);
}