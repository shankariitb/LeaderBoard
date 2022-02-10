package com.shankar.topscores;

import com.shankar.topscores.domain.jpa.GameEvent;
import com.shankar.topscores.service.TopScoreService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class TopscoresApplication {

	public static void main(String[] args) {

		ApplicationContext applicationContext =  SpringApplication.run(TopscoresApplication.class, args);
		TopScoreService topScoreService = applicationContext.getBean(TopScoreService.class);
		List<GameEvent>gameEvents = new ArrayList<>();
		GameEvent gameEvent = new GameEvent();
		gameEvent.setScore(10);
		gameEvent.setName("abc");
		gameEvents.add(gameEvent);
		GameEvent gameEvent1 = new GameEvent();
		gameEvent1.setScore(20);
		gameEvent1.setName("abcd");
		gameEvents.add(gameEvent1);
		GameEvent gameEvent2 = new GameEvent();
		gameEvent2.setScore(40);
		gameEvent2.setName("abcde");
		gameEvents.add(gameEvent2);
		GameEvent gameEvent3 = new GameEvent();
		gameEvent3.setScore(10);
		gameEvent3.setName("abcde");
		gameEvents.add(gameEvent3);
		topScoreService.storeScore(gameEvents);
	}

}
