import java.util.Objects;

class Auto implements Payer {
    private int id;
    private String brand;
    private String model;
    private int year;
    private float price;
    private Reservation reservation;

    public Auto(AutoBuilder builder) {
        this.brand = builder.getBrand();
        this.model = builder.getModel();
        this.year = builder.getYear();
        this.price = builder.getPrice();
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

    @Override
    public float getPrice() {
        return price;
    }

    public boolean isReserved() {
        return reservation != null;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auto auto = (Auto) o;
        return id == auto.id && year == auto.year && Float.compare(price, auto.price) == 0 && Objects.equals(brand, auto.brand) && Objects.equals(model, auto.model) && Objects.equals(reservation, auto.reservation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, model, year, price, reservation);
    }
}