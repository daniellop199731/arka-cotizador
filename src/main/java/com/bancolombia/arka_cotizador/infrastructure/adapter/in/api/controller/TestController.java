package com.bancolombia.arka_cotizador.infrastructure.adapter.in.api.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    
    @Value("${server.port}")
    private String port;
    
    @GetMapping("")
    public ResponseEntity<String> test() throws UnknownHostException{
        String hostName = InetAddress.getLocalHost().getHostName();
        return ResponseEntity.ok(String.format("Hello World from arka-cotizador %s on port %s!", hostName, port));
    }    
}
