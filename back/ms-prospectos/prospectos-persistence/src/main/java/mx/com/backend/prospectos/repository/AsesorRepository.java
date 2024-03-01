package mx.com.backend.prospectos.repository;

import mx.com.backend.prospectos.model.TrAsesorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * The Interface AsesorRepository.
 */
@Repository
public interface AsesorRepository extends JpaRepository<TrAsesorEntity, Long> {

	@Query(value = "SELECT ID_ASESOR FROM TR_ASESOR WHERE NUMERO_EMPLEADO_ASESOR = :numeroEmpleadoAsesor", nativeQuery = true)
	Long obtenerIdAsesorPorNumeroEmpleado(@Param("numeroEmpleadoAsesor") String numeroEmpleadoAsesor);

}
