package mx.com.backend.prospectos.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * To string.
 *
 * @return the java.lang. string
 */
@Data
@AllArgsConstructor
public class RespuestaErrorPersonalizadaDTO {

  /** The error. */
  @ApiModelProperty(notes = "Error", required = true)
  private ErrorDTO error;
}