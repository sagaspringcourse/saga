package rs.saga.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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


    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void remove(Player nino) {
        getSession().remove(nino);
    }


    @Override
    public int save(Player player) {
        int sqlCode = 0;
        try {
            getSession().save(player);
        } catch (Exception e) {
            sqlCode = 1;
        }
        return sqlCode;
    }

    @Override
    public Player get(Long playerId) {
        return getSession().get(Player.class, playerId);
    }

    @Override
    public List<Player> findAll() {
        Query query = getSession().createQuery("select p from Player p");
        List<Player> players = query.getResultList();
        return players;
    }

    @Override
    public List<Team> findTeams() {
        Query query = getSession().createQuery("select distinct p.team from Player p join p.team group by p.team.name having count(*) > 2");
        List<Team> teams = query.getResultList();
        return teams;
    }

    @Override
    public List<Team> findTeamsFunctionTest() {
        Query query = getSession().createQuery("select distinct(p.team) from Player p join p.team where lower(p.team.name) = 'Crvena Zvezda'");
        List<Team> teams = query.getResultList();
        return teams;
    }

    @Override
    public List<Team> findTeamsNamed() {
        Query query = getSession().getNamedQuery("Team.withMoreThanOnePlayer");
        List<Team> teams = query.getResultList();
        return teams;
    }

    @Override
    public List<Player> findPlayersWithPositionalParameter(Integer ageL, Integer ageU) {
        Query query = getSession().createQuery("from Player p where (p.age between ? and ?) and firstName like '%ik%' order by p.firstName");
        query.setParameter(0, ageL);
        query.setParameter(1, ageU);
        List<Player> players = query.getResultList();
        return players;
    }



    @Override
    public List<Player> findPlayersWithNamedParameter(Integer ageL, Integer ageU) {
        Query query = getSession().createQuery("from Player p where (p.age between :ageMin and :ageMax) and firstName like '%ik%' order by p.firstName");
        query.setParameter("ageMin", ageL);
        query.setParameter("ageMax", ageU);
        List<Player> players = query.getResultList();
        return players;
    }


}
