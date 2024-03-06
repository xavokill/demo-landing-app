package mx.com.backend.prospectos.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TrProspectoDTO {
  
  /** The id prospecto. */
  private Long idProspecto;
  
  /** The correo electronico prospecto. */
  private String correoElectronicoProspecto;
  
  /** The curp prospecto. */
  private String curpProspecto;
  
  /** The telefono prospecto. */
  private String telefonoProspecto;
  
  /** The fecha creacion prospecto. */
  private String fechaCreacionProspecto;
  
  /** The fk id asesor. */
  private String numeroEmpleadoAsesor;

}
