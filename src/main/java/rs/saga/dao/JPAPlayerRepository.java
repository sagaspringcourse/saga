package rs.saga.dao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import rs.saga.domain.Player;
import rs.saga.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
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

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void remove(Player nino) {
        entityManager.remove(nino);
    }


    @Override
    public int save(Player player) {
        int sqlCode = 0;
        try {
            entityManager.persist(player);
        } catch (Exception e) {
            sqlCode = 1;
        }
        return sqlCode;
    }


    @Override
    public List<Player> findAll() {
        TypedQuery<Player> query = entityManager.createQuery("from Player p order by p.firstName", Player.class);
        return query.getResultList();
    }

    @Override
    public List<Player> findPlayersWithPositionalParameter(Integer ageL, Integer ageU) {
        TypedQuery<Player> query = entityManager.createQuery("from Player p where (p.age between ?1 and ?2) and firstName like '%ik%' order by p.firstName", Player.class);
        query.setParameter(1, ageL);
        query.setParameter(2, ageU);
        return query.getResultList();
    }

    @Override
    public List<Player> findPlayersWithNamedParameter(Integer ageL, Integer ageU) {
        TypedQuery<Player> query = entityManager.createQuery("from Player p where (p.age between :ageMin and :ageMax) and firstName like '%ik%' order by p.firstName", Player.class);
        query.setParameter("ageMin", ageL);
        query.setParameter("ageMax", ageU);
        return query.getResultList();
    }

    @Override
    public List<Team> findTeamsFunctionTest() {
        TypedQuery<Team> query = entityManager.createQuery("select distinct(p.team) from Player p join p.team where lower(p.team.name) = 'crvena zvezda'", Team.class);
        List<Team> teams = query.getResultList();
        return teams;
    }

    @Override
    public List<Team> findTeamsNamed() {
        TypedQuery<Team> query = entityManager.createNamedQuery("Team.withMoreThanOnePlayer", Team.class);
        List<Team> teams = query.getResultList();
        return teams;
    }

    @Override
    public List<Team> findTeams() {
        TypedQuery<Team> query = entityManager.createQuery("select distinct p.team from Player p join p.team group by p.team.name having count(*) > 2", Team.class);
        List<Team> teams = query.getResultList();
        return teams;
    }


    @Override
    public Player get(Long playerId) {
        return entityManager.find(Player.class, playerId);
    }

    @Override
    public Long countPlayers(long playerId) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("count_players")
                .registerStoredProcedureParameter(
                        "playerId", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "playerCount", Long.class, ParameterMode.OUT)
                .setParameter("playerId", playerId);

        query.execute();

        Long playerCount = (Long) query
                .getOutputParameterValue("playerCount");

        return playerCount;
    }

}
