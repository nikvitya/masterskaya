package ru.yandex.exception.handler;


import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.yandex.exception.IncorrectParameterException;
import ru.yandex.exception.UserNotFoundException;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {
    @ExceptionHandler({
            NumberFormatException.class,
            MethodArgumentNotValidException.class,
            MethodArgumentTypeMismatchException.class,
            IllegalArgumentException.class,
            MissingServletRequestParameterException.class,
            IllegalStateException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(Exception e) {
        log.warn("{}", e.getMessage());
        return new ErrorResponse("Ошибка валидации входных параметров",
                e.getLocalizedMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleIncomingParametersNotValidException(ConstraintViolationException e) {
        log.warn("{}", e.getMessage());
        return new ErrorResponse("Ошибка валидации входных параметров Constraint",
                e.getLocalizedMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationExceptionException(IncorrectParameterException e) {
        log.warn("{}", e.getMessage());
        return new ErrorResponse("Ошибка валидации входных параметров.",
                e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleValidationExceptionException(UserNotFoundException e) {
        log.warn("{}", e.getMessage());
        return new ErrorResponse("Ошибка валидации пользователя", e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(Exception e) {
        log.warn("{}", e.getMessage());
        return new ErrorResponse("Произошла непредвиденная ошибка.", e.getMessage());
    }
}
