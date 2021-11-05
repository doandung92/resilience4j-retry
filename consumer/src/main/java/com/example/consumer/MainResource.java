package com.example.consumer;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainResource {
    private final MainService service;

    @GetMapping("/normal")
    public String normal(){
        return service.normal();
    }


    @GetMapping("/slow")
    public String slow(){
        return service.slow();
    }


    @GetMapping("/fail")
    public String fail(){
        return service.fail();
    }


}
