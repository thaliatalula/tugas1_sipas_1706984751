package apap.tugas.sipas.service;

import apap.tugas.sipas.model.PasienModel;

import java.util.List;

public interface PasienService {
    //method untuk mendapatkan semua data pasien yang tersimpan
    List<PasienModel> getPasienList();

}
