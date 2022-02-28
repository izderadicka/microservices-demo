package eu.ivan.colorservice.service;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import eu.zderadicka.spoiler.Spoiler;
import reactor.core.publisher.Mono;

public class RandomColorService implements ColorService{

    private Spoiler spoiler;

    @Autowired
    public RandomColorService(Spoiler spoiler) {
        this.spoiler = spoiler;
    }

    public Mono<Color> generateColor(Optional<String> name) {
        spoiler.spoil();
        byte[] colors = new byte[3];
        Random rng = name.map(s -> new Random(name.hashCode())).orElseGet(()-> new Random());
        rng.nextBytes(colors);
        String result = "#";

        for (int i=0; i< colors.length; i++) {
            int newColor =(int) ((colors[i] & 0xFF) * 0.75);
            result += String.format("%1$02x", newColor);
        }

        


        return Mono.just( new Color(result));
    }
    
}
