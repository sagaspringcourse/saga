package rs.saga.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import rs.saga.builder.PlayerBuilder;
import rs.saga.config.SpringDataConfig;
import rs.saga.domain.Player;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-15
 */
@ContextConfiguration(classes = SpringDataConfig.class)
@RunWith(SpringRunner.class)
@Transactional
public class SpringDataPlayerDAOIT {

    @Autowired
    private ISpringDataPlayerRepo playerRepo;

    @Before
    public void setUp() throws Exception {
        assertNotNull(playerRepo);
    }

    @Test
    public void save() throws Exception {
        Player nino = PlayerBuilder.getInstance().nino().createPlayer();
        Player player = playerRepo.save(nino);
        assertNotNull(player.getId());
    }

    @Test
    public void findByFirstName() throws Exception {
        playerRepo.save(PlayerBuilder.getInstance().nino().createPlayer());
        Set<Player> players = playerRepo.findByFirstName("Nikola");
        assertEquals(3, players.size());
    }
}