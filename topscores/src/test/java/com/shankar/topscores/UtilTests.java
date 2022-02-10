package com.shankar.topscores;

import com.shankar.topscores.domain.jpa.GameEvent;
import com.shankar.topscores.utils.FileReaderUtil;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

@ActiveProfiles(profiles = "test")
public class UtilTests {

    @Test
    public void gameEventCheck1(){
        String s = "abc,10";
        List<GameEvent>output = new ArrayList<>();
        FileReaderUtil.checkIfConvertibleAndAdd(s,output);
        assert output.size()==1;
    }

    @Test
    public void gameEventCheck2(){
        String s = "abc,-10";
        List<GameEvent>output = new ArrayList<>();
        FileReaderUtil.checkIfConvertibleAndAdd(s,output);
        assert output.size()==0;
    }

    @Test
    public void gameEventCheck3(){
        String s = "abc,100000000000000022";
        List<GameEvent>output = new ArrayList<>();
        FileReaderUtil.checkIfConvertibleAndAdd(s,output);
        assert output.size()==0;
    }

    @Test
    public void gameEventCheck4(){
        String s = "abc,10.22";
        List<GameEvent>output = new ArrayList<>();
        FileReaderUtil.checkIfConvertibleAndAdd(s,output);
        assert output.size()==0;
    }

}
