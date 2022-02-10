package com.shankar.topscores.repository.redis;

import com.shankar.topscores.domain.jpa.GameEvent;
import com.shankar.topscores.repository.interfaces.CacheRepository;
import com.shankar.topscores.utils.FileReaderUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class WeeklyRepository extends BaseRedisRepository implements CacheRepository {
    @Value("${redis.weeklySet}")
    private String weeklySet;


    @Override
    public void saveEntry(GameEvent gameEvent) {
        saveEntry(gameEvent, FileReaderUtil.getWeekOfTheYear() +weeklySet);
    }

    @Override
    public List<GameEvent> getEntries(Integer n) {
        return getEntries(n,FileReaderUtil.getWeekOfTheYear()+weeklySet);
    }

    @Override
    public Long getTotalCount(){
        return getTotalCount(FileReaderUtil.getWeekOfTheYear()+weeklySet);
    }

    @Override
    public void deleteEntries(Long toRemove){
        deleteEntries(toRemove,FileReaderUtil.getWeekOfTheYear()+weeklySet);
    }
}
