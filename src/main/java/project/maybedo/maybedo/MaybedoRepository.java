package project.maybedo.maybedo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MaybedoRepository extends JpaRepository<Maybedo, Integer> {
}
