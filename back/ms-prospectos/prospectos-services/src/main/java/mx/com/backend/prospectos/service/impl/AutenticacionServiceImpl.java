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
import mx.com.backend.prospectos.dto.TrAutenticacionEnviarCodigoDTO;
import mx.com.backend.prospectos.dto.TrAutenticacionValidarCodigoDTO;
import mx.com.backend.prospectos.exceptions.ExcepcionErrorInternoDelServidor;
import mx.com.backend.prospectos.exceptions.ExcepcionReglaDeNegocio;
import mx.com.backend.prospectos.model.TrCodigoAutenticacionEntity;
import mx.com.backend.prospectos.repository.AsesorRepository;
import mx.com.backend.prospectos.repository.CodigoAutenticacionRepository;
import mx.com.backend.prospectos.service.AutenticacionService;

/**
 * The Class AutenticacionServiceImpl.
 */
@Component
public class AutenticacionServiceImpl implements AutenticacionService {

  /** The codigo autenticacion repository. */
  @Autowired
  CodigoAutenticacionRepository codigoAutenticacionRepository;

  /** The asesor repository. */
  @Autowired
  AsesorRepository asesorRepository;

  /** The email sender. */
  @Autowired
  private JavaMailSender emailSender;

  /** The email from. */
  @Value("${mail.from}")
  private String emailFrom;

  /** The account sid. */
  @Value("${account.sid}")
  private String accountSid;

  /** The auth token. */
  @Value("${auth.token}")
  private String authToken;

  /** The telefono origen. */
  @Value("${telefono.origen}")
  private String telefonoOrigen;

  /**
   * Enviar codigo.
   *
   * @param trAutenticacionEnviarCodigoDto the tr autenticacion enviar codigo dto
   * @return the map
   */
  @Override
  public Map<String, Object> enviarCodigo(TrAutenticacionEnviarCodigoDTO trAutenticacionEnviarCodigoDto) {
    Map<String, Object> respuestaMap = new HashMap<>();
    respuestaMap = setRespuestaMap(Constantes.FALSE, Constantes.CERO);
    try {
      Long idAsesor = asesorRepository
          .obtenerIdAsesorPorNumeroEmpleado(trAutenticacionEnviarCodigoDto.getNumeroEmpleado());
      if (idAsesor != null) {
        String codigo = generarCodigo();
        String codigoEncriptado = encriptarCodigo(codigo);
        if (trAutenticacionEnviarCodigoDto.getMedioAutenticacion() == Constantes.MEDIO_AUTENTICACION_SMS) {
          enviarSmsMensage(trAutenticacionEnviarCodigoDto.getDestinatario(), telefonoOrigen, codigo);
        } else {
          enviarEmailMensage(emailFrom, trAutenticacionEnviarCodigoDto.getDestinatario(), codigo);
        }
        respuestaMap = guardarCodigoAutenticacion(trAutenticacionEnviarCodigoDto, codigoEncriptado, idAsesor, respuestaMap);
      } else {
        throw new ExcepcionReglaDeNegocio("Error al procesar el numero de empleado.");
      }
    } catch (ExcepcionReglaDeNegocio e) {
      throw new ExcepcionReglaDeNegocio(e.getMessage());
    }
    return respuestaMap;
  }

  /**
   * Enviar sms mensage.
   *
   * @param telefonoDestinatario the telefono destinatario
   * @param origen               the origen
   * @param codigo               the codigo
   */
  private void enviarSmsMensage(String telefonoDestinatario, String origen, String codigo) {
    Twilio.init(accountSid, authToken);
    Message.creator(new PhoneNumber(telefonoDestinatario), new PhoneNumber(origen),
        Constantes.MENSAJE_CODIGO_AUTENTICACION.concat(codigo)).create();
  }

  /**
   * Generar codigo.
   *
   * @return the string
   */
  private String generarCodigo() {
    String codigo = String.format("%06d", new Random().nextInt(1000000));
    return codigo;
  }

  /**
   * Encriptar codigo.
   *
   * @param codigo the codigo
   * @return the string
   */
  private String encriptarCodigo(String codigo) {
    byte[] bytesEncriptados = Base64.getEncoder().encode(codigo.getBytes(StandardCharsets.UTF_8));
    return new String(bytesEncriptados, StandardCharsets.UTF_8);
  }

  /**
   * Desencriptar codigo.
   *
   * @param codigo the codigo
   * @return the string
   */
  private String desencriptarCodigo(String codigo) {
    byte[] bytesDesencriptados = Base64.getDecoder().decode(codigo.getBytes(StandardCharsets.UTF_8));
    return new String(bytesDesencriptados, StandardCharsets.UTF_8);
  }

  /**
   * Guardar codigo autenticacion.
   *
   * @param trAutenticacionEnviarCodigoDto the tr autenticacion enviar codigo dto
   * @param codigoEncriptado   the codigo encriptado
   * @param idAsesor           the id asesor
   * @param respuestaMap       the respuesta map
   * @return the map
   */
  private Map<String, Object> guardarCodigoAutenticacion(TrAutenticacionEnviarCodigoDTO trAutenticacionEnviarCodigoDto,
      String codigoEncriptado, long idAsesor, Map<String, Object> respuestaMap) {
    try {
      TrCodigoAutenticacionEntity trCodigoAutenticacionEntity = new TrCodigoAutenticacionEntity(null, codigoEncriptado,
          trAutenticacionEnviarCodigoDto.getMedioAutenticacion(), trAutenticacionEnviarCodigoDto.getDestinatario(),
          ConstantesFormatosFecha.obtenerFechaActualMx(), trAutenticacionEnviarCodigoDto.getCurp(), idAsesor);
      codigoAutenticacionRepository.save(trCodigoAutenticacionEntity);
      respuestaMap = setRespuestaMap(Constantes.TRUE,Constantes.UNO);
    } catch (Exception e) {
      throw new ExcepcionErrorInternoDelServidor("Error al procesar la informacion.");
    }
    return respuestaMap;
  }

  /**
   * Enviar email mensage.
   *
   * @param emailDe   the email de
   * @param emailPara the email para
   * @param codigo    the codigo
   */
  private void enviarEmailMensage(String emailDe, String emailPara, String codigo) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom(emailDe);
    message.setTo(emailPara);
    message.setSubject(Constantes.ASUNTO_CODIGO_AUTENTICACION);
    message.setText(Constantes.MENSAJE_CODIGO_AUTENTICACION.concat(codigo));
    emailSender.send(message);
  }

  /**
   * Validar codigo curp.
   *
   * @param trAutenticacionValidarCodigoDto the tr autenticacion validar codigo dto
   * @return the map
   */
  @Override
  public Map<String, Object> validarCodigoCurp(TrAutenticacionValidarCodigoDTO trAutenticacionValidarCodigoDto) {
    Map<String, Object> respuestaMap = new HashMap<>();
    respuestaMap = setRespuestaMap(Constantes.FALSE, Constantes.CERO);
    try {
      Long idAsesor = asesorRepository
          .obtenerIdAsesorPorNumeroEmpleado(trAutenticacionValidarCodigoDto.getNumeroEmpleado());
      if (idAsesor != null) {
        String codigoDesencriptado = desencriptarCodigo(codigoAutenticacionRepository.obtenerCodigoPorCurpNumeroEmpleado(idAsesor,
            trAutenticacionValidarCodigoDto.getCurp()));
        if (codigoDesencriptado.equals(trAutenticacionValidarCodigoDto.getCodigo())) {
          respuestaMap = setRespuestaMap(Constantes.TRUE,Constantes.UNO);
        }
      } else {
        throw new ExcepcionReglaDeNegocio("Error al procesar el numero de empleado.");
      }
    } catch (Exception e) {
      throw new ExcepcionErrorInternoDelServidor("Error al procesar la validación de codigo.");
    }
    return respuestaMap;
  }
  
  /**
   * Sets the respuesta map.
   *
   * @param resultado the resultado
   * @param codigo the codigo
   * @return the map
   */
  private Map<String, Object> setRespuestaMap(Object resultado, Object codigo) {
    Map<String, Object> respuestaMap = new HashMap<>();
    try {
      respuestaMap.put(Constantes.RESULTADO, resultado);
      respuestaMap.put(Constantes.CODIGO, codigo);
    } catch (Exception e) {
      throw new ExcepcionErrorInternoDelServidor("Error al procesar la respuesta Map.");
    }
    return respuestaMap;
  }
}
