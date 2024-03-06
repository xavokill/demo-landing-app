package mx.com.backend.prospectos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import mx.com.backend.prospectos.model.TrCodigoAutenticacionEntity;

/**
 * The Interface CodigoAutenticacionRepository.
 */
@Repository
public interface CodigoAutenticacionRepository extends JpaRepository<TrCodigoAutenticacionEntity, Long> {

  /**
   * Obtener codigo por curp numero empleado.
   *
   * @param fkIdAsesor the fk id asesor
   * @param curp       the curp
   * @return the string
   */
  @Query(value = "SELECT CODIGO_AUTENTICACION FROM TR_CODIGO_AUTENTICACION WHERE "
      + "FK_ID_ASESOR = :fkIdAsesor AND CURP = :curp ORDER BY FECHA_CREACION_CODIGO_AUTENTICACION "
      + "DESC FETCH FIRST 1 ROWS ONLY", nativeQuery = true)
  String obtenerCodigoPorCurpNumeroEmpleado(@Param("fkIdAsesor") Long fkIdAsesor, @Param("curp") String curp);

}
