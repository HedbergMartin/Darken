package datapath;

import java.util.HashMap;
import java.util.Map;

public class Control {

    private Map<String, Integer> result = new HashMap();

    public void perform(int instruction){


    }

    public int getResult(String name){
        return result.get("name");
    }
}
