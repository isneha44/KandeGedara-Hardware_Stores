package Model;

public class supplierDetails {

private String sId;
private String i_Id;
private double payment;
private String paymentType;
private String status;
private String date;
private String time;


    public supplierDetails() {
    }


    public supplierDetails(String sId, String i_Id, double payment, String paymentType, String status, String date, String time) {
        this.sId = sId;
        this.i_Id = i_Id;
        this.payment = payment;
        this.paymentType = paymentType;
        this.status = status;
        this.date = date;
        this.time = time;
    }

    public String getSId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getI_Id() {
        return i_Id;
    }

    public void setI_Id(String i_Id) {
        this.i_Id = i_Id;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "SupplierDetails{" +
                "sId='" + sId + '\'' +
                ", i_Id='" + i_Id + '\'' +
                ", payment='" + payment + '\'' +
                ", paymentType='" + paymentType + '\'' +
                ", status='" + status + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
