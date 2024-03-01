package mx.com.backend.asesores.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import mx.com.backend.asesores.commons.Constantes;
import mx.com.backend.asesores.commons.ConstantesExcepcion;
import mx.com.backend.asesores.commons.ConstantesFormatosFecha;
import mx.com.backend.asesores.commons.EEstatusAsesor;
import mx.com.backend.asesores.dto.TrAsesorDto;
import mx.com.backend.asesores.exceptions.ExcepcionErrorInternoDelServidor;
import mx.com.backend.asesores.exceptions.ExcepcionRecursoNoEncontrado;
import mx.com.backend.asesores.model.TrAsesorEntity;
import mx.com.backend.asesores.repository.AsesorRepository;
import mx.com.backend.asesores.service.AsesorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class AsesorServiceImpl.
 */
@Service
public class AsesorServiceImpl implements AsesorService {
  
  private static final SimpleDateFormat dateFormat = new SimpleDateFormat(ConstantesFormatosFecha.FORMATO_FECHA_JSON);

  /** The model mapper. */
  @Autowired
  private ModelMapper modelMapper;

  /** The asesor repository. */
  @Autowired
  private AsesorRepository asesorRepository;

  /**
   * Crear asesor.
   *
   * @param trAsesorDto the tr asesor dto
   * @return the tr asesor dto
   */
  @Override
  @Transactional
  public TrAsesorDto crearAsesor(TrAsesorDto trAsesorDto) {
    try {
      if (trAsesorDto == Constantes.NULO) {
        throw new ExcepcionErrorInternoDelServidor(ConstantesExcepcion.MENSAJE_ERROR_RESTRICCION_DE_NEGOCIO);
      }
      Date fechaActualMx = ConstantesFormatosFecha.obtenerFechaActualMx();
      TrAsesorEntity trAsesorEntity = trAsesorDtoaTrAsesorEntity(trAsesorDto);
      trAsesorEntity.setFkIdEstatusAsesor(EEstatusAsesor.ACTIVO.getIdEstatusAsesor());
      trAsesorEntity.setFechaCreacionAsesor(fechaActualMx);
      trAsesorEntity.setFechaActualizacionAsesor(fechaActualMx);
      trAsesorEntity.setFechaEstatusAsesor(fechaActualMx);
      trAsesorEntity = asesorRepository.save(trAsesorEntity);
      trAsesorDto = trAsesorEntityaTrAsesorDto(trAsesorEntity);
    } catch (Exception e) {
      throw new ExcepcionErrorInternoDelServidor(ConstantesExcepcion.MENSAJE_ERROR_RESTRICCION_DE_NEGOCIO);
    }
    return trAsesorDto;
  }

  /**
   * Tr asesor dtoa tr asesor entity.
   *
   * @param trAsesorDto the tr asesor dto
   * @return the tr asesor entity
   */
  private TrAsesorEntity trAsesorDtoaTrAsesorEntity(TrAsesorDto trAsesorDto) {
    try {
      TrAsesorEntity trAsesorEntity = modelMapper.map(trAsesorDto, TrAsesorEntity.class);
      return trAsesorEntity;
    } catch (Exception e) {
      throw new ExcepcionErrorInternoDelServidor(ConstantesExcepcion.MENSAJE_ERROR_RESTRICCION_DE_NEGOCIO);
    }
  }

  /**
   * Tr asesor entitya tr asesor dto.
   *
   * @param trAsesorEntity the tr asesor entity
   * @return the tr asesor dto
   */
  private TrAsesorDto trAsesorEntityaTrAsesorDto(TrAsesorEntity trAsesorEntity) {
    try {
      TrAsesorDto trAsesorDto = modelMapper.map(trAsesorEntity, TrAsesorDto.class);
      trAsesorDto.setFechaCreacionAsesor(dateFormat.format(trAsesorEntity.getFechaCreacionAsesor()));
      trAsesorDto.setFechaActualizacionAsesor(dateFormat.format(trAsesorEntity.getFechaActualizacionAsesor()));
      trAsesorDto.setFechaEstatusAsesor(dateFormat.format(trAsesorEntity.getFechaEstatusAsesor()));
      return trAsesorDto;
    } catch (Exception e) {
      throw new ExcepcionErrorInternoDelServidor(ConstantesExcepcion.MENSAJE_ERROR_RESTRICCION_DE_NEGOCIO);
    }
  }

  /**
   * Obtener asesor.
   *
   * @param idAsesor the id asesor
   * @return the tr asesor dto
   */
  @Override
  @Transactional(readOnly = Constantes.TRUE)
  public TrAsesorDto obtenerAsesor(long idAsesor) {
    TrAsesorDto trAsesorDto = new TrAsesorDto();
    Optional<TrAsesorEntity> optionalTrAsesorEntity = asesorRepository.findById(idAsesor);
    if (!optionalTrAsesorEntity.isPresent()) {
      throw new ExcepcionRecursoNoEncontrado(ConstantesExcepcion.MENSAJE_RECURSO_NO_ENCONTRADO);
    }
    try {
      trAsesorDto = trAsesorEntityaTrAsesorDto(optionalTrAsesorEntity.get());
    } catch (Exception e) {
      throw new ExcepcionErrorInternoDelServidor(ConstantesExcepcion.MENSAJE_ERROR_RESTRICCION_DE_NEGOCIO);
    }
    return trAsesorDto;
  }

  /**
   * Eliminar asesor.
   *
   * @param idAsesor the id asesor
   * @return the tr asesor dto
   */
  @Override
  @Transactional()
  public TrAsesorDto eliminarAsesor(long idAsesor) {
    TrAsesorDto trAsesorDto = new TrAsesorDto();
    Optional<TrAsesorEntity> optionalTrAsesorEntity = asesorRepository.findById(idAsesor);
    if (!optionalTrAsesorEntity.isPresent()) {
      throw new ExcepcionRecursoNoEncontrado(ConstantesExcepcion.MENSAJE_RECURSO_NO_ENCONTRADO);
    }
    try {
      asesorRepository.delete(optionalTrAsesorEntity.get());
    } catch (Exception e) {
      throw new ExcepcionErrorInternoDelServidor(ConstantesExcepcion.MENSAJE_ERROR_RESTRICCION_DE_NEGOCIO);
    }
    return trAsesorDto;
  }

  /**
   * Actualizar asesor.
   *
   * @param idAsesor    the id asesor
   * @param trAsesorDto the tr asesor dto
   * @return the tr asesor dto
   */
  @Override
  @Transactional()
  public TrAsesorDto actualizarAsesor(long idAsesor, TrAsesorDto trAsesorDto) {
    Optional<TrAsesorEntity> optionalTrAsesorEntity = asesorRepository.findById(idAsesor);
    if (!optionalTrAsesorEntity.isPresent()) {
      throw new ExcepcionRecursoNoEncontrado(ConstantesExcepcion.MENSAJE_RECURSO_NO_ENCONTRADO);
    }
    try {
      TrAsesorEntity trAsesorEntityNuevo = trAsesorDtoaTrAsesorEntity(trAsesorDto);
      trAsesorEntityNuevo.setIdAsesor(idAsesor);
      trAsesorDto = trAsesorEntityaTrAsesorDto(asesorRepository.save(trAsesorEntityNuevo));
    } catch (Exception e) {
      throw new ExcepcionErrorInternoDelServidor(ConstantesExcepcion.MENSAJE_ERROR_RESTRICCION_DE_NEGOCIO);
    }
    return trAsesorDto;
  }

  /**
   * Actualizacion parcial asesor.
   *
   * @param idAsesor    the id asesor
   * @param trAsesorDto the tr asesor dto
   * @return the tr asesor dto
   */
  @Override
  @Transactional()
  public TrAsesorDto actualizacionParcialAsesor(long idAsesor, TrAsesorDto trAsesorDto) {
    Optional<TrAsesorEntity> optionalTrAsesorEntity = asesorRepository.findById(idAsesor);
    if (!optionalTrAsesorEntity.isPresent()) {
      throw new ExcepcionRecursoNoEncontrado(ConstantesExcepcion.MENSAJE_RECURSO_NO_ENCONTRADO);
    }
    try {
      TrAsesorEntity trAsesorEntity = optionalTrAsesorEntity.get();
      trAsesorEntity.setContraseniaAsesor(trAsesorDto.getContraseniaAsesor());
      trAsesorEntity.setFechaActualizacionAsesor(ConstantesFormatosFecha.obtenerFechaActualMx());
      trAsesorDto = trAsesorEntityaTrAsesorDto(asesorRepository.save(trAsesorEntity));
    } catch (Exception e) {
      throw new ExcepcionErrorInternoDelServidor(ConstantesExcepcion.MENSAJE_ERROR_RESTRICCION_DE_NEGOCIO);
    }
    return trAsesorDto;
  }
}
