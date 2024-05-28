package MVC.Utility;

public class DbException extends Throwable {
    /**
     *
     * @param message
     * super keyword fetches the constructer of the superclass and then passes a message
     * that should be retrieved by getMessage() inherited via throwable
     */
    public DbException(String message) {
        super(message);
    }


}
