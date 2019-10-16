package apap.tugas.sipas.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Table(name = "pasienDiagnosisPenyakit")
public class PasienDiagnosisPenyakitModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Size(max = 20)
    private BigInteger idPasienDiagnosisPenyakit;

    @ManyToOne
    @JoinColumn(name = "idPasien", referencedColumnName = "idPasien")
    private PasienModel pasien;

    @ManyToOne
    @JoinColumn(name = "idDiagnosisPenyakit", referencedColumnName = "idDiagnosisPenyakit")
    private DiagnosisPenyakitModel diagnosisPenyakit;

    public BigInteger getIdPasienDiagnosisPenyakit() {
        return idPasienDiagnosisPenyakit;
    }

    public void setIdPasienDiagnosisPenyakit(BigInteger idPasienDiagnosisPenyakit) {
        this.idPasienDiagnosisPenyakit = idPasienDiagnosisPenyakit;
    }
}
