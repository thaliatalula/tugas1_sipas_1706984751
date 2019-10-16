package apap.tugas.sipas.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name = "asuransi")
public class AsuransiModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Size(max = 20)
    private Long idAsuransi;

    @NotNull
    @Column(name = "namaAsuransi", nullable = false)
    private String namaAsuransi;

    @NotNull
    @Column(name = "jenisAsuransi", nullable = false)
    private String jenisAsuransi;

    @OneToMany(mappedBy = "asuransi", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PasienAsuransiModel> listPasienAsuransi;

    public Long getIdAsuransi() {
        return idAsuransi;
    }

    public void setIdAsuransi(Long idAsuransi) {
        this.idAsuransi = idAsuransi;
    }

    public String getNamaAsuransi() {
        return namaAsuransi;
    }

    public void setNamaAsuransi(String namaAsuransi) {
        this.namaAsuransi = namaAsuransi;
    }

    public String getJenisAsuransi() {
        return jenisAsuransi;
    }

    public void setJenisAsuransi(String jenisAsuransi) {
        this.jenisAsuransi = jenisAsuransi;
    }

    public List<PasienAsuransiModel> getListPasienAsuransi() {
        return listPasienAsuransi;
    }

    public void setListPasienAsuransi(List<PasienAsuransiModel> listPasienAsuransi) {
        this.listPasienAsuransi = listPasienAsuransi;
    }
}
