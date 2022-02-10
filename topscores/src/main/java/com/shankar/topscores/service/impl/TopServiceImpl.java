package com.shankar.topscores.service.impl;

import com.shankar.topscores.domain.enums.TimePeriod;
import com.shankar.topscores.domain.jpa.GameEvent;
import com.shankar.topscores.repository.jdbc.PersistentRepository;
import com.shankar.topscores.service.TopScoreService;
import com.shankar.topscores.utils.FactoryPatternUtil;
import com.shankar.topscores.utils.FileReaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopServiceImpl implements TopScoreService {
    private static final Logger logger = LoggerFactory.getLogger(TopServiceImpl.class);
    @Autowired
    private FactoryPatternUtil factoryPatternUtil;
    @Autowired
    private PersistentRepository persistentRepository;

    @Value("${topN.default}")
    private  Integer defaultN;

    @Value("${topN.limit}")
    private  Integer limitN;

    @Override
    public List<GameEvent> getTopScore(Integer topN){

        if(topN==null) {
            topN=defaultN;
        }
        if(topN>limitN){
            topN = limitN;
        }
        return factoryPatternUtil.getMethod(TimePeriod.ALL_TIME).getEntries(topN);
    }

    @Override
    public boolean storeScore(List<GameEvent> gameEvents){
        gameEvents.forEach(s -> storeScore(s));
        return true;
    }

    @Override
    public boolean storeScore(GameEvent gameEvent){
        if(FileReaderUtil.isValid(gameEvent)) {
            factoryPatternUtil.getMethod(TimePeriod.ALL_TIME).saveEntry(gameEvent);
            persistentRepository.saveEvent(gameEvent);
        }
        return true;
    }


}
