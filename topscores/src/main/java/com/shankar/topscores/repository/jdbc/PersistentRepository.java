package com.shankar.topscores.repository.jdbc;

import com.shankar.topscores.domain.jpa.GameEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
public class PersistentRepository extends BasePersistentRepository implements PersistenceInterface{

    private static final Logger logger = LoggerFactory.getLogger(PersistentRepository.class);


    @Override
    public void saveEvent(GameEvent gameEvent) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        StringBuilder query = new StringBuilder("insert into gamescore (name,score,created_on,modified_on) values (?,?,NOW(),NOW()) on duplicate key update modified_on = case when values(score)>score then values(modified_on) else modified_on end, score = case when values(score)>score then values(score) else score end");
        parameters.addValue("name",gameEvent.getName());
        parameters.addValue("score",gameEvent.getScore());
        try {
            this.getWriteAccess().update(con -> {
                PreparedStatement ps = con.prepareStatement(query.toString());
                ps.setString(1, gameEvent.getName());
                ps.setInt(2, gameEvent.getScore());
                return ps;
            });
        }
        catch (Exception e){
            logger.error("Exception in PersistentSaveEvent",e);
        }
    }
}
