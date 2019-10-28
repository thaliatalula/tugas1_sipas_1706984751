package apap.tugas.sipas.service;

import apap.tugas.sipas.model.PasienModel;
import apap.tugas.sipas.repository.PasienDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class PasienServiceImpl implements PasienService{
    @Autowired
    private PasienDb pasienDb;

    @Override
    public List<PasienModel> getPasienList() {
        return pasienDb.findAll();
    }

    @Override
    public void addPasien(PasienModel pasien){
        pasienDb.save(pasien);
    }

    @Override
    public Optional<PasienModel> getPasienByNikPasien(String nikPasien){
        return pasienDb.findByNikPasien(nikPasien);
    }

    @Override
    public PasienModel changePasien(PasienModel pasienModel){
        PasienModel targetPasien = pasienDb.findByNikPasien(pasienModel.getNikPasien()).get();
        Date tglSblm = targetPasien.getTglLahir();

        targetPasien.setNamaPasien(pasienModel.getNamaPasien());
        targetPasien.setJenisKelamin(pasienModel.getJenisKelamin());
        targetPasien.setTglLahir(pasienModel.getTglLahir());
        targetPasien.setTempatLahir(pasienModel.getTempatLahir());
        if (tglSblm != pasienModel.getTglLahir()){
            targetPasien.buatKode(pasienModel);
        }

        pasienDb.save(targetPasien);
        return targetPasien;
    }
}
