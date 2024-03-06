package mx.com.backend.prospectos.controller;

import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mx.com.backend.prospectos.dto.TrAutenticacionDTO;
import mx.com.backend.prospectos.dto.TrAutenticacionEnviarCodigoDTO;
import mx.com.backend.prospectos.dto.TrAutenticacionValidarCodigoDTO;
import mx.com.backend.prospectos.exceptions.ExcepcionReglaDeNegocio;
import mx.com.backend.prospectos.service.AutenticacionService;

/**
 * The Class AutenticacionController.
 */
@RestController
@RequestMapping("autenticacion")
public class AutenticacionController {

  /** The autenticacion service. */
  @Autowired
  AutenticacionService autenticacionService;

  /**
   * Enviar codigo.
   *
   * @param trAutenticacionDto the tr autenticacion dto
   * @return the response entity
   */
  @PostMapping("enviarCodigo")
  public ResponseEntity<Map<String, Object>> enviarCodigo(@RequestBody @Valid TrAutenticacionEnviarCodigoDTO trAutenticacionEnviarCodigoDto) {
    Map<String, Object> respuestaMap = new HashMap<>();
    try {
      respuestaMap = autenticacionService.enviarCodigo(trAutenticacionEnviarCodigoDto);
    } catch (ExcepcionReglaDeNegocio e) {
      throw new ExcepcionReglaDeNegocio(e.getMessage());
    }
    return ResponseEntity.status(HttpStatus.OK).body(respuestaMap);
  }

  /**
   * Validar codigo.
   *
   * @param curp the curp
   * @param codigo the codigo
   * @return the response entity
   */
  @PostMapping("validarCodigo")
  public ResponseEntity<Map<String, Object>> validarCodigo(@RequestBody @Valid TrAutenticacionValidarCodigoDTO trAutenticacionValidarCodigoDto) {
    Map<String, Object> respuestaMap = new HashMap<>();
    try {
      respuestaMap = autenticacionService.validarCodigoCurp(trAutenticacionValidarCodigoDto);
    } catch (ExcepcionReglaDeNegocio e) {
      throw new ExcepcionReglaDeNegocio(e.getMessage());
    }
    return ResponseEntity.status(HttpStatus.OK).body(respuestaMap);
  }

  /**
   * Enviar codigo mock.
   *
   * @param trAutenticacionDto the tr autenticacion dto
   * @return the response entity
   */
  @PostMapping("enviarCodigoMock")
  public ResponseEntity<Map<String, Object>> enviarCodigoMock(
      @RequestBody @Valid TrAutenticacionDTO trAutenticacionDto) {
    Map<String, Object> respuestaMap = new HashMap<>();
    try {
      respuestaMap.put("respuesta", true);
      respuestaMap.put("mensaje", "exitoso");
    } catch (ExcepcionReglaDeNegocio e) {
      throw new ExcepcionReglaDeNegocio(e.getMessage());
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaMap);
  }
  
}
