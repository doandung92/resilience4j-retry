package com.example.provider;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainResource {

    @GetMapping("/slow")
    public String slow() throws InterruptedException {
        Thread.sleep(2000);
        return "SLOW";
    }

    @GetMapping("/fail")
    public String fail() throws InterruptedException {
        Thread.sleep(1000);
        throw new IllegalArgumentException();
    }

    @GetMapping("/normal")
    public String normal() {
        return "OK";
    }
}
