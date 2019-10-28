package apap.tugas.sipas.service;

import apap.tugas.sipas.model.DiagnosisPenyakitModel;
import apap.tugas.sipas.repository.DiagnosisPenyakitDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DiagnosisPenyakitServiceImpl implements DiagnosisPenyakitService{
    @Autowired
    private DiagnosisPenyakitDb diagnosisPenyakitDb;

    @Override
    public List<DiagnosisPenyakitModel> getDiagnosisList(){
        return diagnosisPenyakitDb.findAll();
    }

    @Override
    public Optional<DiagnosisPenyakitModel> getDiagnosisByIdPenyakit(Long idPenyakit){
        return diagnosisPenyakitDb.findByIdPenyakit(idPenyakit);
    }

    @Override
    public void addDiagnosisPenyakit(DiagnosisPenyakitModel diagnosisPenyakit){
        diagnosisPenyakitDb.save(diagnosisPenyakit);
    }
}
