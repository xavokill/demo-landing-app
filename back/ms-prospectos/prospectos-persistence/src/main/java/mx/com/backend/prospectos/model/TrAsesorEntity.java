package mx.com.backend.prospectos.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "TR_ASESOR")
@SequenceGenerator(name = "idAsesorSeq", sequenceName = "SEQ_TR_ASESOR", allocationSize = 1)
@NamedQuery(name = "TrAsesorEntity.findAll", query = "SELECT e FROM TrAsesorEntity e")
public class TrAsesorEntity implements Serializable {

    /** The id asesor. */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idAsesorSeq")
    @Column(name = "ID_ASESOR")
    private Long idAsesor;

    /** The nombre asesor. */
    @Column(name = "NOMBRE_ASESOR")
    private String nombreAsesor;

    /** The apellido paterno asesor. */
    @Column(name = "APELLIDO_PATERNO_ASESOR")
    private String apellidoPaternoAsesor;

    /** The apellido materno asesor. */
    @Column(name = "APELLIDO_MATERNO_ASESOR")
    private String apellidoMaternoAsesor;

    /** The correo electronico asesor. */
    @Column(name = "CORREO_ELECTRONICO_ASESOR")
    private String correoElectronicoAsesor;

    /** The contrasenia asesor. */
    @Column(name = "CONTRASENIA_ASESOR")
    private String contraseniaAsesor;

    /** The fecha creacion asesor. */
    @Column(name = "FECHA_CREACION_ASESOR")
    private Date fechaCreacionAsesor;

    /** The fecha actualizacion asesor. */
    @Column(name = "FECHA_ACTUALIZACION_ASESOR")
    private Date fechaActualizacionAsesor;

    /** The fecha estatus asesor. */
    @Column(name = "FECHA_ESTATUS_ASESOR")
    private Date fechaEstatusAsesor;

    /** The fk id estatus asesor. */
    @Column(name = "FK_ID_ESTATUS_ASESOR")
    private Long fkIdEstatusAsesor;

    /** The numero empleado asesor. */
    @Column(name = "NUMERO_EMPLEADO_ASESOR")
    private String numeroEmpleadoAsesor;
}
