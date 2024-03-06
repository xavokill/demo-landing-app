package mx.com.backend.prospectos.service;

import java.util.Map;
import mx.com.backend.prospectos.dto.TrAutenticacionEnviarCodigoDTO;
import mx.com.backend.prospectos.dto.TrAutenticacionValidarCodigoDTO;

/**
 * The Interface AutenticacionService.
 */
public interface AutenticacionService {
	
	/**
	 * Enviar codigo.
	 *
	 * @param trAutenticacionDto the tr autenticacion dto
	 * @return the map
	 */
	public Map<String, Object> enviarCodigo(TrAutenticacionEnviarCodigoDTO trAutenticacionEnviarCodigoDto);
	
	/**
	 * Validar codigo curp.
	 * @param codigo 
	 * @param curp 
	 *
	 * @return the map
	 */
	public Map<String, Object> validarCodigoCurp(TrAutenticacionValidarCodigoDTO trAutenticacionValidarCodigoDto);
}
