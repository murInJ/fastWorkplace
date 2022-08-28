import com.alibaba.fastjson.JSON;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static Web.url.isURL;

/**
 * @title: workSpace
 * @Author MurInj
 * @Date: 2022/8/28 21:32
 * @Version 1.0
 */

public class workSpace {
    private String currentWorkplace;
    private Map<String,workPlace> workPlaces;
    private hotkey hk;

    public workSpace() {
        currentWorkplace = "None";
        workPlaces = new HashMap<>();
        hk = new hotkey();
    }

    public String getCurrentWorkplace() {
        return currentWorkplace;
    }

    public void setCurrentWorkplace(String currentWorkplace) {
        this.currentWorkplace = currentWorkplace;
    }

    public Map<String, workPlace> getWorkPlaces() {
        return workPlaces;
    }

    public void setWorkPlaces(Map<String, workPlace> workPlaces) {
        this.workPlaces = workPlaces;
    }

    public static workSpace readWorkSpace(String jsonStr){
        return JSON.parseObject(jsonStr,workSpace.class);
    }

    public void newWorkplace(String workplaceName){
        if(workPlaces.containsKey(workplaceName)){
            System.out.println("workplace have been existed.");
        }
        else{
            workPlaces.put(workplaceName,new workPlace(workplaceName));
        }
    }

    public void removeWorkplace(String workplaceName){
        if(workPlaces.containsKey(workplaceName)){
            workPlaces.remove(workplaceName);
        }
        else{
            System.out.println("workplace not exist.");
        }
    }

    public hotkey getHk() {
        return hk;
    }

    public void setHk(hotkey hk) {
        this.hk = hk;
    }

    public void useWorkPlace(String workplaceName){
        if(workPlaces.containsKey(workplaceName)){
            currentWorkplace = workplaceName;
        }
        else{
            System.out.println("workplace not exist.");
        }
    }

    public void addWorkPlace(String workplaceName,String str){
        if(isURL(str)){
            workPlaces.get(workplaceName).urls.add(str);
        }
        else if(new File(str).exists()){
            workPlaces.get(workplaceName).softwares.add(str);
        }
    }
}

