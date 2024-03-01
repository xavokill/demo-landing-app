package mx.com.backend.prospectos.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "TR_CODIGO_AUTENTICACION")
@SequenceGenerator(name = "idCodigoAutenticacionSeq", sequenceName = "SEQ_TR_CODIGO_AUTENTICACION", allocationSize = 1)
@NamedQuery(name = "TrCodigoAutenticacionEntity.findAll", query = "SELECT e FROM TrCodigoAutenticacionEntity e")
public class TrCodigoAutenticacionEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idCodigoAutenticacionSeq")
	@Column(name = "ID_CODIGO_AUTENTICACION")
	private Long idCodigoAutenticacion;

	@Column(name = "CODIGO_AUTENTICACION")
	private String codigoAutenticacion;

	@Column(name = "FK_ID_MEDIO_AUTENTICACION")
	private Long fkIdMedioAutenticacion;

	@Column(name = "DESTINATARIO")
	private String destinatario;

	@Column(name = "FECHA_CREACION_CODIGO_AUTENTICACION")
	private Date fechaCreacionCodigoAutenticacion;

	@Column(name = "CURP")
	private String curp;

	@Column(name = "FK_ID_ASESOR")
	private Long fkIdAsesor;

}
