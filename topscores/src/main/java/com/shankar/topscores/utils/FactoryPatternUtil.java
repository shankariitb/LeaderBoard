package com.shankar.topscores.utils;

import com.shankar.topscores.domain.enums.TimePeriod;
import com.shankar.topscores.repository.interfaces.CacheRepository;
import com.shankar.topscores.repository.redis.AllTimeRepository;
import com.shankar.topscores.repository.redis.WeeklyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FactoryPatternUtil {
    @Autowired
    private AllTimeRepository allTimeRepository;
    @Autowired
    private WeeklyRepository weeklyRepository;

    public CacheRepository getMethod(TimePeriod timePeriod){
        switch (timePeriod){
            case WEEK:
                return weeklyRepository;
            default:
                return allTimeRepository;
        }
    }
}
