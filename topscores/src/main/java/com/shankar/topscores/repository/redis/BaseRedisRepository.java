package com.shankar.topscores.repository.redis;


import com.shankar.topscores.domain.jpa.GameEvent;
import io.lettuce.core.ZAddArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public abstract class BaseRedisRepository {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private LettuceConnectionFactory lettuceConnectionFactory;
    private static RedisZSetCommands.ZAddArgs zAddArgs = RedisZSetCommands.ZAddArgs.empty().gt();

    protected ZSetOperations<String, String> getOptsForZSet(){
        return redisTemplate.opsForZSet();
    }

    protected void saveEntry(GameEvent gameEvent,String setName){
        lettuceConnectionFactory.getConnection().zAdd(setName.getBytes(),gameEvent.getScore(),gameEvent.getName().getBytes(),zAddArgs);
    }

    protected List<GameEvent> getEntries(Integer n,String setName) {
        List<GameEvent>results = new ArrayList<>();
        getOptsForZSet().reverseRangeWithScores(setName,0L,n-1).forEach(s ->{
            GameEvent gameEvent = new GameEvent();
            gameEvent.setName(String.valueOf(s.getValue()));
            gameEvent.setScore(s.getScore().intValue());
            results.add(gameEvent);
        });
        return results;
    }

    protected Long getTotalCount(String setName){
        Long result = getOptsForZSet().count(setName,Double.MIN_VALUE,Double.MAX_VALUE);
        return result;
    }

    protected void deleteEntries(Long toRemove,String setName){
        getOptsForZSet().removeRange(setName,0,toRemove);
    }

}
