package gob.ice.crashreportsc.models;

public class Involved {
    private String name;
    private Boolean sw;
    private Boolean swGenero;

    public Involved(String name, Boolean sw, Boolean swGenero) {
        this.name = name;
        this.sw = sw;
        this.swGenero = swGenero;
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

    public Boolean getSwGenero() {
        return swGenero;
    }

    public void setSwGenero(Boolean swGenero) {
        this.swGenero = swGenero;
    }
}
