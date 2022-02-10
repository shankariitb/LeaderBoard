package com.shankar.topscores.repository.redis;

import com.shankar.topscores.domain.jpa.GameEvent;
import com.shankar.topscores.repository.interfaces.CacheRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WeeklyRepository extends BaseRedisRepository implements CacheRepository {
    @Value("${redis.weeklySet}")
    private String weeklySet;

    @Override
    public void saveEntry(GameEvent gameEvent) {
        saveEntry(gameEvent,weeklySet);
    }

    @Override
    public List<GameEvent> getEntries(Integer n) {
        return getEntries(n,weeklySet);
    }

    @Override
    public Long getTotalCount(){
        return getTotalCount(weeklySet);
    }

    @Override
    public void deleteEntries(Long toRemove){
        deleteEntries(toRemove,weeklySet);
    }
}
