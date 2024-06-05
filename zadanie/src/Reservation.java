import java.util.Date;
import java.util.Objects;

class Reservation {
    private int id;
    private Auto auto;
    private Customer customer;
    private Date reservationDate;
    private boolean isPaid;

    public Reservation(Auto auto, Customer customer, Date reservationDate, boolean isPaid) {
        this.auto = auto;
        this.customer = customer;
        this.reservationDate = reservationDate;
        this.isPaid = isPaid;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return id == that.id && isPaid == that.isPaid && Objects.equals(auto, that.auto) && Objects.equals(customer, that.customer) && Objects.equals(reservationDate, that.reservationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, auto, customer, reservationDate, isPaid);
    }
}
