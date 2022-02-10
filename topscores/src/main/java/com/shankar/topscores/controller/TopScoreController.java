package com.shankar.topscores.controller;


import com.shankar.topscores.domain.jpa.GameEvent;
import com.shankar.topscores.service.TopScoreService;
import com.shankar.topscores.utils.FileReaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class TopScoreController {

    @Autowired
    private TopScoreService topScoreService;

    /**
     *
     * @param topN Limit Scoreboard by this count,have default limiter for null and very high topN values
     * @param httpRequest Request Received
     * @return List of topN Players and their scores
     */
    @RequestMapping(value = {"/getTopScores", "getTopScores/{N}"}, method = {RequestMethod.GET})
    private List<GameEvent> getTopScores(@PathVariable(required = false) Integer topN, HttpServletRequest httpRequest) {
        return topScoreService.getTopScore(topN);
    }

    /**
     *
     * @param fileName file is read and valid scoreEvents are stored in DB.
     * @param httpRequest Request Received
     * @return Just an Int to tell Request was Proceeded Successfully
     */
    @RequestMapping(value = "/storeScore/{fileName}", method = {RequestMethod.GET})
    private int storeScore(@PathVariable String fileName, HttpServletRequest httpRequest) {
        List<GameEvent> gameEvents = FileReaderUtil.readFile(fileName);
        topScoreService.storeScore(gameEvents);
        return 1;
    }

}
