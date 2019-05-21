package com.example.SimulacroParcial.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class Health {

    /**
     *
     * @return status of the microservices.
     */
    @GetMapping("")
    public String getHealth() {
        return "Status Alive";
    }

}
