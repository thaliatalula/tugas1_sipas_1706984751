package apap.tugas.sipas.repository;

import apap.tugas.sipas.model.DiagnosisPenyakitModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiagnosisPenyakitDb extends JpaRepository<DiagnosisPenyakitModel, Long> {
    Optional<DiagnosisPenyakitModel> findByIdPenyakit(Long idPenyakit);
    Optional<DiagnosisPenyakitModel> findById(Long idPenyakit);
}
