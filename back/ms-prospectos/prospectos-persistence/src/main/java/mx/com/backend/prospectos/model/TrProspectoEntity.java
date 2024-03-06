package mx.com.backend.prospectos.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class TrProspectoEntity.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "TR_PROSPECTO")
@SequenceGenerator(name = "idProspectoSeq", sequenceName = "SEQ_TR_PROSPECTO", allocationSize = 1)
@NamedQuery(name = "TrProspectoEntity.findAll", query = "SELECT e FROM TrAsesorEntity e")
public class TrProspectoEntity {
  
  /** The id prospecto. */
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idProspectoSeq")
  @Column(name = "ID_PROSPECTO")
  private Long idProspecto;
  
  /** The correo electronico prospecto. */
  @Column(name = "CORREO_ELECTRONICO_PROSPECTO")
  private String correoElectronicoProspecto;
  
  /** The curp prospecto. */
  @Column(name = "CURP_PROSPECTO")
  private String curpProspecto;
  
  /** The telefono prospecto. */
  @Column(name = "TELEFONO_PROSEPCTO")
  private String telefonoProspecto;
  
  /** The fecha creacion prospecto. */
  @Column(name = "FECHA_CREACION_PROSEPCTO")
  private Date fechaCreacionProspecto;
  
  /** The fk id asesor. */
  @Column(name="FK_ID_ASESOR")
  private Long fkIdAsesor;

}
