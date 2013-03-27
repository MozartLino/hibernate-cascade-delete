package br.com.lino.model;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("unchecked")
public class TeamTest {

	public Session session;
	private SessionFactory sessionFactory;
	private Transaction tx;

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
		sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		tx = session.beginTransaction();

		truncateTables();

		Team team = new Team("Team", Arrays.asList(new Person("Person One"), new Person("Person Two")));
		session.save(team);
		session.flush();
		session.clear();
	}

	@After
	public void setDown() {
		tx.commit();
		session.close();
		sessionFactory.close();
	}

	private void truncateTables() {
		session.createSQLQuery("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();
		session.createSQLQuery("truncate table Team_Person").executeUpdate();
		session.createSQLQuery("truncate table Team").executeUpdate();
		session.createSQLQuery("truncate table Person").executeUpdate();
		session.createSQLQuery("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();
	}

}
