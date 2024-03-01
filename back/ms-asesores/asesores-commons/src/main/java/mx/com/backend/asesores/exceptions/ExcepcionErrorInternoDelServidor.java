package mx.com.backend.asesores.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ExcepcionErrorInternoDelServidor extends RuntimeException {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new excepcion error interno del servidor.
   *
   * @param message the message
   */
  public ExcepcionErrorInternoDelServidor(String message) {
    super(message);
  }
}