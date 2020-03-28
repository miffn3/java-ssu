package tasks.model;

public class Product {
    private String name;
    private double price;
    private int yearOfProduction;

    public Product() {
    }

    public Product(String name, int price, int yearOfProduction) {
        this.name = name;
        this.price = price;
        this.yearOfProduction = yearOfProduction;
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

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public int yearsToProduction(int currentYear) {
        return currentYear - this.yearOfProduction;
    }

    public void changePrice(int currentYear) {
        if (yearsToProduction(currentYear) == 0) {
            this.setPrice(this.price * 1.2d);
        }
    }

    @Override
    public String toString() {
        return "Models.Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", yearOfProduction=" + yearOfProduction +
                '}';
    }
}
