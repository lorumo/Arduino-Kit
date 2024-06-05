import java.util.List;

interface Database extends Payer {
    List<User> getCustomers();
    void addCustomer(User user);
    void addAuto(Auto auto);
    float getPrice();
    List<Auto> getAvailableCars();
}
