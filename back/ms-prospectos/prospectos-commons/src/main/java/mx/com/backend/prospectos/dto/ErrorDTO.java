package mx.com.backend.prospectos.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import mx.com.backend.prospectos.commons.ConstantesFormatosFecha;
import org.springframework.http.HttpStatus;

/**
 * To string.
 *
 * @return the java.lang. string
 */
@Data
@AllArgsConstructor
public class ErrorDTO {

  /**
   * Instantiates a new error DTO.
   *
   * @param codigoRespuesta the codigo respuesta
   * @param mensaje the mensaje
   */
  public ErrorDTO(HttpStatus codigoRespuesta, String mensaje) {
    this.mensaje = mensaje;
    this.codigoRespuesta = codigoRespuesta;
    this.codigoError = codigoRespuesta.value();
    this.fecha = new Date();
  }

  /** The codigo error. */
  @ApiModelProperty(notes = "Código de estado de respuesta HTTTP", required = true)
  private int codigoError; // 200, 201, 400, 500

  /** Codigo de respuesta. */
  @ApiModelProperty(notes = "Código de estado de respuesta HTTTP en texto", required = true)
  private HttpStatus codigoRespuesta;

  /** Mensaje. */
  @ApiModelProperty(notes = "Mensaje", required = true)
  private String mensaje;

  /** Fecha. */
  @ApiModelProperty(notes = "Fecha", required = true)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ConstantesFormatosFecha.FORMATO_FECHA_JSON)
  private Date fecha;
}
