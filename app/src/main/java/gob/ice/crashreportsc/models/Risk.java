package gob.ice.crashreportsc.models;

/**
 * Created by SALGUERO on 14/12/2016.
 */

public class Risk {
    private String name;
    private Boolean swSelected;

    public Risk() {
    }

    public Risk(String name, Boolean swSelected) {
        this.name = name;
        this.swSelected = swSelected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSwSelected() {
        return swSelected;
    }

    public void setSwSelected(Boolean swSelected) {
        this.swSelected = swSelected;
    }
}
