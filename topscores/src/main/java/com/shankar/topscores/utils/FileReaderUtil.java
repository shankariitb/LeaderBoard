package com.shankar.topscores.utils;

import com.shankar.topscores.domain.jpa.GameEvent;
import org.springframework.util.ObjectUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FileReaderUtil {

    static final BigInteger maxInt = BigInteger.valueOf(Integer.MAX_VALUE);
    static final String filePath = "/home/pallenavya/topscores/";


    public static List<GameEvent> readFile(String filename) {
        List<GameEvent>gameEvents = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath+filename));
            String line = br.readLine();
            while (line != null) {
                checkIfConvertibleAndAdd(line,gameEvents);
                line = br.readLine();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return gameEvents;
    }

    public static void checkIfConvertibleAndAdd(String line,List<GameEvent> gameEvents){
        if(line != null){
            String[] sh = line.split(",");
            if(sh.length==2){
                if(isInteger(sh[1],10)){
                    BigInteger curScore = new BigInteger(sh[1]);
                    if(curScore.compareTo(maxInt)<0){
                        GameEvent gameEvent = new GameEvent();
                        gameEvent.setName(sh[0]);
                        gameEvent.setScore(Integer.parseInt(sh[1]));
                        gameEvents.add(gameEvent);
                    }
                }
            }
        }
    }

    public static boolean isInteger(String s, int radix) {
        if(s.isEmpty()) return false;
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                return false;
            }
            if(Character.digit(s.charAt(i),radix) < 0) return false;
        }
        return true;
    }

    public static boolean isValid(GameEvent gameEvent){
        return !ObjectUtils.isEmpty(gameEvent) && !ObjectUtils.isEmpty(gameEvent.getName()) && !ObjectUtils.isEmpty(gameEvent.getScore());
    }

    public static int getWeekOfTheYear(){
        LocalDate localDate = LocalDate.now();
        TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        return localDate.get(woy);
    }
}
