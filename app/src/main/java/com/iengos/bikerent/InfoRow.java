package com.iengos.bikerent;

/**
 * Created by Dave on 12/07/2016.
 */
public class InfoRow {
    public String Date;
    public String Status;
    public String Number;

    public void setDate(String date) {
        Date = date;
    }

    public InfoRow(){
        super();
    }

    public InfoRow(String Date, String Status, String Number) {
        super();
        this.Date = Date;
        this.Status = Status;
        this.Number = Number;
    }
}