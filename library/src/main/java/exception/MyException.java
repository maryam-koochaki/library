package exception;

public class MyException extends RuntimeException{

    public static int INVALID_COUNT_BOOK = 1;
    public static int BOOK_IS_NOT_AVAILABLE = 2;
    public static int OBJECT_NOT_FOUND = 3;

    protected int code;
    public MyException() {
    }
    public MyException(String message) {
        super(message);
    }
    public MyException(int code,String message){
        super(message);
        this.code=code;
    }
}
