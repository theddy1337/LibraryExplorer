package Exceptions;

public class InvalidNumberOfThreadsException extends Exception {

    @Override
    public String getMessage() {
        return "Invalid number of threads!";
    }

}
