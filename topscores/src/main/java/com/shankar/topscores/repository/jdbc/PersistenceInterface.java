package com.shankar.topscores.repository.jdbc;

import com.shankar.topscores.domain.jpa.GameEvent;

public interface PersistenceInterface {
    void saveEvent(GameEvent gameEvent);
}
