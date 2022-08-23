
import IO.FileTK;
import Web.op;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @title: fastWorkPlace
 * @Author MurInj
 * @Date: 2022/8/23 21:34
 * @Version 1.0
 */

public class fastWorkPlace {

    public static void main(String[] args) throws IOException, InterruptedException {
        if (!FileTK.check(".\\config.json", false)) {
            FileTK.writeFile(".\\config.json", JSON.toJSONString(getExampleConfig()));
            System.out.println("请配置config.json");
            Thread.sleep(3000);
            System.exit(0);
        } else {
            String jsonStr = FileTK.readFile(".\\config.json");
            JSONObject config = JSON.parseObject(jsonStr);
            String currentWorkplace = config.getString("currentWorkplace");
            JSONArray workplaces = config.getJSONArray("workplaces");

            for (Object workplace : workplaces.toArray()) {
                JSONObject wp = (JSONObject) workplace;
                String wpName = wp.getString("name");
                if (wpName.equals(currentWorkplace)) {
                    JSONArray softwares = wp.getJSONArray("softwares");
                    JSONArray urls = wp.getJSONArray("urls");
                    for (int i = 0; i < softwares.size(); ++i) {
                        FileTK.execFile(softwares.getString(i));
                    }
                    for (int i = 0; i < urls.size(); ++i) {
                        op.openInBrowser(urls.getString(i));
                    }
                    System.exit(0);
                }
            }
        }
    }
    public static HashMap<String, Object> getExampleConfig(){
        String currentWorkplace = "example workPlace1_name";
        List<Object> workplaces = new ArrayList<>();

        List<String> urls = new ArrayList<>();
        urls.add("example url1");
        urls.add("example url2");

        List<String> softwares = new ArrayList<>();
        softwares.add("example softwarePath1");
        softwares.add("example softWarePath2");

        HashMap<String,Object> workPlace1 = new HashMap<>();
        workPlace1.put("urls",urls);
        workPlace1.put("softwares",softwares);
        workPlace1.put("name","example workPlace1_name");

        workplaces.add(workPlace1);

        HashMap<String,Object> exampleConfig = new HashMap<>();
        exampleConfig.put("currentWorkplace","example workPlace1_name") ;
        exampleConfig.put("workplaces",workplaces);

        return exampleConfig;

    }
}
