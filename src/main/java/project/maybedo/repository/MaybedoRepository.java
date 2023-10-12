package project.maybedo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.maybedo.domain.Maybedo;

public interface MaybedoRepository extends JpaRepository<Maybedo, Integer> {
}
