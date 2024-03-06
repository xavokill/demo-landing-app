package mx.com.backend.prospectos.dto;

import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class TrAutenticacionEnviarCodigoDTO extends TrAutenticacionDTO {
  
  @Range(min = 1, max = 2, message = "Los valores solo pueden ser 1 o 2")
  private long medioAutenticacion;
  
  @Size(min = 1, max = 50, message = "El destinatario es a 100 posiciones maximo y minimo de 1")
  private String destinatario;

}
