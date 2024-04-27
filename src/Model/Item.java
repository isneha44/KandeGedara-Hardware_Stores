package Model;

public class Item {
    private String i_Code;
    private String name;
    private double price;

    public Item() {
    }

    public Item(String i_Code, String name, double price) {
        this.i_Code = i_Code;
        this.name = name;
        this.price = price;
    }

    public String getI_Code() {
        return i_Code;
    }

    public void setI_Code(String i_Code) {
        this.i_Code = i_Code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "i_Code='" + i_Code + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
