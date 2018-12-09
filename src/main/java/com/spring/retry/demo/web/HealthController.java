package com.spring.retry.demo.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
public class HealthController {

    @RequestMapping("/health")
    public ResponseEntity<String> getStatus() {
        Random random = new Random();
        int randomInt = random.nextInt(2);

        if (randomInt == 1)
            return new ResponseEntity<String>("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<String>("Ok", HttpStatus.OK);


    }

    @RequestMapping("/test")
    public ResponseEntity<Map<String, String>> test() {

        Map<String, String> map = new HashMap<>();
        map.put("name", "Simon");
        map.put("id", "432432");


        return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);


    }


}
