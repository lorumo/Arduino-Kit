public class DatabaseException extends RuntimeException {
    // if the  same user is added to DB code throws this error
    public DatabaseException(String error) {
        super(error);
    }
}
