package mx.com.backend.asesores.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class RespuestaErrorPersonalizadaDTO {

  /** The error. */
  @ApiModelProperty(notes = "Error", required = true)
  private ErrorDTO error;
}