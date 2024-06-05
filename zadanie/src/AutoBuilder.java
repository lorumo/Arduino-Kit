class AutoBuilder {
     private String brand;
     private String model;
     private int year;
     private float price;

    public AutoBuilder setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public AutoBuilder setModel(String model) {
        this.model = model;
        return this;
    }

    public AutoBuilder setYear(int year) {
        this.year = year;
        return this;
    }

    public AutoBuilder setPrice(float price) {
        this.price = price;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public float getPrice() {
        return price;
    }

    public Auto buildAuto() {
        return new Auto(this);
    }
}