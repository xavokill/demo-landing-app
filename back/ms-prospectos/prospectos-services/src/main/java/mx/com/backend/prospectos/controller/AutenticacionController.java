package mx.com.backend.prospectos.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mx.com.backend.prospectos.dto.TrAutenticacionDTO;
import mx.com.backend.prospectos.exceptions.ExcepcionReglaDeNegocio;
import mx.com.backend.prospectos.service.AutenticacionService;

@RestController
@RequestMapping("autenticacion")
public class AutenticacionController {

	/** The Constant log. */
	private static final Logger log = LoggerFactory.getLogger(AutenticacionController.class);

	private Map<String, Object> respuestaMap = new HashMap<>();

	@Autowired
	AutenticacionService autenticacionService;

	@PostMapping("enviarCodigo")
	public ResponseEntity<Map<String, Object>> enviarCodigo(@RequestBody @Valid TrAutenticacionDTO trAutenticacionDto) {
		try {
			respuestaMap = autenticacionService.enviarCodigo(trAutenticacionDto);
		} catch (ExcepcionReglaDeNegocio e) {
			throw new ExcepcionReglaDeNegocio(e.getMessage());
		} 
		return ResponseEntity.status(HttpStatus.OK).body(respuestaMap);
	}
	
	@PostMapping("enviarCodigoMock")
	public ResponseEntity<Map<String, Object>> enviarCodigoMock(@RequestBody @Valid TrAutenticacionDTO trAutenticacionDto) {
		try {
			respuestaMap.put("respuesta", true);
			respuestaMap.put("mensaje", "exitoso");
		} catch (ExcepcionReglaDeNegocio e) {
			throw new ExcepcionReglaDeNegocio(e.getMessage());
		} 
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaMap);
	}
}
