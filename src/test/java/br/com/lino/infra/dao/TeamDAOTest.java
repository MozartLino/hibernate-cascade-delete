package br.com.lino.infra.dao;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.lino.model.Person;
import br.com.lino.model.Team;
import br.com.lino.util.HibernateUtil;

@ContextConfiguration({ "/applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TeamDAOTest {

	@Autowired
	public TeamDAO teamDAO;

	@Autowired
	public PersonDAO personDAO;

	@Test
	public void shouldRemovedAllEntityWithObjectDetached() {
		teamDAO.delete(new Team(1L));

		assertTrue("Should Removed all people from detached object", personDAO.list().isEmpty());
	}

	@Test
	public void shouldRemoveAllEntityWithObjectManaged() {
		teamDAO.delete(teamDAO.load(1L));

		assertTrue("Should Removed all people from managed object", personDAO.list().isEmpty());
	}

	@Before
	public void setUp() {
		Session session = HibernateUtil.getCurrentSession();
		HibernateUtil.beginTransaction();
		session.save(new Team(1L, "Team", Arrays.asList(
				new Person("Person One"), new Person("Person Two"))));
		HibernateUtil.commit();
		HibernateUtil.closeSession();
	}

}
