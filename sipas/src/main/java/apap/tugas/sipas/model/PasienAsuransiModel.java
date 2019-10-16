package apap.tugas.sipas.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Table(name = "pasienAsuransi")
public class PasienAsuransiModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Size(max = 20)
    private BigInteger idPasienAsuransi;

    @ManyToOne
    @JoinColumn(name = "idPasien", referencedColumnName = "idPasien")
    private PasienModel pasien;

    @ManyToOne
    @JoinColumn(name = "idAsuransi", referencedColumnName = "idAsuransi")
    private AsuransiModel asuransi;

    public BigInteger getIdPasienAsuransi() {
        return idPasienAsuransi;
    }

    public void setIdPasienAsuransi(BigInteger idPasienAsuransi) {
        this.idPasienAsuransi = idPasienAsuransi;
    }
}
