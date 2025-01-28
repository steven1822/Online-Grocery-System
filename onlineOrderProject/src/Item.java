import java.io.Serializable;

public class Item implements Serializable {


    private String name;
    private double price;
    private int stock;
    private double weight;


    

    public Item(String name, double price, int stock, double weight) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.weight = weight;
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
    public void setPrice(float price) {
        this.price = price;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }


    @Override
    public String toString() {
        return "item [name=" + name + ", price=" + price + ", stock=" + stock + ", weight=" + weight + "]";
    }

    

    
}
