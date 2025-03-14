package content.handler;

import content.handler.exceptions.BadRequestException;
import content.handler.exceptions.ForbiddenException;
import content.handler.exceptions.NotFoundException;
import content.handler.exceptions.UnauthorizedException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.PrintWriter;
import java.io.StringWriter;

import static java.time.LocalDateTime.now;


@RestControllerAdvice
public class RestControllerAdvisor {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDto handleNotFoundException(NotFoundException e, HttpServletRequest request) {
        return buildErrorResponse(e, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleValidationException(BadRequestException e, HttpServletRequest request) {
        return buildErrorResponse(e, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponseDto handleValidationException(UnauthorizedException e, HttpServletRequest request) {
        return buildErrorResponse(e, HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponseDto handleValidationException(ForbiddenException e, HttpServletRequest request) {
        return buildErrorResponse(e, HttpStatus.FORBIDDEN, request);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDto handleInternalException(RuntimeException e, HttpServletRequest request) {
        return buildErrorResponse(e, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorResponseDto handleMethodNotAllowedException(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        return buildErrorResponse(e, HttpStatus.METHOD_NOT_ALLOWED, request);
    }



    private ErrorResponseDto buildErrorResponse(Exception e, HttpStatus status, HttpServletRequest request) {
        e.printStackTrace();
        return ErrorResponseDto.builder()
                .message(e.getMessage())
                .status(status.value())
                .error(status.getReasonPhrase())
                .stackTrace(getStackTrace(e))
                .timestamp(now())
                .path(request.getRequestURI())
                .build();
    }

    String getStackTrace(final Throwable throwable) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString();
    }
}
