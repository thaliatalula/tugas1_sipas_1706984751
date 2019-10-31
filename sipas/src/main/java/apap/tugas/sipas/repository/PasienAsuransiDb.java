package apap.tugas.sipas.repository;

import apap.tugas.sipas.model.PasienAsuransiModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PasienAsuransiDb extends JpaRepository<PasienAsuransiModel, Long> {
    List<PasienAsuransiModel> findByAsuransiIdAsuransi(Long idAsuransi);
}
