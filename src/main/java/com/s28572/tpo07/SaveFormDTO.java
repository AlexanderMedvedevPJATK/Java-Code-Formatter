package com.s28572.tpo07;

public class SaveFormDTO {

    private String formattedCode;
    private String id;
    private int days;
    private int hours;
    private int minutes;
    private int seconds;

    public SaveFormDTO() {
    }

    public SaveFormDTO(String formattedCode, String id, int days, int hours, int minutes, int seconds) {
        this.formattedCode = formattedCode;
        this.id = id;
        this.days = days;
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public String getFormattedCode() {
        return formattedCode;
    }

    public void setFormattedCode(String formattedCode) {
        this.formattedCode = formattedCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    @Override
    public String toString() {
        return "SaveFormDTO{" +
                "formattedCode='" + formattedCode + '\'' +
                ", days=" + days +
                ", hours=" + hours +
                ", minutes=" + minutes +
                ", seconds=" + seconds +
                '}';
    }
}
