package com.shankar.topscores.repository.interfaces;

import com.shankar.topscores.domain.jpa.GameEvent;

public interface PersistenceInterface {
    void saveEvent(GameEvent gameEvent);
}
