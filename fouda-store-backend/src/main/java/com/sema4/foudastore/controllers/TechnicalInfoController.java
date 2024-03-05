package com.sema4.foudastore.controllers;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/technical-infos")
public class TechnicalInfoController {



    @Value("${application.env}")
    private String env;


    @GetMapping("/")
    public ResponseEntity<Map> techInfos() {
        Map<String,String> body = Map.of("env",env );
        return ResponseEntity.ok(body);
    }
}
