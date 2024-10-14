package repositories;

import entities.DnaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DNAStorageRepository extends JpaRepository<DnaEntity, Long> {
    long countByMutantFlag(boolean isMutant);
}