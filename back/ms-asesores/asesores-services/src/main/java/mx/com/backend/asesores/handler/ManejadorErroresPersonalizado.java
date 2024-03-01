package mx.com.backend.asesores.handler;

import java.util.HashMap;
import java.util.Map;
import mx.com.backend.asesores.dto.ErrorDTO;
import mx.com.backend.asesores.dto.RespuestaErrorPersonalizadaDTO;
import mx.com.backend.asesores.exceptions.ExcepcionErrorInternoDelServidor;
import mx.com.backend.asesores.exceptions.ExcepcionNoAutorizado;
import mx.com.backend.asesores.exceptions.ExcepcionPeticionIncorrecta;
import mx.com.backend.asesores.exceptions.ExcepcionProhibido;
import mx.com.backend.asesores.exceptions.ExcepcionRecursoNoEncontrado;
import mx.com.backend.asesores.exceptions.ExcepcionReglaDeNegocio;
import mx.com.backend.asesores.exceptions.ExcepcionSinContenido;
import mx.com.backend.asesores.exceptions.ExcepcionTiempoDeEspera;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * The Class ManejadorErroresPersonalizado.
 */
@ControllerAdvice
public class ManejadorErroresPersonalizado extends ResponseEntityExceptionHandler {

  /** The Constant log. */
  private static final Logger log = LoggerFactory.getLogger(ManejadorErroresPersonalizado.class);

  /**
   * Maneja excepciones.
   *
   * @param ex the ex
   * @return the response entity
   */
  @ResponseStatus // 500
  @ExceptionHandler(Exception.class)
  @ResponseBody
  public ResponseEntity<Object> manejaExcepciones(Exception ex) {
    log.error("Excepción general", ex);
    ErrorDTO eventoError = new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    RespuestaErrorPersonalizadaDTO respuestaError = new RespuestaErrorPersonalizadaDTO(eventoError);
    return new ResponseEntity<Object>(respuestaError, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * Maneja excepcion sin contenido.
   *
   * @param ex the ex
   * @return the response entity
   */
  @ResponseStatus // 204
  @ExceptionHandler(ExcepcionSinContenido.class)
  @ResponseBody
  public ResponseEntity<Object> manejaExcepcionSinContenido(ExcepcionSinContenido ex) {
    log.error("Excepción por respuesta sin contenido", ex);
    ErrorDTO eventoError = new ErrorDTO(HttpStatus.NO_CONTENT, ex.getMessage());
    RespuestaErrorPersonalizadaDTO respuestaError = new RespuestaErrorPersonalizadaDTO(eventoError);
    return new ResponseEntity<Object>(respuestaError, HttpStatus.NO_CONTENT);
  }

  /**
   * Maneja excepcion peticion incorrecta.
   *
   * @param ex the ex
   * @return the response entity
   */
  @ResponseStatus // 400
  @ExceptionHandler(ExcepcionPeticionIncorrecta.class)
  @ResponseBody
  public ResponseEntity<Object> manejaExcepcionPeticionIncorrecta(ExcepcionPeticionIncorrecta ex) {
    log.error("Excepción por petición incorrecta", ex);
    ErrorDTO eventoError = new ErrorDTO(HttpStatus.BAD_REQUEST, ex.getMessage());
    RespuestaErrorPersonalizadaDTO respuestaError = new RespuestaErrorPersonalizadaDTO(eventoError);
    return new ResponseEntity<Object>(respuestaError, HttpStatus.BAD_REQUEST);
  }

  /**
   * Maneja excepcion autorizado.
   *
   * @param ex the ex
   * @return the response entity
   */
  @ResponseStatus // 401
  @ExceptionHandler(ExcepcionNoAutorizado.class)
  @ResponseBody
  public ResponseEntity<Object> manejaExcepcionAutorizado(ExcepcionNoAutorizado ex) {
    log.error("Excepción por acceso no autorizado", ex);
    ErrorDTO eventoError = new ErrorDTO(HttpStatus.UNAUTHORIZED, ex.getMessage());
    RespuestaErrorPersonalizadaDTO respuestaError = new RespuestaErrorPersonalizadaDTO(eventoError);
    return new ResponseEntity<Object>(respuestaError, HttpStatus.UNAUTHORIZED);
  }

  /**
   * Maneja excepcion prohibido.
   *
   * @param ex the ex
   * @return the response entity
   */
  @ResponseStatus // 403
  @ExceptionHandler(ExcepcionProhibido.class)
  @ResponseBody
  public ResponseEntity<Object> manejaExcepcionProhibido(ExcepcionProhibido ex) {
    log.error("Excepción por acceso prohibido", ex);
    ErrorDTO eventoError = new ErrorDTO(HttpStatus.FORBIDDEN, ex.getMessage());
    RespuestaErrorPersonalizadaDTO respuestaError = new RespuestaErrorPersonalizadaDTO(eventoError);
    return new ResponseEntity<Object>(respuestaError, HttpStatus.FORBIDDEN);
  }

  /**
   * Maneja excepcion encontrado.
   *
   * @param ex the ex
   * @return the response entity
   */
  @ResponseStatus // 404
  @ExceptionHandler(ExcepcionRecursoNoEncontrado.class)
  @ResponseBody
  public ResponseEntity<Object> manejaExcepcionEncontrado(ExcepcionRecursoNoEncontrado ex) {
    log.error("Excepción por recurso no encontrado", ex);
    ErrorDTO eventoError = new ErrorDTO(HttpStatus.NOT_FOUND, ex.getMessage());
    RespuestaErrorPersonalizadaDTO respuestaError = new RespuestaErrorPersonalizadaDTO(eventoError);
    return new ResponseEntity<Object>(respuestaError, HttpStatus.NOT_FOUND);
  }

  /**
   * Maneja excepcion tiempo de espera.
   *
   * @param ex the ex
   * @return the response entity
   */
  @ResponseStatus // 408
  @ExceptionHandler(ExcepcionTiempoDeEspera.class)
  @ResponseBody
  public ResponseEntity<Object> manejaExcepcionTiempoDeEspera(ExcepcionTiempoDeEspera ex) {
    log.error("Excepción por tiempo de espera", ex);
    ErrorDTO eventoError = new ErrorDTO(HttpStatus.REQUEST_TIMEOUT, ex.getMessage());
    RespuestaErrorPersonalizadaDTO respuestaError = new RespuestaErrorPersonalizadaDTO(eventoError);
    return new ResponseEntity<Object>(respuestaError, HttpStatus.REQUEST_TIMEOUT);
  }

  /**
   * Maneja excepcion regla de negocio.
   *
   * @param ex the ex
   * @return the response entity
   */
  @ResponseStatus // 470
  @ExceptionHandler(ExcepcionReglaDeNegocio.class)
  @ResponseBody
  public ResponseEntity<Object> manejaExcepcionReglaDeNegocio(ExcepcionReglaDeNegocio ex) {
    log.error("Excepción por regla de negocio", ex);
    ErrorDTO eventoError = new ErrorDTO(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
    RespuestaErrorPersonalizadaDTO respuestaError = new RespuestaErrorPersonalizadaDTO(eventoError);
    return new ResponseEntity<Object>(respuestaError, HttpStatus.UNPROCESSABLE_ENTITY);
  }

  /**
   * Maneja excepcion error interno del servidor.
   *
   * @param ex the ex
   * @return the response entity
   */
  @ResponseStatus // 500
  @ExceptionHandler(ExcepcionErrorInternoDelServidor.class)
  @ResponseBody
  public ResponseEntity<Object> manejaExcepcionErrorInternoDelServidor(ExcepcionErrorInternoDelServidor ex) {
    log.error("Excepción por error interno del servidor", ex);
    ErrorDTO eventoError = new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    RespuestaErrorPersonalizadaDTO respuestaError = new RespuestaErrorPersonalizadaDTO(eventoError);
    return new ResponseEntity<Object>(respuestaError, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * Handle no handler found exception.
   *
   * @param ex      the ex
   * @param headers the headers
   * @param status  the status
   * @param request the request
   * @return the response entity
   */
  protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {
    log.error("Excepción causada por NoHandlerFoundException", ex);
    ErrorDTO eventoError = new ErrorDTO(HttpStatus.NOT_FOUND, ex.getMessage());
    RespuestaErrorPersonalizadaDTO respuestaError = new RespuestaErrorPersonalizadaDTO(eventoError);
    return new ResponseEntity<Object>(respuestaError, HttpStatus.NOT_FOUND);
  }

  /**
   * Handle missing path variable.
   *
   * @param ex      the ex
   * @param headers the headers
   * @param status  the status
   * @param request the request
   * @return the response entity
   */
  protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {
    log.error("Excepción por no tener una variable del path", ex);
    ErrorDTO eventoError = new ErrorDTO(HttpStatus.BAD_REQUEST, ex.getMessage());
    RespuestaErrorPersonalizadaDTO respuestaError = new RespuestaErrorPersonalizadaDTO(eventoError);
    return new ResponseEntity<Object>(respuestaError, HttpStatus.BAD_REQUEST);
  }

  /**
   * Handle http message not readable.
   *
   * @param ex      the ex
   * @param headers the headers
   * @param status  the status
   * @param request the request
   * @return the response entity
   */
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {
    log.error("Excepción causada por HttpMessageNotReadableException", ex);
    ErrorDTO eventoError = new ErrorDTO(HttpStatus.NOT_FOUND, ex.getMessage());
    RespuestaErrorPersonalizadaDTO respuestaError = new RespuestaErrorPersonalizadaDTO(eventoError);
    return new ResponseEntity<Object>(respuestaError, HttpStatus.NOT_FOUND);
  }

  /**
   * Handle method argument not valid.
   *
   * @param ex      the ex
   * @param headers the headers
   * @param status  the status
   * @param request the request
   * @return the response entity
   */
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {

    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {

      String fieldName = ((FieldError) error).getField();
      String message = error.getDefaultMessage();
      errors.put(fieldName, message);
    });
    return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
  }
}