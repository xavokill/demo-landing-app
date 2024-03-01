package mx.com.backend.asesores.repository;

import mx.com.backend.asesores.model.TrAsesorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The Interface AsesorRepository.
 */
@Repository
public interface AsesorRepository extends JpaRepository<TrAsesorEntity, Long> {

}
