package apap.tugas.sipas.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Table(name = "diagnosisPenyakit")
public class DiagnosisPenyakitModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Size(max = 20)
    private BigInteger idPenyakit;

    @NotNull
    @Column(name = "namaPenyakit, nullable = false")
    private String namaPenyakit;

    @NotNull
    @Column(name = "kodePenyakit", nullable = false)
    private String kodePenyakit;

    public BigInteger getIdPenyakit() {
        return idPenyakit;
    }

    public void setIdPenyakit(BigInteger idPenyakit) {
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
