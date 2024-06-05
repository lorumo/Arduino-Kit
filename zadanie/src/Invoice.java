import java.util.List;
import java.util.Objects;

class Invoice implements Payer {
    private List<Auto> cars;
    private String customerName;

    public Invoice(List<Auto> cars) {
        this.cars = cars;
    }


    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


    public String getCustomerName() {
        return customerName;
    }


    @Override
    public float getPrice() {
        float totalPrice = 0;
        for (Auto car : cars) {
            totalPrice += car.getPrice();
        }
        return totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(cars, invoice.cars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cars);
    }
}
