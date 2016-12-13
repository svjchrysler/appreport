package gob.ice.crashreportsc.models;

public class Involved {
    private String name;
    private Boolean sw;
    private Boolean swGenero;
    private int Count;

    public Involved() {}

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

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }
}
