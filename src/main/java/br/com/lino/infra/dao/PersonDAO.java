package br.com.lino.infra.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.lino.model.Person;

@Transactional
@Component
public class PersonDAO {

	private SessionFactory factory;
	
	public PersonDAO(){}

	@Autowired
	public PersonDAO(SessionFactory factory) {
		this.factory = factory;
	}

	@SuppressWarnings("unchecked")
	public List<Person> list() {
		return getSession().createCriteria(Person.class).list();
	}

	public Session getSession() {
		return factory.getCurrentSession();
	}

}
