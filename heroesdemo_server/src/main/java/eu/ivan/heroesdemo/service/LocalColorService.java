package eu.ivan.heroesdemo.service;

import java.util.Arrays;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class LocalColorService implements ColorService{

    @Override
    public String getColorForName(String name) {

        byte[] colors = new byte[3];
        Random rng = new Random();
        rng.nextBytes(colors);
        String result = "#";

        for (int i=0; i< colors.length; i++) {
            int newColor =(int) ((colors[i] & 0xFF) * 0.75);
            result += String.format("%1$02x", newColor);
        }

        


        return result;
    }


    
}
