package content.handler.exceptions;

public class BadRequestException extends ServiceException {

    public BadRequestException(String message) {
        super(message);
    }
}
