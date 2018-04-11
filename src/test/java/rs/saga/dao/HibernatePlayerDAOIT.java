package rs.saga.dao;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import rs.saga.config.HibernateConfig;
import rs.saga.domain.Player;
import rs.saga.domain.Team;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-30
 */
@ContextConfiguration
@RunWith(SpringRunner.class)
@Profile("hibernate")
public class HibernatePlayerDAOIT {

    @Autowired
    private IPlayerRepo playerRepo;

    @Test
    public void testFindAll() {
        List<Player> all = playerRepo.findAll();

        assertEquals(7, all.size());
    }

    @Test
    public void testFindPlayersPositional() {
        List<Player> all = playerRepo.findPlayersWithPositionalParameter(28, 30);

        assertEquals(2, all.size());
    }

    @Test
    public void testFindPlayersNamed() {
        List<Player> all = playerRepo.findPlayersWithNamedParameter(28, 30);

        assertEquals(2, all.size());
    }

    @Test
    public void testNamedQuery() {
        List<Team> all = playerRepo.findTeamsNamed();

        assertEquals(2, all.size());
    }

    @Test
    public void testFindTeams() {
        List<Team> all = playerRepo.findTeams();

        assertEquals(2, all.size());
    }

    @Test
    public void testFindFunctions() {
        List<Team> all = playerRepo.findTeamsFunctionTest();

        assertEquals(1, all.size());
    }


    @Import(HibernateConfig.class)
    @Configuration
    static class HibConfig {

        @Bean
        public IPlayerRepo hibernatePlayerRepo(SessionFactory sessionFactory) {
            return new HibernatePlayerRepository(sessionFactory);
        }
    }

}
