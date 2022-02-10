package com.shankar.topscores.repository.interfaces;

import com.shankar.topscores.domain.jpa.GameEvent;

import java.util.List;

public interface CacheRepository {
    void saveEntry(GameEvent gameEvent);
    List<GameEvent> getEntries(Integer n);
    Long getTotalCount();
    void deleteEntries(Long toRemove);
}
