package model;

public class Facilities {
    boolean miniBar = true;
    boolean airConditioning = true;
    boolean television = true;
    boolean sonos = true;
    boolean bath = true;
    boolean toilet = true;
    boolean safe = true;
    boolean wifi = true;
    boolean phone = true;

    @Override
    public String toString() {
        return "Facilities{" +
                "miniBar=" + miniBar +
                ", airConditioning=" + airConditioning +
                ", television=" + television +
                ", sonos=" + sonos +
                ", bath=" + bath +
                ", toilet=" + toilet +
                ", safe=" + safe +
                ", wifi=" + wifi +
                ", phone=" + phone +
                '}';
    }

    public boolean hasMiniBar() {
        return miniBar;
    }

    public void setMiniBar(boolean miniBar) {
        this.miniBar = miniBar;
    }

    public boolean hasAirConditioning() {
        return airConditioning;
    }

    public void setAirConditioning(boolean airConditioning) {
        this.airConditioning = airConditioning;
    }

    public boolean hasTelevision() {
        return television;
    }

    public void setTelevision(boolean television) {
        this.television = television;
    }

    public boolean hasSonos() {
        return sonos;
    }

    public void setSonos(boolean sonos) {
        this.sonos = sonos;
    }

    public boolean hasBath() {
        return bath;
    }

    public void setBath(boolean bath) {
        this.bath = bath;
    }

    public boolean hasToilet() {
        return toilet;
    }

    public void setToilet(boolean toilet) {
        this.toilet = toilet;
    }

    public boolean hasSafe() {
        return safe;
    }

    public void setSafe(boolean safe) {
        this.safe = safe;
    }

    public boolean hasWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean hasPhone() {
        return phone;
    }

    public void setPhone(boolean phone) {
        this.phone = phone;
    }
}
