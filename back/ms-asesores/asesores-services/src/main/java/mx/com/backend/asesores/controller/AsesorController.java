package mx.com.backend.asesores.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mx.com.backend.asesores.dto.TrAsesorDto;
import mx.com.backend.asesores.exceptions.ExcepcionErrorInternoDelServidor;
import mx.com.backend.asesores.exceptions.ExcepcionRecursoNoEncontrado;
import mx.com.backend.asesores.service.AsesorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Class AsesorController.
 */
@RestController
@RequestMapping("asesores/")
@Api(value = "api asesores")
public class AsesorController {

  /** The Constant log. */
  private static final Logger log = LoggerFactory.getLogger(ApplicationController.class);

  /** The application service. */
  @Autowired
  private AsesorService asesorService;

  /**
   * Crear asesor.
   *
   * @param asesorDto the asesor dto
   * @return the response entity
   */
  @PostMapping(produces = "application/json", value = { "asesor" })
  @ApiOperation(value = "Crear asesor.")
  public ResponseEntity<TrAsesorDto> crearAsesor(@RequestBody TrAsesorDto asesorDto) {
    try {
      asesorDto = asesorService.crearAsesor(asesorDto);
    } catch (Exception e) {
      throw new ExcepcionErrorInternoDelServidor(e.getMessage());
    }
    log.info(HttpStatus.CREATED.getReasonPhrase());
    return new ResponseEntity<TrAsesorDto>(asesorDto, HttpStatus.CREATED);
  }

  /**
   * Gets the asesor.
   *
   * @param idAsesor the id asesor
   * @return the asesor
   */
  @GetMapping(produces = "application/json", value = { "asesor/{idAsesor}" })
  @ApiOperation(value = "Obtener asesor.")
  public ResponseEntity<TrAsesorDto> obetenerAsesor(@PathVariable long idAsesor) {
    TrAsesorDto trAsesorDto = new TrAsesorDto();
    try {
      trAsesorDto = asesorService.obtenerAsesor(idAsesor);
    } catch (ExcepcionRecursoNoEncontrado e) {
      throw new ExcepcionRecursoNoEncontrado(e.getMessage());
    } catch (ExcepcionErrorInternoDelServidor e) {
      throw new ExcepcionErrorInternoDelServidor(e.getMessage());
    }
    return new ResponseEntity<TrAsesorDto>(trAsesorDto, HttpStatus.OK);
  }

  /**
   * Eliminar asesor.
   *
   * @param idAsesor the id asesor
   * @return the response entity
   */
  @DeleteMapping(produces = "application/json", value = { "asesor" })
  @ApiOperation(value = "Eliminar asesor.")
  public ResponseEntity<TrAsesorDto> eliminarAsesor(@RequestParam(required = true) long idAsesor) {
    TrAsesorDto trAsesorDto = new TrAsesorDto();
    try {
      trAsesorDto = asesorService.eliminarAsesor(idAsesor);
    } catch (ExcepcionRecursoNoEncontrado e) {
      throw new ExcepcionRecursoNoEncontrado(e.getMessage());
    } catch (ExcepcionErrorInternoDelServidor e) {
      throw new ExcepcionErrorInternoDelServidor(e.getMessage());
    }
    return new ResponseEntity<TrAsesorDto>(trAsesorDto, HttpStatus.OK);
  }

  /**
   * Actualizar asesor.
   *
   * @param idAsesor    the id asesor
   * @param trAsesorDto the tr asesor dto
   * @return the response entity
   */
  @PutMapping(produces = "application/json", value = { "asesor" })
  @ApiOperation(value = "Actualizar asesor.")
  public ResponseEntity<TrAsesorDto> actualizarAsesor(@RequestParam(required = true) long idAsesor,
      @RequestBody TrAsesorDto trAsesorDto) {
    try {
      trAsesorDto = asesorService.actualizarAsesor(idAsesor, trAsesorDto);
    } catch (ExcepcionRecursoNoEncontrado e) {
      throw new ExcepcionRecursoNoEncontrado(e.getMessage());
    } catch (ExcepcionErrorInternoDelServidor e) {
      throw new ExcepcionErrorInternoDelServidor(e.getMessage());
    }
    return new ResponseEntity<TrAsesorDto>(trAsesorDto, HttpStatus.OK);
  }

  /**
   * Actualizar parcial asesor.
   *
   * @param idAsesor    the id asesor
   * @param trAsesorDto the tr asesor dto
   * @return the response entity
   */
  @PatchMapping(produces = "application/json", value = { "asesor" })
  @ApiOperation(value = "Actualización parcial asesor.")
  public ResponseEntity<TrAsesorDto> actualizacionParcialAsesor(@RequestParam(required = true) long idAsesor,
      @RequestBody TrAsesorDto trAsesorDto) {
    try {
      trAsesorDto = asesorService.actualizacionParcialAsesor(idAsesor, trAsesorDto);
    } catch (ExcepcionRecursoNoEncontrado e) {
      throw new ExcepcionRecursoNoEncontrado(e.getMessage());
    } catch (ExcepcionErrorInternoDelServidor e) {
      throw new ExcepcionErrorInternoDelServidor(e.getMessage());
    }
    return new ResponseEntity<TrAsesorDto>(trAsesorDto, HttpStatus.OK);
  }
}
