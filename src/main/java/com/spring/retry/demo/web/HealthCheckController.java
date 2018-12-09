package com.spring.retry.demo.web;

import com.spring.retry.demo.service.HealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @Autowired
    private HealthService healthService;

    @GetMapping("/check")
    public String checkStatus() {
        healthService.clearAttempt();

        return healthService.getHealth();
    }

}
