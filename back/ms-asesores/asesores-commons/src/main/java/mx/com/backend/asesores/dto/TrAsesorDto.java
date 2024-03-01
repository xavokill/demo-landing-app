package mx.com.backend.asesores.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.com.backend.asesores.commons.ConstantesFormatosFecha;

/**
 * To string.
 *
 * @return the java.lang. string
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrAsesorDto {

  /** The id asesor. */
  private Long idAsesor;

  /** The nombre asesor. */
  private String nombreAsesor;

  /** The apellido paterno asesor. */
  private String apellidoPaternoAsesor;

  /** The apellido materno asesor. */
  private String apellidoMaternoAsesor;

  /** The correo electronico asesor. */
  private String correoElectronicoAsesor;

  /** The contrasenia asesor. */
  private String contraseniaAsesor;

  /** The fecha creacion asesor. */
  @JsonFormat(pattern = ConstantesFormatosFecha.FORMATO_FECHA_JSON)
  private String fechaCreacionAsesor;

  /** The fecha actualizacion asesor. */
  @JsonFormat(pattern = ConstantesFormatosFecha.FORMATO_FECHA_JSON)
  private String fechaActualizacionAsesor;

  /** The fecha estatus asesor. */
  @JsonFormat(pattern = ConstantesFormatosFecha.FORMATO_FECHA_JSON)
  private String fechaEstatusAsesor;

  /** The fk id estatus asesor. */
  private Long fkIdEstatusAsesor;

  /** The numero empleado asesor. */
  private String numeroEmpleadoAsesor;

}
