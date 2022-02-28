package eu.ivan.heroesdemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import eu.ivan.heroesdemo.entity.Hero;

public interface HeroRepository extends JpaRepository<Hero, Long> {
	
	@Query("from Hero order by name")
	public List<Hero> findAllOrderByName();
	
	@Query("from Hero h where lower(h.name) like concat('%', lower(?1), '%') order by h.name")
	public List<Hero> findByName(String token);
	
	//TODO: This is very inefficient way how to get random row, also PG dependent
	@Query(value = "select * from heroes order by random() limit 4", nativeQuery = true)
	public List<Hero> findRandom();
	

}
