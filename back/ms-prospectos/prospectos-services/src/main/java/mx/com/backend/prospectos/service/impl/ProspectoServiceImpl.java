package mx.com.backend.prospectos.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.backend.prospectos.commons.ConstantesFormatosFecha;
import mx.com.backend.prospectos.commons.Constantes;
import mx.com.backend.prospectos.commons.ConstantesExcepcion;
import mx.com.backend.prospectos.exceptions.ExcepcionErrorInternoDelServidor;
import mx.com.backend.prospectos.exceptions.ExcepcionReglaDeNegocio;
import mx.com.backend.prospectos.model.TrProspectoEntity;
import mx.com.backend.prospectos.repository.AsesorRepository;
import mx.com.backend.prospectos.repository.ProspectoRepository;
import mx.com.backend.prospectos.dto.TrProspectoDTO;
import mx.com.backend.prospectos.service.ProspectoService;

@Service
public class ProspectoServiceImpl implements ProspectoService {

  private static final SimpleDateFormat dateFormat = new SimpleDateFormat(ConstantesFormatosFecha.FORMATO_FECHA_JSON);

  /** The model mapper. */
  @Autowired
  private ModelMapper modelMapper;

  /** The asesor repository. */
  @Autowired
  private AsesorRepository asesorRepository;

  @Autowired
  private ProspectoRepository prospectoRepository;

  /**
   * Crear prospecto.
   *
   * @param trProspectoDto the tr prospecto dto
   * @return the tr prospecto DTO
   */
  @Override
  public TrProspectoDTO crearProspecto(TrProspectoDTO trProspectoDto) {
    try {
      if (trProspectoDto == Constantes.NULO) {
        throw new ExcepcionErrorInternoDelServidor(ConstantesExcepcion.MENSAJE_ERROR_RESTRICCION_DE_NEGOCIO);
      }
      Long idAsesor = asesorRepository.obtenerIdAsesorPorNumeroEmpleado(trProspectoDto.getNumeroEmpleadoAsesor());
      if (idAsesor != null) {
        Date fechaActualMx = ConstantesFormatosFecha.obtenerFechaActualMx();
        TrProspectoEntity trProspectoEntity = trProspectoDtoaTrProspectoEntity(trProspectoDto);
        trProspectoEntity.setFechaCreacionProspecto(fechaActualMx);
        trProspectoEntity.setFkIdAsesor(idAsesor);
        trProspectoEntity = prospectoRepository.save(trProspectoEntity);
        trProspectoDto = trProspectoEntityaTrPorspectoDto(trProspectoEntity, trProspectoDto.getNumeroEmpleadoAsesor());
      } else {
        throw new ExcepcionReglaDeNegocio("Error al procesar el numero de empleado.");
      }
    } catch (Exception e) {
      throw new ExcepcionErrorInternoDelServidor(ConstantesExcepcion.MENSAJE_ERROR_RESTRICCION_DE_NEGOCIO);
    }
    return trProspectoDto;
  }

  private TrProspectoEntity trProspectoDtoaTrProspectoEntity(TrProspectoDTO trProspectoDto) {
    try {
      modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
      TrProspectoEntity trAsesorEntity = modelMapper.map(trProspectoDto, TrProspectoEntity.class);
      return trAsesorEntity;
    } catch (Exception e) {
      throw new ExcepcionErrorInternoDelServidor(ConstantesExcepcion.MENSAJE_ERROR_RESTRICCION_DE_NEGOCIO);
    }
  }

  private TrProspectoDTO trProspectoEntityaTrPorspectoDto(TrProspectoEntity trProspectoEntity, String numeroEmpleadoAsesor) {
    try {
      modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
      TrProspectoDTO trProspectoDto = modelMapper.map(trProspectoEntity, TrProspectoDTO.class);
      trProspectoDto.setFechaCreacionProspecto(dateFormat.format(trProspectoEntity.getFechaCreacionProspecto()));
      trProspectoDto.setNumeroEmpleadoAsesor(numeroEmpleadoAsesor);
      return trProspectoDto;
    } catch (Exception e) {
      throw new ExcepcionErrorInternoDelServidor(ConstantesExcepcion.MENSAJE_ERROR_RESTRICCION_DE_NEGOCIO);
    }
  }

}
