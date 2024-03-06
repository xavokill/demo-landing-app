package mx.com.backend.prospectos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mx.com.backend.prospectos.model.TrProspectoEntity;

@Repository
public interface ProspectoRepository extends JpaRepository<TrProspectoEntity, Long>{

}
