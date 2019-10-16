package apap.tugas.sipas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    private Long idPasienAsuransi;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idPasien", referencedColumnName = "idPasien")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PasienModel pasien;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idAsuransi", referencedColumnName = "idAsuransi")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private AsuransiModel asuransi;

    public Long getIdPasienAsuransi() {
        return idPasienAsuransi;
    }

    public void setIdPasienAsuransi(Long idPasienAsuransi) {
        this.idPasienAsuransi = idPasienAsuransi;
    }

    public PasienModel getPasien() {
        return pasien;
    }

    public void setPasien(PasienModel pasien) {
        this.pasien = pasien;
    }

    public AsuransiModel getAsuransi() {
        return asuransi;
    }

    public void setAsuransi(AsuransiModel asuransi) {
        this.asuransi = asuransi;
    }
}
