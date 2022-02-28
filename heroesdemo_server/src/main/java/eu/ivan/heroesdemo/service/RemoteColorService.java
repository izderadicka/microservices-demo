package eu.ivan.heroesdemo.service;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;

import reactor.core.publisher.Mono;

@Component
@Primary
public class RemoteColorService implements ColorService {

    private static final Logger log = LoggerFactory.getLogger(RemoteColorService.class);

    private WebClient.Builder clientBuilder;
    private Environment env;

    
    @Autowired
    public RemoteColorService(Builder clientBuilder, Environment env) {
        this.clientBuilder = clientBuilder;
        this.env = env;
    }



    @Override
    public String getColorForName(String name) {
        log.debug("Calling remote color service");
        WebClient client = clientBuilder.baseUrl(env.getProperty("service.color.baseUrl")).build();
        Mono<String> result = client.get().uri("/color")
        .retrieve()
        .bodyToMono(HashMap.class)
        .retry(3)
        .map(map -> (String) map.get("webColor"));

        return result.block();
    }
    
}
