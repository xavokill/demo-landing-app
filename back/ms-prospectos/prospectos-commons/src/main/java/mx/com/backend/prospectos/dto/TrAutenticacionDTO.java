package mx.com.backend.prospectos.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;
import lombok.Data;

@Data
public class TrAutenticacionDTO {

	@Pattern(regexp = "^[A-Z]{1}[AEIOUX]{1}[A-ZX]{2}[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])[HM]{1}(AS|BC|BS|CC|CS|CH|CL|CM|DF|DG|GT|GR|HG|JC|MC|MN|MS|NT|NL|OC|PL|QT|QR|SP|SL|SR|TC|TS|TL|VZ|YN|ZS|NE)[B-DF-HJ-NP-TV-Z]{3}[0-9A-Z]{1}[0-9]{1}$", message = "El valor debe corresponder al registro unico de poblacion")
	private String curp;

	@Size(min = 1, max = 50, message = "El destinatario es a 100 posiciones maximo y minimo de 1")
	private String destinatario;

	@Size(min = 1, max = 50, message = "El numero de empleado es a 50 posiciones maximo y minimo de 1")
	private String numeroEmpleado;

	@Range(min = 1, max = 2, message = "Los valores solo pueden ser 1 o 2")
	private long medioAutenticacion;
}
