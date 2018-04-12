package rs.saga.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import rs.saga.config.JPAConfig;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-30
 */
@ContextConfiguration
@RunWith(SpringRunner.class)
public class JPAPlayerDAOIT {

    @Autowired
    private IPlayerRepo playerRepo;

    @Test
    public void findAll() throws Exception {
    }

    @Test
    public void findPlayersWithPositionalParameter() throws Exception {
    }

    @Test
    public void findPlayersUsingNamedParameters() throws Exception {
    }

    @Test
    public void findTeamsUsingJoin() throws Exception {
    }

    @Test
    public void findTeamsUsingFunction() throws Exception {
    }

    @Test
    public void findTeamsUsingNamedQuery() throws Exception {
    }

    @Test
    public void findAllUsingNativeQuery() throws Exception {
    }

    @Test
    public void countPlayersUsingStoredProcedure() throws Exception {
    }

    @Configuration
    @Import(JPAConfig.class)
    static class TestConfig {

        @Bean
        public IPlayerRepo playerStateTransitionRepo() {
            return new JPAPlayerRepository();
        }

    }
}
