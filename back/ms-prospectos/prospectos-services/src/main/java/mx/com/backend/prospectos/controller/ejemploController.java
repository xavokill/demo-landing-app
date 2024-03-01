package mx.com.backend.prospectos.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ejemplo")
public class ejemploController {
	
	@PostMapping("/enviarCodigo")
	public String enviarCodigo () {
		return "hola";
		
	}

}
