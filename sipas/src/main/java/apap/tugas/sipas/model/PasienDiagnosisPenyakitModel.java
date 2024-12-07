package apap.tugas.sipas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name = "pasienDiagnosisPenyakit")
public class PasienDiagnosisPenyakitModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPasienDiagnosisPenyakit;

    @NotNull
    private Date tanggalDiagnosis;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idPasien", referencedColumnName = "idPasien")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PasienModel pasien;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idPenyakit", referencedColumnName = "idPenyakit")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private DiagnosisPenyakitModel diagnosisPenyakit;

    public PasienModel getPasien() {
        return pasien;
    }

    public void setPasien(PasienModel pasien) {
        this.pasien = pasien;
    }

    public DiagnosisPenyakitModel getDiagnosisPenyakit() {
        return diagnosisPenyakit;
    }

    public void setDiagnosisPenyakit(DiagnosisPenyakitModel diagnosisPenyakit) {
        this.diagnosisPenyakit = diagnosisPenyakit;
    }

    public Long getIdPasienDiagnosisPenyakit() {
        return idPasienDiagnosisPenyakit;
    }

    public void setIdPasienDiagnosisPenyakit(Long idPasienDiagnosisPenyakit) {
        this.idPasienDiagnosisPenyakit = idPasienDiagnosisPenyakit;
    }

    public Date getTanggalDiagnosis() {
        return tanggalDiagnosis;
    }

    public void setTanggalDiagnosis(Date tanggalDiagnosis) {
        this.tanggalDiagnosis = tanggalDiagnosis;
    }


}
