package com.example.ikoi;

public class Riwayat {
    private String day;
    private String time;
    private String status;

    public Riwayat(String day, String time, String status) {
        this.day = day;
        this.time = time;
        this.status = status;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
