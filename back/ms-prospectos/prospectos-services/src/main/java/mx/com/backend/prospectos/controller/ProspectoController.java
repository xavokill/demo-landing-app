package mx.com.backend.prospectos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import mx.com.backend.prospectos.exceptions.ExcepcionErrorInternoDelServidor;
import mx.com.backend.prospectos.service.ProspectoService;
import mx.com.backend.prospectos.dto.TrProspectoDTO;

/**
 * The Class ProspectoController.
 */
@RestController
@RequestMapping("prospectos")
@Api(value = "api prospectos")
@Slf4j
public class ProspectoController {
  
  @Autowired
  private ProspectoService prospectoService;

  @PostMapping("prospectos")
  @ApiOperation(value = "Crear prospecto.")
  public ResponseEntity<TrProspectoDTO> crearProspecto(@RequestBody TrProspectoDTO trProspectoDto) {
    try {
      trProspectoDto = prospectoService.crearProspecto(trProspectoDto);
    } catch (Exception e) {
      throw new ExcepcionErrorInternoDelServidor(e.getMessage());
    }
    log.info(HttpStatus.CREATED.getReasonPhrase());
    return new ResponseEntity<TrProspectoDTO>(trProspectoDto, HttpStatus.OK);
  }

}
