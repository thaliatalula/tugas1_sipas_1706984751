package apap.tugas.sipas.service;

import apap.tugas.sipas.model.PasienModel;

import java.util.List;
import java.util.Optional;

public interface PasienService {
    //method untuk mendapatkan semua data pasien yang tersimpan
    List<PasienModel> getPasienList();
    //method untuk menambah pasien baru
    void addPasien(PasienModel pasien);
    //method untuk melihat data pasien sesuai nik
    Optional<PasienModel> getPasienByNikPasien(String nikPasien);
    //method untuk ganti data pasien
    PasienModel changePasien(PasienModel pasienModel);
}
