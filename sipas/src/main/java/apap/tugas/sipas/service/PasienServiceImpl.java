package apap.tugas.sipas.service;

import apap.tugas.sipas.model.PasienModel;
import apap.tugas.sipas.repository.PasienDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PasienServiceImpl implements PasienService{
    @Autowired
    private PasienDb pasienDb;

    @Override
    public List<PasienModel> getPasienList() {
        return pasienDb.findAll();
    }
}
