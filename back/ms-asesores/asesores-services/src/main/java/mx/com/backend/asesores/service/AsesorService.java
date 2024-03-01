package mx.com.backend.asesores.service;

import mx.com.backend.asesores.dto.TrAsesorDto;

/**
 * The Class AsesorService.
 */
public interface AsesorService {

  /**
   * Crear asesor.
   *
   * @param trAsesorDto the tr asesor dto
   * @return the asesor dto
   */
  public TrAsesorDto crearAsesor(TrAsesorDto trAsesorDto);

  /**
   * Obtener asesor.
   *
   * @param idAsesor the id asesor
   * @return the tr asesor dto
   */
  public TrAsesorDto obtenerAsesor(long idAsesor);

  /**
   * Eliminar asesor.
   *
   * @param idAsesor the id asesor
   * @return the tr asesor dto
   */
  public TrAsesorDto eliminarAsesor(long idAsesor);

  /**
   * Actualizar asesor.
   *
   * @param idAsesor    the id asesor
   * @param trAsesorDto the tr asesor dto
   * @return the tr asesor dto
   */
  public TrAsesorDto actualizarAsesor(long idAsesor, TrAsesorDto trAsesorDto);

  /**
   * Actualizacion parcial asesor.
   *
   * @param idAsesor    the id asesor
   * @param trAsesorDto the tr asesor dto
   * @return the tr asesor dto
   */
  public TrAsesorDto actualizacionParcialAsesor(long idAsesor, TrAsesorDto trAsesorDto);

}
