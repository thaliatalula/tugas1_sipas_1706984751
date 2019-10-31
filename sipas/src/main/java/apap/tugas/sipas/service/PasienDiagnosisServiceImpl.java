package apap.tugas.sipas.service;

import apap.tugas.sipas.model.PasienDiagnosisPenyakitModel;
import apap.tugas.sipas.repository.PasienDiagnosisDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PasienDiagnosisServiceImpl implements  PasienDiagnosisService{
    @Autowired
    private PasienDiagnosisDb pasienDiagnosisDb;

    @Override
    public void addPasienDiagnosis(PasienDiagnosisPenyakitModel pasienDiagnosis){
        pasienDiagnosisDb.save(pasienDiagnosis);
    }

    @Override
    public List<PasienDiagnosisPenyakitModel> getPasienJenisKelaminAndDiagnosisPenyakitIdPenyakit (Integer jenisKelamin, Long idPenyakit){
        return pasienDiagnosisDb.findByPasienJenisKelaminAndDiagnosisPenyakitIdPenyakit(jenisKelamin, idPenyakit);
    }
}
