package mx.com.backend.prospectos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mx.com.backend.prospectos.model.TrCodigoAutenticacionEntity;

@Repository
public interface CodigoAutenticacionRepository extends JpaRepository<TrCodigoAutenticacionEntity, Long> {
	
	
}
