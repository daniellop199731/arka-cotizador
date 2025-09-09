package com.bancolombia.arka_cotizador.application.useCase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bancolombia.arka_cotizador.domain.model.CotizacionResponse;
import com.bancolombia.arka_cotizador.domain.port.in.CotizacionUseCase;
import com.bancolombia.arka_cotizador.infrastructure.config.WebClientConfig;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CotizacionApplicationService implements CotizacionUseCase {

    private final WebClientConfig clientConfig;

    @Override
    public Mono<Double> totalPriceProducts(List<Integer> idsProductos) {
        
        Flux<Double> fluxPrices = Flux.fromIterable(idsProductos)
                                    .flatMap(idProducto -> 
                                        clientConfig.getWebClienteProductos()
                                        .get().uri(WebClientConfig.PRODUCTO_PRICE_REACTIVE + "{idProducto}", idProducto)
                                        .retrieve()
                                        .bodyToMono(Double.class)
                                        .doOnNext(price-> System.out.println(price)));

        return fluxPrices.reduce(0.0, Double::sum);
    }

    @Override
    public Mono<CotizacionResponse> crearCotizacion(List<Integer> idsProductos) {

        Flux<Double> fluxPrices = Flux.fromIterable(idsProductos)
                                    .flatMap(idProducto -> 
                                        clientConfig.getWebClienteProductos()
                                        .get().uri(WebClientConfig.PRODUCTO_PRICE_REACTIVE + "{idProducto}", idProducto)
                                        .retrieve()
                                        .bodyToMono(Double.class)
                                        .doOnNext(price-> System.out.println(price)));        

        return fluxPrices.reduce(0.0, Double::sum)
            .flatMap(total ->{
                CotizacionResponse response = new CotizacionResponse();
                response.setTotal(total);
                response.setIdsProductos(idsProductos);
                return Mono.just(response);
        });
    }

    

}
