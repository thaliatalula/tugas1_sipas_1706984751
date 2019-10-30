package apap.tugas.sipas.service;

import apap.tugas.sipas.model.DiagnosisPenyakitModel;

import java.util.List;
import java.util.Optional;

public interface DiagnosisPenyakitService {
    //method untuk mendapatkan semua data diagnosis penyakit yang tersimpan
    List<DiagnosisPenyakitModel> getDiagnosisList();
    //method untuk melihat data diagnosis penyakit sesuai id
    Optional<DiagnosisPenyakitModel> getDiagnosisByIdPenyakit(Long idPenyakit);
//    Optional<DiagnosisPenyakitModel> getDiagnosisByIdDiagnosis(Long idDiagnosis);
    //method untuk menambah diagnosis penyakit baru
    void addDiagnosisPenyakit(DiagnosisPenyakitModel diagnosisPenyakit);
    //method untuk menghapus diagnosis penyakit
    void deleteDiagnosisPenyakit(DiagnosisPenyakitModel diagnosisPenyakit);

}
