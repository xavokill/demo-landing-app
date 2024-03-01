package mx.com.backend.prospectos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExcepcionPeticionIncorrecta extends RuntimeException {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new excepcion peticion incorrecta.
   *
   * @param message the message
   */
  public ExcepcionPeticionIncorrecta(String message) {
    super(message);
  }
}