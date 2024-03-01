package mx.com.backend.asesores.commons;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import mx.com.backend.asesores.exceptions.ExcepcionProhibido;

/**
 * The Class ConstantesFormatosFecha.
 */
public class ConstantesFormatosFecha {
  
  /** The Constant FORMATO_FECHA_JSON. */
  public static final String FORMATO_FECHA_JSON = "yyyy-MM-dd HH:mm";
  
  /** The Constant FORMATO_FECHA_HORA_JSON_T. */
  public static final String FORMATO_FECHA_HORA_JSON_T = "yyyy-MM-dd'T'HH:mm";
  
  /**
   * Obtener fecha actual mx.
   *
   * @return the date
   */
  public static Date obtenerFechaActualMx() {
    Date fechaActualMx = null;
    LocalDateTime localDateTime = null;
    try {
      Instant now = Instant.now();
      ZoneId zoneId = ZoneId.of("SystemV/CST6");
      ZonedDateTime dateAndTimeInMx = ZonedDateTime.ofInstant(now, zoneId);
      localDateTime = dateAndTimeInMx.toLocalDateTime();
      SimpleDateFormat formateador = new SimpleDateFormat(FORMATO_FECHA_HORA_JSON_T);
      fechaActualMx = formateador.parse(localDateTime.toString());
    } catch (Exception e) {
      throw new ExcepcionProhibido(ConstantesExcepcion.MENSAJE_HORA_ACTUAL_NO_DISPONIBLE);
    }
    return fechaActualMx;
  }
}
