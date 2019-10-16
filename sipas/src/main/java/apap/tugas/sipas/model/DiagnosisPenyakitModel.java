package apap.tugas.sipas.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name = "diagnosisPenyakit")
public class DiagnosisPenyakitModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Size(max = 20)
    private Long idPenyakit;

    @NotNull
    @Column(name = "namaPenyakit", nullable = false)
    private String namaPenyakit;

    @NotNull
    @Column(name = "kodePenyakit", nullable = false)
    private String kodePenyakit;

    @OneToMany(mappedBy = "diagnosisPenyakit", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PasienDiagnosisPenyakitModel> listPasienDiagnosisPenyakit;

    public List<PasienDiagnosisPenyakitModel> getListPasienDiagnosisPenyakit() {
        return listPasienDiagnosisPenyakit;
    }

    public void setListPasienDiagnosisPenyakit(List<PasienDiagnosisPenyakitModel> listPasienDiagnosisPenyakit) {
        this.listPasienDiagnosisPenyakit = listPasienDiagnosisPenyakit;
    }

    public Long getIdPenyakit() {
        return idPenyakit;
    }

    public void setIdPenyakit(Long idPenyakit) {
        this.idPenyakit = idPenyakit;
    }

    public String getNamaPenyakit() {
        return namaPenyakit;
    }

    public void setNamaPenyakit(String namaPenyakit) {
        this.namaPenyakit = namaPenyakit;
    }

    public String getKodePenyakit() {
        return kodePenyakit;
    }

    public void setKodePenyakit(String kodePenyakit) {
        this.kodePenyakit = kodePenyakit;
    }
}
