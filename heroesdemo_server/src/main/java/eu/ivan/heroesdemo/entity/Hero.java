package eu.ivan.heroesdemo.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "heroes")
public class Hero {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "color", nullable = true, length = 20)
	private String color;


	public Hero() {

	}

	public Hero(String name) {
		this.name = name;
	}

	public Hero(long id, String name) {
		this.id = id;
		this.name = name;
	}

//	@Override
//	public boolean equals(Object obj) {
//		if (obj != null && getClass() == obj.getClass()) {
//			Hero other = (Hero) obj;
//			return this.id == other.id && Objects.equals(this.name, other.name);
//
//		}
//
//		return false;
//	}
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(id, name);
//	}

	public long getId() {
		return id;
	}


	public void setId(Long heroId) {
		this.id = heroId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Hero [id=" + id + ", name=" + name + "color="+ color + "]";
	}

}
