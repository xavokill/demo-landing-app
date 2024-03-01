package mx.com.backend.asesores.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ExcepcionReglaDeNegocio extends RuntimeException {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new excepcion regla de negocio.
   *
   * @param message the message
   */
  public ExcepcionReglaDeNegocio(String message) {
    super(message);
  }
}