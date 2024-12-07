package apap.tugas.sipas.service;

import apap.tugas.sipas.model.PasienAsuransiModel;

import java.util.List;

public interface PasienAsuransiService {
    List<PasienAsuransiModel> getAsuransiIdAsuransi(Long idAsuransi);
}
