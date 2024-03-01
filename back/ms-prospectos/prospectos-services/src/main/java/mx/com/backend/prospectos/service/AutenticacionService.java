package mx.com.backend.prospectos.service;

import java.util.Map;
import mx.com.backend.prospectos.dto.TrAutenticacionDTO;

public interface AutenticacionService {
	
	public Map<String, Object> enviarCodigo(TrAutenticacionDTO trAutenticacionDto);

}
