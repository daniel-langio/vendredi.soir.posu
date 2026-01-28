package vendredi.soir.posu.model.exception;

public class BadRequestException extends ApiException {
  public BadRequestException(String message) {
    super(message);
  }
}
