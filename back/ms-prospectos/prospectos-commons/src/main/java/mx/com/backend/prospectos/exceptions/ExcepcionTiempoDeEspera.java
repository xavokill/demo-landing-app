package mx.com.backend.prospectos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
public class ExcepcionTiempoDeEspera extends RuntimeException {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new excepcion tiempo de espera.
   *
   * @param message the message
   */
  public ExcepcionTiempoDeEspera(String message) {
    super(message);
  }
}