package apap.tugas.sipas.service;

import apap.tugas.sipas.model.PasienDiagnosisPenyakitModel;

import java.util.List;

public interface PasienDiagnosisService {
    void addPasienDiagnosis(PasienDiagnosisPenyakitModel pasienDiagnosis);
    List<PasienDiagnosisPenyakitModel> getPasienJenisKelaminAndDiagnosisPenyakitIdPenyakit (Integer jenisKelamin, Long idPenyakit);
}
