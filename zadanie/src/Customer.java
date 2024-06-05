import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

class Customer extends User {
    private List<Reservation> reservations;
    private List<Auto> cars;

    public Customer(String login, String password, String name, String lastName, String phoneNumber) {
        super(login, password, name, lastName, phoneNumber);
        reservations = new ArrayList<>();
        cars = new ArrayList<>();
    }

    public Reservation createReservation(Auto auto) {
        Reservation reservation = new Reservation(auto, this, new Date(), false);
        reservations.add(reservation);
        auto.setReservation(reservation);
        return reservation;
    }

    public void addCar(Auto auto) {
        cars.add(auto);
    }
    public String getCustomerName() {
        return getName() + " " + getLastName();
    }
    //

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(reservations, customer.reservations) && Objects.equals(cars, customer.cars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), reservations, cars);
    }
}