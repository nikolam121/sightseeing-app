package hr.tis.academy.sightseeingapp.repository.exception;

public class NoAttractionFoundException extends RuntimeException {
    public NoAttractionFoundException(String message) {
        super(message);
    }
    public NoAttractionFoundException(Exception e) {super(e);}
}
