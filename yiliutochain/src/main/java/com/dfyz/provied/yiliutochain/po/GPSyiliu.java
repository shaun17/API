package com.dfyz.provied.yiliutochain.po;

public class GPSyiliu {
    private String PlateNumber;

    public String getPlateNumber() {
        return PlateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        PlateNumber = plateNumber;
    }

    private String GpsTime;
    private String Wid;
    private double Lon;
    private double Lat;
    private int Speed;
    private int Direction;
    private double Odometer;

    public String getGpsTime() {
        return GpsTime;
    }

    public void setGpsTime(String gpsTime) {
        GpsTime = gpsTime;
    }

    public String getWid() {
        return Wid;
    }

    public void setWid(String wid) {
        Wid = wid;
    }

    public double getLon() {
        return Lon;
    }

    public void setLon(double lon) {
        Lon = lon;
    }

    public double getLat() {
        return Lat;
    }

    public void setLat(double lat) {
        Lat = lat;
    }

    public int getSpeed() {
        return Speed;
    }

    public void setSpeed(int speed) {
        Speed = speed;
    }

    public int getDirection() {
        return Direction;
    }

    public void setDirection(int direction) {
        Direction = direction;
    }

    public double getOdometer() {
        return Odometer;
    }

    public void setOdometer(double odometer) {
        Odometer = odometer;
    }
}
