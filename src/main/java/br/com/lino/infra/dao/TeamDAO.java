package br.com.lino.infra.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.lino.model.Team;

@Transactional
@Component
public class TeamDAO {

	private SessionFactory factory;

	public TeamDAO(){}
	
	@Autowired
	public TeamDAO(SessionFactory factory) {
		this.factory = factory;
	}

	public Team load(Long id) {
		return (Team) getSession().load(Team.class, id);
	}

	public void delete(Team team) {
		getSession().delete(team);
	}

	public Session getSession() {
		return factory.getCurrentSession();
	}

}
