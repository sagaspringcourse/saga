package rs.saga.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import rs.saga.domain.Player;
import rs.saga.domain.Team;

import java.util.List;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-15
 */
@Repository
@Transactional
@Profile("hibernate")
public class HibernatePlayerRepository implements IPlayerRepo {

    private SessionFactory sessionFactory;

    @Autowired
    public HibernatePlayerRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Player> findAll() {
        return null;
    }

    @Override
    public List<Player> findPlayersWithPositionalParameter(Integer ageMin, Integer ageMax) {
        return null;
    }

    @Override
    public List<Player> findPlayersUsingNamedParameters(Integer ageMin, Integer ageMax) {
        return null;
    }

    @Override
    public List<Team> findTeamsUsingJoin() {
        return null;
    }

    @Override
    public List<Team> findTeamsUsingFunction() {
        return null;
    }

    @Override
    public List<Team> findTeamsUsingNamedQuery() {
        return null;
    }

    @Override
    public List<Player> findAllUsingNativeQuery() {
        return null;
    }

    @Override
    public Long countPlayersUsingStoredProcedure(long playerId) {
        return null;
    }


    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
