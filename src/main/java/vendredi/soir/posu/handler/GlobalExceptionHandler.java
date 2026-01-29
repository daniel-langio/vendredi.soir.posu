package vendredi.soir.posu.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import vendredi.soir.posu.endpoint.rest.model.Exception;
import vendredi.soir.posu.model.exception.ApiException;
import vendredi.soir.posu.model.exception.BadRequestException;
import vendredi.soir.posu.model.exception.ConflictException;
import vendredi.soir.posu.model.exception.ForbiddenException;
import vendredi.soir.posu.model.exception.NotFoundException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<Exception> handleBadRequestException(BadRequestException ex) {
    return new ResponseEntity<>(toRest(ex, "BadRequestException"), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<Exception> handleNotFoundException(NotFoundException ex) {
    return new ResponseEntity<>(toRest(ex, "NotFoundException"), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(ConflictException.class)
  public ResponseEntity<Exception> handleConflictException(ConflictException ex) {
    return new ResponseEntity<>(toRest(ex, "ConflictException"), HttpStatus.CONFLICT);
  }

  @ExceptionHandler(ForbiddenException.class)
  public ResponseEntity<Exception> handleForbiddenException(ForbiddenException ex) {
    return new ResponseEntity<>(toRest(ex, "ForbiddenException"), HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<Exception> handleIllegalArgumentException(IllegalArgumentException ex) {
    return new ResponseEntity<>(toRest(ex, "BadRequestException"), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(java.lang.Exception.class)
  public ResponseEntity<Exception> handleDefaultException(java.lang.Exception ex) {
    log.error("Internal Server Error", ex);
    Exception restException =
        Exception.builder().type("InternalServerError").message("An internal error occurred").build();
    return new ResponseEntity<>(restException, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  private Exception toRest(ApiException ex, String type) {
    return Exception.builder().type(type).message(ex.getMessage()).build();
  }

  private Exception toRest(IllegalArgumentException ex, String type) {
    return Exception.builder().type(type).message(ex.getMessage()).build();
  }
}
