package mx.com.backend.asesores.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExcepcionRecursoNoEncontrado extends RuntimeException {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new excepcion recurso no encontrado.
   *
   * @param message the message
   */
  public ExcepcionRecursoNoEncontrado(String message) {
    super(message);
  }
}