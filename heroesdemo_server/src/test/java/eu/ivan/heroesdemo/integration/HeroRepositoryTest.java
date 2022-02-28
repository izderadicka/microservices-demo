package eu.ivan.heroesdemo.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashSet;
import java.util.List;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import eu.ivan.heroesdemo.entity.Hero;
import eu.ivan.heroesdemo.repository.HeroRepository;

@DataJpaTest
public class HeroRepositoryTest {
	
	@Autowired
	private HeroRepository heroes;
	
	@Test
	public void testListAll() {
		
		List<Hero> result = heroes.findAll();
		assertEquals(10, result.size());
	}
	
	@Test
	public void testSearch() {
		String token = "ma";
		List<Hero> result = heroes.findByName(token);
		assertEquals(4, result.size());
		
		// Search result is repeatable
		int numUnique = getUnique(result, () -> heroes.findByName(token), 3);
		assertEquals(1, numUnique);
		
	}
	
	@Test
	public void testRandom() {
		List<Hero> result = heroes.findRandom();
		assertEquals(4, result.size());
		
		// returns different results
		
		int numUnique = getUnique(result, () -> heroes.findRandom(), 3);
		assertTrue(numUnique>1);
	}
	
	
	private int getUnique(List<Hero> initial, Supplier<List<Hero>> heroes, int numberOfRuns) {
		
		HashSet<List<Hero>> uniqueMembers = new HashSet();
		
		uniqueMembers.add(initial);
		
		for (int count =0; count < numberOfRuns; count++) {
			uniqueMembers.add(heroes.get());
		}
		
		return uniqueMembers.size();
		
	}
	

}
