package gob.ice.crashreportsc.models;

import java.util.List;

/**
 * Created by mvaldez on 15/12/2016.
 */

public class Accident {
    private String severidad;
    private List<Involved> listInvolved;
    private List<Risk> listRisk;
    private String clima;
    private int soat;
    private int privado;
    private int noFigurado;
    private String latitude;
    private String longitude;
    private String photoOne;
    private String photoTwo;
    private String photoThree;
    private String photoFour;
    private String registerDate;
    private String registerHour;
    private String hourAccident;

    public Accident(String severidad, List<Involved> listInvolved, List<Risk> listRisk, String clima, int soat, int privado, int noFigurado, String latitude, String longitude, String photoOne, String photoTwo, String photoThree, String photoFour, String registerDate, String registerHour, String hourAccident) {
        this.severidad = severidad;
        this.listInvolved = listInvolved;
        this.listRisk = listRisk;
        this.clima = clima;
        this.soat = soat;
        this.privado = privado;
        this.noFigurado = noFigurado;
        this.latitude = latitude;
        this.longitude = longitude;
        this.photoOne = photoOne;
        this.photoTwo = photoTwo;
        this.photoThree = photoThree;
        this.photoFour = photoFour;
        this.registerDate = registerDate;
        this.registerHour = registerHour;
        this.hourAccident = hourAccident;
    }

    public String getSeveridad() {
        return severidad;
    }

    public void setSeveridad(String severidad) {
        this.severidad = severidad;
    }

    public List<Involved> getListInvolved() {
        return listInvolved;
    }

    public void setListInvolved(List<Involved> listInvolved) {
        this.listInvolved = listInvolved;
    }

    public List<Risk> getListRisk() {
        return listRisk;
    }

    public void setListRisk(List<Risk> listRisk) {
        this.listRisk = listRisk;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }

    public int getSoat() {
        return soat;
    }

    public void setSoat(int soat) {
        this.soat = soat;
    }

    public int getPrivado() {
        return privado;
    }

    public void setPrivado(int privado) {
        this.privado = privado;
    }

    public int getNoFigurado() {
        return noFigurado;
    }

    public void setNoFigurado(int noFigurado) {
        this.noFigurado = noFigurado;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPhotoOne() {
        return photoOne;
    }

    public void setPhotoOne(String photoOne) {
        this.photoOne = photoOne;
    }

    public String getPhotoTwo() {
        return photoTwo;
    }

    public void setPhotoTwo(String photoTwo) {
        this.photoTwo = photoTwo;
    }

    public String getPhotoThree() {
        return photoThree;
    }

    public void setPhotoThree(String photoThree) {
        this.photoThree = photoThree;
    }

    public String getPhotoFour() {
        return photoFour;
    }

    public void setPhotoFour(String photoFour) {
        this.photoFour = photoFour;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getRegisterHour() {
        return registerHour;
    }

    public void setRegisterHour(String registerHour) {
        this.registerHour = registerHour;
    }

    public String getHourAccident() {
        return hourAccident;
    }

    public void setHourAccident(String hourAccident) {
        this.hourAccident = hourAccident;
    }
}
