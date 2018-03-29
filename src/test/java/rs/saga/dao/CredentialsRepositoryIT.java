package rs.saga.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import rs.saga.builder.PlayerBuilder;
import rs.saga.config.DataSourceConfig;
import rs.saga.domain.Credentials;
import rs.saga.domain.Player;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-15
 */
@ContextConfiguration
@RunWith(SpringRunner.class)
@Transactional
public class CredentialsRepositoryIT {

    @Autowired
    private IPlayerRepo playerRepo;

    @Autowired
    private ICredentialsRepo credentialsRepo;

    @Before
    public void setUp() throws Exception {
        assertNotNull(playerRepo);
    }

    @Commit
    @Test
    public void save() throws Exception {
        Credentials credentials = new Credentials();
        Player nino = new PlayerBuilder().setFirstName("Nikola").setLastName("Ninovic").setEmail("nikola.n@saga.rs").createPlayer();


        credentials.setPassword("pass");
        credentials.setUsername("ninovic.n");
        credentials.setPlayer(nino);
        nino.setCredentials(credentials);

        credentialsRepo.save(credentials);

        assertNotNull(nino.getId());

        Player d = playerRepo.findOne(nino.getId());
        System.out.println(d.getCredentials().getUsername());

    }

    @Test
    public void findByFirstName() throws Exception {
        Set<Player> players = playerRepo.findByFirstName("Nikola");
        assertEquals(2, players.size());
    }

    @Configuration
    @Import(DataSourceConfig.class)
    @EnableJpaRepositories
    static class TestConfig {

    }
}