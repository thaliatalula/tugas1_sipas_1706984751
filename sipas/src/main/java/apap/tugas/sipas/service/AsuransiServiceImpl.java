package apap.tugas.sipas.service;

import apap.tugas.sipas.model.AsuransiModel;
import apap.tugas.sipas.repository.AsuransiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AsuransiServiceImpl implements AsuransiService {
    @Autowired
    private AsuransiDb asuransiDb;

    @Override
    public List<AsuransiModel> getAsuransiList(){
        return asuransiDb.findAll();
    }
}
