package com.example.consumer;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Slf4j
public class MainService {
    private final RestTemplate restTemplate;
    int count = 0;

    public String normal(){
        return "OK";
    }

    @CircuitBreaker(name = "backendA", fallbackMethod = "fallback")
    public String slow(){
        return restTemplate.getForObject("http://localhost:9000/slow", String.class);
    }

    @Retry(name = "backendA", fallbackMethod = "fallback")
    public String fail(){
        log.info("Attempt {} time(s). At {}", ++count, getTime());
        return restTemplate.getForObject("http://localhost:9000/fail", String.class);
    }

    public String fallback(Exception e){
        return "Provider Service is down";
    }

    private String getTime(){
        return DateTimeFormatter.ofPattern("mm:ss").format(LocalDateTime.now());
    }
}
