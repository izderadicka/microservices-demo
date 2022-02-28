package eu.ivan.heroesdemo.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import eu.ivan.heroesdemo.entity.Hero;
import eu.ivan.heroesdemo.repository.HeroRepository;
import eu.ivan.heroesdemo.rest.HeroController;
import eu.ivan.heroesdemo.service.ColorService;
import eu.ivan.heroesdemo.service.LocalColorService;


@ExtendWith(MockitoExtension.class)
public class ControllerTest {

    @Mock
    private HeroRepository heroes;


    @Test
    public void testHeroSave() {
        ColorService colorService = new LocalColorService();
        HeroController ctrl = new HeroController(heroes, colorService);

        when(heroes.save(any(Hero.class))).thenAnswer(
            invocation -> {
                Hero hero = (Hero) invocation.getArguments()[0];
                hero.setId(1L);
                return hero;
            }
        );

        Hero h = new Hero("Usak");
        h = ctrl.save(h);
        assertEquals(1L, h.getId());
        assertEquals(7, h.getColor().length());

    }
    
}
