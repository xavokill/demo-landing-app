package mx.com.backend.prospectos.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import mx.com.backend.prospectos.commons.Constantes;
import mx.com.backend.prospectos.commons.ConstantesFormatosFecha;
import mx.com.backend.prospectos.dto.TrAutenticacionDTO;
import mx.com.backend.prospectos.exceptions.ExcepcionErrorInternoDelServidor;
import mx.com.backend.prospectos.exceptions.ExcepcionReglaDeNegocio;
import mx.com.backend.prospectos.model.TrCodigoAutenticacionEntity;
import mx.com.backend.prospectos.repository.AsesorRepository;
import mx.com.backend.prospectos.repository.CodigoAutenticacionRepository;
import mx.com.backend.prospectos.service.AutenticacionService;

@Component
public class AutenticacionServiceImpl implements AutenticacionService {

	@Autowired
	CodigoAutenticacionRepository codigoAutenticacionRepository;

	@Autowired
	AsesorRepository asesorRepository;

	@Autowired
	private JavaMailSender emailSender;

	@Value("${mail.from}")
	private String emailFrom;

	@Value("${account.sid}")
	private String accountSid;

	@Value("${auth.token}")
	private String authToken;

	@Value("${telefono.origen}")
	private String telefonoOrigen;

	@Override
	public Map<String, Object> enviarCodigo(TrAutenticacionDTO trAutenticacionDto) {
		Map<String, Object> respuestaMap = new HashMap<>();
		respuestaMap.put(Constantes.RESULTADO, Constantes.FALSE);
		respuestaMap.put(Constantes.CODIGO, Constantes.CERO);
		try {
			Long idAsesor = asesorRepository.obtenerIdAsesorPorNumeroEmpleado(trAutenticacionDto.getNumeroEmpleado());
			if (idAsesor != null) {
				String codigo = generarCodigo();
				String codigoEncriptado = encriptarCodigo(codigo);
				if (trAutenticacionDto.getMedioAutenticacion() == Constantes.MEDIO_AUTENTICACION_SMS) {
					enviarSmsMensage(trAutenticacionDto.getDestinatario(), telefonoOrigen, codigo);
				} else {
					enviarEmailMensage(emailFrom, trAutenticacionDto.getDestinatario(), codigo);
				}
				guardarCodigoAutenticacion(trAutenticacionDto, codigoEncriptado, idAsesor, respuestaMap);
			} else {
				throw new ExcepcionReglaDeNegocio("Error al procesar el numero de empleado.");
			}
		} catch (ExcepcionReglaDeNegocio e) {
			throw new ExcepcionReglaDeNegocio(e.getMessage());
		}
		return respuestaMap;
	}

	private void enviarSmsMensage(String telefonoDestinatario, String origen, String codigo) {
		Twilio.init(accountSid, authToken);
		Message.creator(new PhoneNumber("+52".concat(telefonoDestinatario)), new PhoneNumber(origen),
				Constantes.MENSAJE_CODIGO_AUTENTICACION.concat(codigo)).create();
	}

	private String generarCodigo() {
		String codigo = String.format("%06d", new Random().nextInt(1000000));
		return codigo;
	}

	private String encriptarCodigo(String codigo) {
		byte[] bytesEncriptados = Base64.getEncoder().encode(codigo.getBytes(StandardCharsets.UTF_8));
		return new String(bytesEncriptados, StandardCharsets.UTF_8);
	}

	private String desencriptarCodigo(String codigo) {
		byte[] bytesDesencriptados = Base64.getDecoder().decode(codigo.getBytes(StandardCharsets.UTF_8));
		return new String(bytesDesencriptados, StandardCharsets.UTF_8);
	}

	private Map<String, Object> guardarCodigoAutenticacion(TrAutenticacionDTO trAutenticacionDto,
			String codigoEncriptado, long idAsesor, Map<String, Object> respuestaMap) {
		try {
			TrCodigoAutenticacionEntity trCodigoAutenticacionEntity = new TrCodigoAutenticacionEntity(null,
					codigoEncriptado, trAutenticacionDto.getMedioAutenticacion(), trAutenticacionDto.getDestinatario(),
					ConstantesFormatosFecha.obtenerFechaActualMx(), trAutenticacionDto.getCurp(), idAsesor);
			codigoAutenticacionRepository.save(trCodigoAutenticacionEntity);
			respuestaMap.put(Constantes.RESULTADO, Constantes.TRUE);
			respuestaMap.put(Constantes.CODIGO, Constantes.UNO);
		} catch (Exception e) {
			throw new ExcepcionErrorInternoDelServidor("Error al procesar la informacion.");
		}
		return respuestaMap;
	}

	private void enviarEmailMensage(String emailDe, String emailPara, String codigo) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(emailDe);
		message.setTo(emailPara);
		message.setSubject(Constantes.ASUNTO_CODIGO_AUTENTICACION);
		message.setText(Constantes.MENSAJE_CODIGO_AUTENTICACION.concat(codigo));
		emailSender.send(message);
	}

}