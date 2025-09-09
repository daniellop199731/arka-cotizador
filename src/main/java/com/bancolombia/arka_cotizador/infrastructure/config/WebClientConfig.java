package com.bancolombia.arka_cotizador.infrastructure.config;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.Data;

@Component
@Data
public class WebClientConfig {

    private String domain;
    private String port;
    private WebClient webClienteProductos;

    public static final String PRODUCTO_PRICE_REACTIVE = "/price/reactive/";
    
    public WebClientConfig (WebClient.Builder webClientBuilder){
        this.domain = "localhost";
        this.port = "8082";
        this.webClienteProductos = webClientBuilder.baseUrl("http://"+domain+":"+port+"/productos").build();
    }

    public void resetWebClient(String domain, String port){
        WebClient.Builder webClientBuilder = WebClient.builder();
        this.setDomain(domain);
        this.setPort(port);
        this.setWebClienteProductos(webClientBuilder.baseUrl("http://"+domain+":"+port+"/productos").build());
    }

}
