package Model;

import java.sql.Date;
import java.sql.Time;

public class Purchase {
    private String oId;
    private String itemCode;
    private Integer qty;
    private Integer qtyOnHand;
    private Date date;
    private Time time;

    public Purchase() {
    }

    public Purchase(String oId, String itemCode, Integer qty, Integer qtyOnHand, Date date, Time time) {
        this.oId = oId;
        this.itemCode = itemCode;
        this.qty = qty;
        this.qtyOnHand = qtyOnHand;
        this.date = date;
        this.time = time;
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

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(Integer qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
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

    @Override
    public String toString() {
        return "OrderDetails{" +
                "oId='" + oId + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", qty=" + qty +
                ", qtyOnHand=" + qtyOnHand +
                ", date=" + date +
                ", time=" + time +
                '}';
    }
}
