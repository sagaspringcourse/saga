package rs.saga.dao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import rs.saga.domain.Player;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
        System.out.println("Is Entity managed: " + isManaged(player));
        return sqlCode;
    }

    @Override
    public Boolean isManaged(Player player) {
        return entityManager.contains(player);
    }

    @Override
    public List<Player> findAll() {
        TypedQuery<Player> query = entityManager.createQuery("from Player p order by p.name", Player.class);
        return query.getResultList();
    }

    @Override
    public Player get(Long playerId) {
        return entityManager.find(Player.class, playerId);
    }
}
