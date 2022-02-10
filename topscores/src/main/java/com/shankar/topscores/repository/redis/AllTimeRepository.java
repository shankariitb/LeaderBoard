package com.shankar.topscores.repository.redis;

import com.shankar.topscores.domain.enums.TimePeriod;
import com.shankar.topscores.domain.jpa.GameEvent;
import com.shankar.topscores.repository.interfaces.CacheRepository;
import io.lettuce.core.ZAddArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class AllTimeRepository extends BaseRedisRepository implements CacheRepository{

    @Value("${redis.allTimeSet}")
    private String allTimeSet;

    @Override
    public void saveEntry(GameEvent gameEvent) {
        saveEntry(gameEvent,allTimeSet);
    }

    @Override
    public List<GameEvent> getEntries(Integer n) {
        return getEntries(n,allTimeSet);
    }

    @Override
    public Long getTotalCount(){
        return getTotalCount(allTimeSet);
    }

    @Override
    public void deleteEntries(Long toRemove){
        deleteEntries(toRemove,allTimeSet);
    }
}
