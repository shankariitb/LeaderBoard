package com.shankar.topscores.service;

import com.shankar.topscores.domain.jpa.GameEvent;
import com.shankar.topscores.domain.network.request.GameEventRequest;

import java.util.List;

public interface TopScoreService {

    List<GameEvent> getTopScore(Integer topN);

    boolean storeScore(List<GameEvent> gameEvents);

    boolean storeScore(GameEvent gameEvent);

}
