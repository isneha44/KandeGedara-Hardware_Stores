package Model;

import java.sql.Date;
import java.sql.Time;

public class PlaceOrder {

    private String oId;
    private String itemCode;
    private String name;
    private double unitPrice;
    private int qty;
    private Date date;
    private Time time;
    private double total;


    public PlaceOrder() {
    }

    public PlaceOrder(String oId, String itemCode, String name, double unitPrice, int qty, Date date, Time time, double total) {
        this.oId = oId;
        this.itemCode = itemCode;
        this.name = name;
        this.unitPrice = unitPrice;
        this.qty = qty;
        this.date = date;
        this.time = time;
        this.total = total;
    }


    public String getOId() {
        return oId;
    }

    public void setoId(String oId) {
        this.oId = oId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }


    @Override
    public String toString() {
        return "PlaceOrder{" +
                "oId='" + oId + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", name='" + name + '\'' +
                ", unitPrice=" + unitPrice +
                ", qty=" + qty +
                ", date=" + date +
                ", time=" + time +
                ", total=" + total +
                '}';
    }
}
