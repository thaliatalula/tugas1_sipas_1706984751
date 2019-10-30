package apap.tugas.sipas.repository;

import apap.tugas.sipas.model.PasienModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PasienDb extends JpaRepository<PasienModel, Long> {
    Optional<PasienModel> findByNikPasien(String nikPasien);
//    List<PasienModel> findByDiagnosisPenyakitIdPenyakit(Long idPenyakit);
}
