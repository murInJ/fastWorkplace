import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @title: hotkey
 * @Author MurInj
 * @Date: 2022/8/28 22:50
 * @Version 1.0
 */

public class hotkey {
    private Map<String,String> hot;
    private static final Set<String> ignore = new HashSet<>(){};

    public hotkey() {
        hot = new HashMap<>();
    }

    public void setHotkey(String key, String value){
        if(ignore.contains(key)){
            System.out.println("can`t set this hotkey, try another one.");
        }
        else{
            hot.put(key,value);
        }
    }

    public void clearHotkey(String key){
        hot.remove(key);
    }

    public String getHotkey(String key){
        return hot.get(key);
    }
}
