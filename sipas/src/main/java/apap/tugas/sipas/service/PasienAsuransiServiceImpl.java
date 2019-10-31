package apap.tugas.sipas.service;

import apap.tugas.sipas.model.PasienAsuransiModel;
import apap.tugas.sipas.repository.PasienAsuransiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PasienAsuransiServiceImpl implements PasienAsuransiService{
    @Autowired
    private PasienAsuransiDb pasienAsuransiDb;

    @Override
    public List<PasienAsuransiModel> getAsuransiIdAsuransi(Long idAsuransi){
        return pasienAsuransiDb.findByAsuransiIdAsuransi(idAsuransi);
    }
}
