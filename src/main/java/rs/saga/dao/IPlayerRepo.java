package rs.saga.dao;


import rs.saga.domain.Player;

import java.util.List;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-15
 */
public interface IPlayerRepo {

    List<Player> findAll();

    Player get(Long playerId);

    void remove(Player nino);

    int save(Player player);

    Boolean isManaged(Player player);
}
