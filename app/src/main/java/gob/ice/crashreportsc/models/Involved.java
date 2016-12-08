package gob.ice.crashreportsc.models;

/**
 * Created by jcsalguero on 08/12/2016.
 */

public class Involved {
    private String name;
    private Boolean sw;

    public Involved(String name, Boolean sw) {
        this.name = name;
        this.sw = sw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSw() {
        return sw;
    }

    public void setSw(Boolean sw) {
        this.sw = sw;
    }
}
