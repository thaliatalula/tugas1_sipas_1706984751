package apap.tugas.sipas.repository;

import apap.tugas.sipas.model.PasienDiagnosisPenyakitModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PasienDiagnosisDb extends JpaRepository<PasienDiagnosisPenyakitModel, Long> {
    List<PasienDiagnosisPenyakitModel> findByPasienJenisKelaminAndDiagnosisPenyakitIdPenyakit (Integer jenisKelamin, Long idPenyakit);
}
