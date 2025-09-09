package com.bancolombia.arka_cotizador.domain.port.in;

import java.util.List;

import com.bancolombia.arka_cotizador.domain.model.CotizacionResponse;

import reactor.core.publisher.Mono;

public interface CotizacionUseCase {

    Mono<Double> totalPriceProducts(List<Integer> idsProductos);

    Mono<CotizacionResponse> crearCotizacion(List<Integer> idsProductos);
}