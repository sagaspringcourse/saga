package rs.saga.dao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import rs.saga.domain.Player;
import rs.saga.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-15
 */
@Repository
@Transactional
@Profile("jpa")
public class JPAPlayerRepository implements IPlayerRepo {

    private EntityManager entityManager;

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

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }




}
