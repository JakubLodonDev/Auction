package com.auction.dev.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://127.0.0.1:5500")
@RestController
@RequestMapping("api/v1/dashboard")
public class DashboardController  {

    @GetMapping
    public ResponseEntity<?> getDashboard() {
        return ResponseEntity.ok().build();
    }
}
