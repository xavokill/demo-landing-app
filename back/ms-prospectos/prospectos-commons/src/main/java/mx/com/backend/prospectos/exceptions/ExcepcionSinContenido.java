package mx.com.backend.prospectos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class ExcepcionSinContenido extends RuntimeException {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new excepcion sin contenido.
   *
   * @param message the message
   */
  public ExcepcionSinContenido(String message) {
    super(message);
  }
}