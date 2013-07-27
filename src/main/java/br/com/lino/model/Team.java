package br.com.lino.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Team {

	@Id
	private Long id;

	private String name;

	@OneToMany
	@JoinColumn(name = "team_id")
	@Cascade(CascadeType.ALL)
	private List<Person> people;

	protected Team() {
	}

	public Team(Long id) {
		this.id = id;
	}

	public Team(Long id, String name, List<Person> people) {
		this.id = id;
		this.name = name;
		this.people = people;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Person> getPeople() {
		return people;
	}

}
