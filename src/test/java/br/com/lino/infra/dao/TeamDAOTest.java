package br.com.lino.infra.dao;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import br.com.lino.model.Person;
import br.com.lino.model.Team;

@ContextConfiguration({ "/applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(defaultRollback = true)
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
		teamDAO.save(new Team(1L, "Team", Arrays.asList(new Person("Person One"), new Person("Person Two"))));
		teamDAO.clear();
	}

}
