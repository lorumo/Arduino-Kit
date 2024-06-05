import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class DatabaseListImpl implements Database {
    private List<User> database;
    private List<Auto> autos;

    public DatabaseListImpl() {
        database = new ArrayList<>();
        autos = new ArrayList<>();
    }

    @Override
    public List<User> getCustomers() {
        return database;
    }

    @Override
    public void addCustomer(User user) {
        database.add(user);
    }

    @Override
    public void addAuto(Auto auto) {
        autos.add(auto);
    }

    @Override
    public float getPrice() {
        float totalPrice = 0;
        for (User user : database) {
            if (user instanceof Payer) {
                totalPrice += ((Payer) user).getPrice();
            }
        }
        return totalPrice;
    }

    @Override
    public List<Auto> getAvailableCars() {
        List<Auto> availableCars = new ArrayList<>();
        for (Auto auto : autos) {
            if (!auto.isReserved()) {
                availableCars.add(auto);
            }
        }
        return availableCars;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DatabaseListImpl that = (DatabaseListImpl) o;
        return Objects.equals(database, that.database);
    }

    @Override
    public int hashCode() {
        return Objects.hash(database);
    }
}

