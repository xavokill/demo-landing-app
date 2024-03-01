package mx.com.backend.asesores.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class ExcepcionNoAutorizado extends RuntimeException {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new excepcion no autorizado.
   *
   * @param message the message
   */
  public ExcepcionNoAutorizado(String message) {
    super(message);
  }
}