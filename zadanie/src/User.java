import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

abstract class User implements Payer {
    private final String login;
    private final String password;
    private final String name;
    private final String lastName;
    private final String phoneNumber;
    private final List<Invoice> invoices;

    public User(String login, String password, String name, String lastName, String phoneNumber) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        invoices = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    // I didnt use it cause i dont see logic for it rn, but in the future when it will be a whole cool IS it will be used
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }


    @Override
    public float getPrice() {
        float totalPrice = 0;
        for (Invoice invoice : invoices) {
            totalPrice += invoice.getPrice();
        }
        return totalPrice;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(name, user.name) && Objects.equals(lastName, user.lastName) && Objects.equals(phoneNumber, user.phoneNumber) && Objects.equals(invoices, user.invoices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, name, lastName, phoneNumber, invoices);
    }
}