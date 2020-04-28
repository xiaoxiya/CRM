package chapter04;

/**
 * @author xiaoxiya
 * @date 2020/4/21 20:36
 * @describe
 */
public class UnderflowException extends Exception {
    public UnderflowException() {
        super();
    }

    public UnderflowException(String message) {
        super(message);
    }

    public UnderflowException(String message, Throwable cause) {
        super(message,cause);
    }
}
