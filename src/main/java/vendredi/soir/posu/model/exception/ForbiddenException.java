package vendredi.soir.posu.model.exception;

public class ForbiddenException extends ApiException {
  public ForbiddenException(String message) {
    super(message);
  }
}
