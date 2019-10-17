package apap.tugas.sipas.service;

import apap.tugas.sipas.model.PasienModel;
import apap.tugas.sipas.repository.PasienDb;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
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

//    @Override
//    public Optional<PasienModel> getPasienByNIKPasien(Long NIKPasien){
//        return pasienDb.findByNIKPasien(NIKPasien);
//    }


    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final SecureRandom RANDOM = new SecureRandom();

    //random string
    public static String randomString(int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; ++i) {
            sb.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return sb.toString();
    }

    @Override
    public void addPasien(PasienModel pasien){
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        String tanggal = dateFormat.format(pasien.getTglLahir());
        Integer jenisKelamin;
        if (pasien.getJenisKelamin().equals("Laki laki")){
            jenisKelamin = 1;
        }
        else{
            jenisKelamin = 2;
        }
        String kode = "2024" + tanggal + jenisKelamin + randomString(2);
        pasien.setKodePasien(kode);
        pasienDb.save(pasien);
    }
}
