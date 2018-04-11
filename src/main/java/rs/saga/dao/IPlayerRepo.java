package rs.saga.dao;


import rs.saga.domain.Player;
import rs.saga.domain.Team;

import java.util.List;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-15
 */
public interface IPlayerRepo {

    List<Player> findAll();

    List<Team> findTeams();

    List<Player> findPlayersWithNamedParameter(Integer ageL, Integer ageU);

    List<Team> findTeamsFunctionTest();

    List<Team> findTeamsNamed();

    List<Player> findPlayersWithPositionalParameter(Integer ageL, Integer ageU);

    Player get(Long playerId);

    void remove(Player nino);

    int save(Player player);

}
