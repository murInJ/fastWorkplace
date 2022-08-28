import java.util.ArrayList;
import java.util.List;

public class workPlace {
    String name;
    List<String> softwares;
    List<String> urls;

    public workPlace() {
    }

    public workPlace(String name) {
        this.name = name;
        softwares = new ArrayList<>();
        urls = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSoftwares() {
        return softwares;
    }

    public void setSoftwares(List<String> softwares) {
        this.softwares = softwares;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

}
