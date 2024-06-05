import java.util.List;
import java.util.Objects;

class DatabaseListProxy implements Database {
    private static DatabaseListProxy instance;
    private Database database;

    private DatabaseListProxy() {
        database = new DatabaseListImpl();
    }

    public static DatabaseListProxy getInstance() {
        if (instance == null) {
            instance = new DatabaseListProxy();
        }
        return instance;
    }

    @Override
    public List<User> getCustomers() {
        return database.getCustomers();
    }

    @Override
    public void addCustomer(User user) {
        for (User dbUser : database.getCustomers()) {
            if (dbUser.equals(user)) {
                throw new DatabaseException("User " + user + " is already exists");
            }
        }
        database.addCustomer(user);
    }

    @Override
    public void addAuto(Auto auto) {
        if (database instanceof DatabaseListImpl) {
            ((DatabaseListImpl) database).addAuto(auto);
        }
    }

    @Override
    public float getPrice() {
        return database.getPrice();
    }

    @Override
    public List<Auto> getAvailableCars() {
        return database.getAvailableCars();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DatabaseListProxy that = (DatabaseListProxy) o;
        return Objects.equals(database, that.database);
    }

    @Override
    public int hashCode() {
        return Objects.hash(database);
    }
}
