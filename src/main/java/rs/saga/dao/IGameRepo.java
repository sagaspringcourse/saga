package rs.saga.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.saga.domain.Game;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-26
 */
@Repository
public interface IGameRepo extends JpaRepository<Game, Long> {
}
