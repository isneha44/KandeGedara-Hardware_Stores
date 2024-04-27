package Model;

import java.time.LocalDateTime;

public class Delivery {
     private String dId;
     private String veNo;
     private String address;
     private String contact;
     private double deliveyFee;
     private String date;

    public Delivery(String text, String txtveNoText, String txtaddressText, String txtcontactText, String txtdeliveyFreeText, String txtdateText) {
    }


    public Delivery(String dId, String veNo, String address, String contact, double deliveyFee, String date) {
        this.dId = dId;
        this.veNo = veNo;
        this.address = address;
        this.contact = contact;
        this.deliveyFee = deliveyFee;
        this.date = date;
    }

    public String getDId() {
        return dId;
    }

    public void setdId(String dId) {
        this.dId= dId;
    }

    public String getVeNo() {
        return veNo;
    }

    public void setVeNo(String veNo) {
        this.veNo = veNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public double getDeliveyFee() {
        return deliveyFee;
    }

    public void setDeliveyFee(double deliveyFee) {
        this.deliveyFee = deliveyFee;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "dId='" +dId + '\'' +
                ", veNo='" + veNo + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", deliveyFee=" + deliveyFee +
                ", date='" + date + '\'' +
                '}';
    }
}
