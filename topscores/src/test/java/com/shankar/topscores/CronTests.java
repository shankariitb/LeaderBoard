package com.shankar.topscores;

import com.shankar.topscores.domain.jpa.GameEvent;
import com.shankar.topscores.repository.redis.AllTimeRepository;
import com.shankar.topscores.service.CronService;
import com.shankar.topscores.service.TopScoreService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "test")
@SpringBootTest
public class CronTests {
    @Autowired
    private CronService cronService;
    @Autowired
    private AllTimeRepository allTimeRepository;
    @Autowired
    private TopScoreService topScoreService;
    @Value("${redis.allTime.limit}")
    private Integer allTimeLimit;

    @Test
    public void cronTest(){
        assert allTimeLimit<=3;
        allTimeRepository.deleteEntries(Long.MAX_VALUE);
        List<GameEvent> gameEvents = new ArrayList<>();
        GameEvent gameEvent = new GameEvent("abcd",10);
        GameEvent gameEvent1 = new GameEvent("abce",30);
        GameEvent gameEvent2 = new GameEvent("abcf",20);
        GameEvent gameEvent3 = new GameEvent("abcp",5);
        gameEvents.add(gameEvent);
        gameEvents.add(gameEvent1);
        gameEvents.add(gameEvent2);
        gameEvents.add(gameEvent3);
        topScoreService.storeScore(gameEvents);
        cronService.runCron();
        assert allTimeRepository.getTotalCount()<=allTimeLimit;
    }
}
