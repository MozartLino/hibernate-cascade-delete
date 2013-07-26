package br.com.lino.model;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.lino.util.HibernateUtil;

@SuppressWarnings("unchecked")
public class TeamTest {

	public Session session;

	@Test
	public void shouldRemovedAllEntityWithObjectDetached() {
		session.delete(new Team(1L));
		List<Person> people = session.createCriteria(Person.class).list();

		assertTrue("Should Removed all people from detached object", people.isEmpty());
	}

	@Test
	public void shouldRemoveAllEntityWithObjectManaged() {
		Team team = (Team) session.load(Team.class, 1L);
		session.delete(team);
		List<Person> people = session.createCriteria(Person.class).list();

		assertTrue("Should Removed all people from managed object", people.isEmpty());
	}

	@Before
	public void setUp() {
		session = HibernateUtil.getCurrentSession();
		HibernateUtil.beginTransaction();

		Team team = new Team("Team", Arrays.asList(new Person("Person One"), new Person("Person Two")));
		session.save(team);
		session.flush();
		session.clear();
	}

	@After
	public void setDown() {
		HibernateUtil.rollbackTransaction();
		HibernateUtil.closeSession();
	}

}
