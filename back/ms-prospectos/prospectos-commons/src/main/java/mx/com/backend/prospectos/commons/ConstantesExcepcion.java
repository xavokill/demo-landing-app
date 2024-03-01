package mx.com.backend.prospectos.commons;

/**
 * The Class ConstantesExcepcion.
 */
public final class ConstantesExcepcion {

  /**
   * Instantiates a new constantes excepcion.
   */
  private ConstantesExcepcion() {
    super();
  }

  /** The Constant MENSAJE_RECURSO_NO_ENCONTRADO. */
  public static final String MENSAJE_RECURSO_NO_ENCONTRADO = "Recurso no encontrado";

  /** The Constant MENSAJE_ERROR_RESTRICCION_DE_NEGOCIO. */
  public static final String MENSAJE_ERROR_RESTRICCION_DE_NEGOCIO = "La solicitud no puede ser "
      + "procesada, restricción de negocio.";

  /** The Constant MENSAJE_HORA_ACTUAL_NO_DISPONIBLE. */
  public static final String MENSAJE_HORA_ACTUAL_NO_DISPONIBLE = "La hora actual no se encuentra"
      + " disponible.";
}
