package mx.com.backend.prospectos.dto;

import javax.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The Class TrAutenticacionValidarCodigoDTO.
 */

@Data
@EqualsAndHashCode(callSuper=true)
public class TrAutenticacionValidarCodigoDTO extends TrAutenticacionDTO {
  
  /** The codigo. */
  @Size(min = 6, max = 6, message = "El codigo debe ser de 6 posiciones")
  private String codigo;

}
