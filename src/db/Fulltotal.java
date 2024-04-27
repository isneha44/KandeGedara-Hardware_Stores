package db;

public class Fulltotal {

    private String total;

    public Fulltotal() {
    }

    public Fulltotal(String total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Fulltotall{" +
                "total='" + total + '\'' +
                '}';
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
