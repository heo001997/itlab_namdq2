package topica.itlab.namdq2.generics.models;

public class Book {
    private String name;
    private Double price;

    public Book() {
    }

    public Book(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
