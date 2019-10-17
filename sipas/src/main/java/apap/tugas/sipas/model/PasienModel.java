package apap.tugas.sipas.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pasien")
public class PasienModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Size(max = 20)
    private Long idPasien;

    @NotNull
    @Column(name = "namaPasien", nullable = false)
    private String namaPasien;

    @NotNull
    @Column(name = "kodePasien", nullable = false)
    private String kodePasien;

    @NotNull
    @Column(name = "NIKPasien", nullable = false)
    private String NIKPasien;

    @NotNull
    @Column(name = "tglLahir", nullable = false)
    private Date tglLahir;

    @NotNull
    @Column(name = "tempatLahir", nullable = false)
    private String tempatLahir;

    @NotNull
    @Column(name = "jenisKelamin", nullable = false)
    private String jenisKelamin;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idEmergencyContact", referencedColumnName = "idEmergencyContact", nullable = false)
    private EmergencyContactModel emergencyContact;

    @OneToMany(mappedBy = "pasien", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PasienDiagnosisPenyakitModel> listPasienDiagnosisPenyakit;

    @OneToMany(mappedBy = "pasien", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PasienAsuransiModel> listPasienAsuransi;

    public Long getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(Long idPasien) {
        this.idPasien = idPasien;
    }

    public String getNamaPasien() {
        return namaPasien;
    }

    public void setNamaPasien(String namaPasien) {
        this.namaPasien = namaPasien;
    }

    public String getKodePasien() {
        return kodePasien;
    }

    public void setKodePasien(String kodePasien) {
        this.kodePasien = kodePasien;
    }

    public String getNIKPasien() {
        return NIKPasien;
    }

    public void setNIKPasien(String NIKPasien) {
        this.NIKPasien = NIKPasien;
    }

    public Date getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(Date tglLahir) {
        this.tglLahir = tglLahir;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public EmergencyContactModel getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(EmergencyContactModel emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public List<PasienDiagnosisPenyakitModel> getListPasienDiagnosisPenyakit() {
        return listPasienDiagnosisPenyakit;
    }

    public void setListPasienDiagnosisPenyakit(List<PasienDiagnosisPenyakitModel> listPasienDiagnosisPenyakit) {
        this.listPasienDiagnosisPenyakit = listPasienDiagnosisPenyakit;
    }

    public List<PasienAsuransiModel> getListPasienAsuransi() {
        return listPasienAsuransi;
    }

    public void setListPasienAsuransi(List<PasienAsuransiModel> listPasienAsuransi) {
        this.listPasienAsuransi = listPasienAsuransi;
    }
}
