package mx.com.backend.prospectos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ExcepcionProhibido extends RuntimeException {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new excepcion prohibido.
   *
   * @param message the message
   */
  public ExcepcionProhibido(String message) {
    super(message);
  }
}