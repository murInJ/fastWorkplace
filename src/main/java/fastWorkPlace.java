
import IO.FileTK;
import Web.op;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.IOException;
import java.util.*;

/**
 * @title: fastWorkPlace
 * @Author MurInj
 * @Date: 2022/8/23 21:34
 * @Version 1.0
 */

public class fastWorkPlace {
    public static workSpace workspace;
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException, InterruptedException {
        if (!FileTK.check(".\\workSpace.json", false)) {
            FileTK.writeFile(".\\workSpace.json", JSON.toJSONString(new workSpace(), SerializerFeature.WriteMapNullValue));
            System.out.println("请配置workSpace.json");
            Thread.sleep(2000);
            System.exit(0);
        } else {
            String jsonStr = FileTK.readFile(".\\workSpace.json");
            workspace = workSpace.readWorkSpace(jsonStr);
            while (true) {
                String command = scanner.nextLine();
                String hk = workspace.getHk().getHotkey(command);
                if (hk != null) {
                    workspace.setCurrentWorkplace(hk);
                    if(execCommand(""))break;
                }
                else{
                    if(execCommand(command))break;
                }



                FileTK.writeFile(".\\workSpace.json", JSON.toJSONString(workspace, SerializerFeature.WriteMapNullValue));
            }
        }

    }

    public static Boolean execCommand(String command) {
        String[] cmds = command.split(" ");
        if (cmds.length == 1) {
            String key = cmds[0];
            if (key.equals("")) {
                String currentWorkplace = workspace.getCurrentWorkplace();
                if (workspace.getWorkPlaces().containsKey(currentWorkplace)) {
                    workPlace workplace = workspace.getWorkPlaces().get(currentWorkplace);
                    for (String software : workplace.softwares) {
                        FileTK.execFile(software);
                    }
                    for (String url : workplace.urls) {
                        op.openInBrowser(url);
                    }
                    return true;
                } else {
                    System.out.println("current workplace not set.");
                }
            }
            if (key.equals("quit")) {
                return true;
            } else {
                System.out.println("unknown command.");
            }
        } else if (cmds.length == 2) {
            String op = cmds[0];
            String arg = cmds[1];
            switch (op) {
                case "new" -> workspace.newWorkplace(arg);
                case "remove" -> workspace.removeWorkplace(arg);
                case "use" -> workspace.useWorkPlace(arg);
                case "add" -> workspace.addWorkPlace(workspace.getCurrentWorkplace(), arg);
                case "clear" -> workspace.getHk().clearHotkey(arg);
                default -> System.out.println("unknown command.");
            }
        } else if (cmds.length == 3) {
            String op = cmds[0];
            String arg1 = cmds[1];
            String arg2 = cmds[2];
            if (op.equals("set")) {
                if (workspace.getWorkPlaces().containsKey(arg2)) {
                    workspace.getHk().setHotkey(arg1, arg2);
                } else {
                    System.out.println("workplace not exist.");
                }
            } else {
                System.out.println("unknown command.");
            }
        } else {
            System.out.println("unknown command.");
        }
        return false;
    }

}

