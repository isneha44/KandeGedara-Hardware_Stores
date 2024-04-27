package Model;

import java.sql.Time;
import java.util.Date;


public class Vehicle {

    private String veNo;
    private String driverName;
    private Date date;
    private Time inTime;
    private String outTime;

    public Vehicle() {
    }

    public Vehicle(String veNo, String driverName, Date date, Time inTime, String outTime) {
        this.veNo = veNo;
        this.driverName = driverName;
        this.date = date;
        this.inTime = inTime;
        this.outTime = outTime;
    }

    public String getVeNo() {
        return veNo;
    }

    public void setVeNo(String veNo) {
        this.veNo = veNo;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getInTime() {
        return inTime;
    }

    public void setInTime(Time inTime) {
        this.inTime = inTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "veNo='" + veNo + '\'' +
                ", driverName='" + driverName + '\'' +
                ", date=" + date +
                ", inTime=" + inTime +
                ", outTime='" + outTime + '\'' +
                '}';
    }
}
