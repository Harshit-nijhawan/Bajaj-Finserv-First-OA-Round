package com.bajaj.bfhl.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller to expose system health checks, required by the submission form.
 */
@RestController
@CrossOrigin(origins = "*")
public class HealthController {

    /**
     * GET /health
     * Returns a simple JSON payload indicating that the service is running.
     *
     * @return the health status response.
     */
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> checkHealth() {
        Map<String, String> statusMap = new HashMap<>();
        statusMap.put("status", "UP");
        return ResponseEntity.ok(statusMap);
    }
}
