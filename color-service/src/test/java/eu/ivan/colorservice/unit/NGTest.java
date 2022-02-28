package eu.ivan.colorservice.unit;


import java.util.Optional;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import eu.ivan.colorservice.service.ColorService;
import eu.ivan.colorservice.service.RandomColorService;
import eu.zderadicka.spoiler.NeverFailingSpoiler;

public class NGTest {

    private ColorService colorService;

    @BeforeClass
    public void initTest() {
        colorService = new RandomColorService(new NeverFailingSpoiler());
    }

    @Test
    public void testFormatNG() {
        String color = generateColor();
        assertEquals(7, color.length());
        assertEquals('#', color.charAt(0));
        System.out.println("Random color is " + color);
        assertTrue(color.chars().skip(1).allMatch(c -> (c >= '0' && c <= '9') || 
        (c>='a' && c <='f')));
    }

    @Test
    public void testSameResultForSameNameNG() {
        String c1 = generateColor("Usak");
        String c2 = generateColor("Usak");

        assertEquals(c1, c2);
    }

    private String generateColor(String name) {
        return generateColor(Optional.of(name));
    }

    private String generateColor() {
        return generateColor(Optional.empty());
    }

    private String generateColor(Optional<String> name)  {
        return colorService.generateColor(name).block().getwebColor();
    }
    
}
