package eu.ivan.colorservice.service;

import java.util.Optional;

import reactor.core.publisher.Mono;

public interface ColorService {
    public Mono<Color> generateColor(Optional<String> name);
}
