package Model;

public class Order {
    private String oId;
    private String cId;
    private String eId;
    private String dId;
    private String status;
    private String date;


    public Order() {
    }


    public Order(String oId, String cId, String eId, String dId, String status, String date) {
        this.oId = oId;
        this.cId = cId;
        this.eId = eId;
        this.dId = dId;
        this.status = status;
        this.date = date;
    }

    public String getOId() {
        return oId;
    }

    public void setoId(String oId) {
        this.oId = oId;
    }

    public String getCId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getEId() {
        return eId;
    }

    public void seteId(String eId) {
        this.eId = eId;
    }

    public String getDId() {
        return dId;
    }

    public void setdId(String dId) {
        this.dId = dId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Order{" +
                "oId='" + oId + '\'' +
                ", cId='" + cId + '\'' +
                ", eId='" + eId + '\'' +
                ", dId='" + dId + '\'' +
                ", status='" + status + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
