package rs.saga.dao;

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
import rs.saga.domain.Player;
import rs.saga.domain.Team;

import static org.junit.Assert.assertNotNull;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-15
 */
@ContextConfiguration
@RunWith(SpringRunner.class)
@Transactional
public class TeamRepositoryIT {

    @Autowired
    private ITeamRepo teamRepo;

    @Commit
    @Test
    public void save() throws Exception {

        Player nino = new PlayerBuilder().setFirstName("Nikola").setLastName("Ninovic").setEmail("nikola.n@saga.rs").createPlayer();
        Player slave = new PlayerBuilder().setFirstName("Slavisa").setLastName("Avramoviuc").setEmail("nikola.n@saga.rs").createPlayer();


        Team buducnost = new Team("Buducnost");
        buducnost.getPlayers().add(nino);
        buducnost.getPlayers().add(slave);
        slave.setTeam(buducnost);
        nino.setTeam(buducnost);

        teamRepo.save(buducnost);

        // asserting saving by checking that ID is generated and assigned
        assertNotNull(buducnost.getId());
    }



    @Test
    public void findByName() throws Exception {
        Team partizan = teamRepo.findByName("Partizan");

        assertNotNull(partizan.getId());
    }


    @Configuration
    @EnableJpaRepositories
    @Import(DataSourceConfig.class)
    static class TestConfig {
    }

}