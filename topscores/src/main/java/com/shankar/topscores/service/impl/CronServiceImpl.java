package com.shankar.topscores.service.impl;

import com.shankar.topscores.domain.enums.TimePeriod;
import com.shankar.topscores.service.CronService;
import com.shankar.topscores.utils.FactoryPatternUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CronServiceImpl implements CronService {

    @Autowired
    private FactoryPatternUtil factoryPatternUtil;

    @Value("${redis.allTime.limit}")
    private Integer allTimeLimit;

    /**
     * Checks if the size got increased by more than allTimeLimit then it removes entries. Currently only AllTimeRepository can extend to All
     */
    @Override
    public void runCron() {
        Long totalCount = factoryPatternUtil.getMethod(TimePeriod.ALL_TIME).getTotalCount();
        if(totalCount>allTimeLimit){
            factoryPatternUtil.getMethod(TimePeriod.ALL_TIME).deleteEntries(totalCount-allTimeLimit-1);
        }
    }
}
