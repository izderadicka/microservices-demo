package eu.ivan.heroesdemo.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import eu.ivan.heroesdemo.entity.Hero;

public class HeroTest {
	
	@Test
	/// We do not have content based equality
	public void testEquality() {
		Hero h1 = new Hero(1,"Name");
		Hero h2 = new Hero(1,"Name");
		
		assertFalse( h1.equals(h2), "Using .equals");
		
		assertNotEquals(h1, h2);
	}

}
