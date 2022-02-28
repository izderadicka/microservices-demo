package eu.ivan.heroesdemo.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.ivan.heroesdemo.service.ColorService;
import eu.ivan.heroesdemo.service.LocalColorService;

public class TestColorService {

    private ColorService colorService;

    @BeforeEach
    public void initTest() {
        colorService = new LocalColorService();
    }

    @Test
    public void testFormat() {
        String color = colorService.getColorForName("Usak");

        assertEquals(7, color.length());
        assertEquals('#', color.charAt(0));
        System.out.println("Random color is ");
        assertTrue(color.chars().skip(1).allMatch(c -> (c >= '0' && c <= '9') || 
        (c>='a' && c <='f')));
    }
    
}
