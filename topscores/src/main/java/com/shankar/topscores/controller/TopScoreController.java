package com.shankar.topscores.controller;


import com.shankar.topscores.config.BaseRequestHelper;
import com.shankar.topscores.domain.jpa.GameEvent;
import com.shankar.topscores.domain.network.request.GameEventRequest;
import com.shankar.topscores.service.TopScoreService;
import com.shankar.topscores.utils.FileReaderUtil;
import com.sun.deploy.net.HttpResponse;
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
    private BaseRequestHelper baseRequestHelper;
    @Autowired
    private TopScoreService topScoreService;

    @RequestMapping(value = {"/getTopScores","getTopScores/{N}"},method = {RequestMethod.GET})
    private List<GameEvent> getTopScores(@PathVariable(required = false) Integer topN, HttpServletRequest httpRequest){
        return topScoreService.getTopScore(topN);
    }

    @RequestMapping(value = "/storeScore/{fileName}",method = {RequestMethod.GET})
    private int storeScore(@PathVariable String fileName, HttpServletRequest httpRequest){
        List<GameEvent> gameEvents = FileReaderUtil.readFile(fileName);
        topScoreService.storeScore(gameEvents);
        return 1;
    }

}
