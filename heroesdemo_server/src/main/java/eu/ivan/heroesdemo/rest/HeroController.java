package eu.ivan.heroesdemo.rest;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import eu.ivan.heroesdemo.entity.Hero;
import eu.ivan.heroesdemo.repository.HeroRepository;
import eu.ivan.heroesdemo.service.ColorService;

@RequestMapping("/api/heroes")
@RestController
public class HeroController {

	private static final Logger log = LoggerFactory.getLogger(HeroController.class);
	
	private HeroRepository heroes;
	private ColorService colorService;

	
	
	public HeroController(HeroRepository heroes, ColorService colorService) {
		this.heroes = heroes;
		this.colorService = colorService;
	}

	@GetMapping("")
	public List<Hero> listAll(@RequestParam(required = false) String name) {
		log.debug("Calling listAll method in controller");
		if (name != null && name.length()>0) {
			return heroes.findByName(name);
		}
		return heroes.findAllOrderByName();
	}
	
	@GetMapping("/random")
	public List<Hero> findRandom() {	
		return heroes.findRandom();
	}
	
	@PostMapping("")
	public Hero save(@RequestBody Hero hero) {
		hero.setColor(colorService.getColorForName(hero.getName()));
		if (hero.getId() != 0) throw new RuntimeException("id must not be specified on insert");
		return heroes.save(hero);
	}
	
	@GetMapping("{heroId}")
	public Hero get(@PathVariable Long heroId) {
		return heroes.findById(heroId).orElseThrow();
	}
	
	@PutMapping("{heroId}")
	public Hero update(@PathVariable Long heroId, @RequestBody Hero hero) {
		if (heroId <= 0) throw new InvalidId();
		hero.setId(heroId);
		return heroes.save(hero);
	}
	
	@PutMapping("")
	public Hero update2(@RequestBody Hero hero) {
		if (hero.getId() <= 0) throw new InvalidId();
		return heroes.save(hero);
	}
	
	@DeleteMapping("{heroId}")
	public void delete(@PathVariable Long heroId) {
		
		if (heroId <= 0) throw new InvalidId();
		heroes.deleteById(heroId);
		
	}
	

}
