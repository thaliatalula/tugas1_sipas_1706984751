package apap.tugas.sipas.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pasien")
public class PasienModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPasien;

    @NotNull
    @Column(name = "namaPasien", nullable = false)
    private String namaPasien;

    @NotNull
    @Column(name = "kodePasien", nullable = false)
    private String kodePasien;

    @NotNull
    @Column(name = "nikPasien", nullable = false)
    private String nikPasien;

    @NotNull
    @Column(name = "tglLahir", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tglLahir;

    @NotNull
    @Column(name = "tempatLahir", nullable = false)
    private String tempatLahir;

    @NotNull
    @Column(name = "jenisKelamin", nullable = false)
    private Integer jenisKelamin;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idEmergencyContact", referencedColumnName = "idEmergencyContact", nullable = false)
    private EmergencyContactModel emergencyContact;

    @OneToMany(mappedBy = "pasien", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PasienDiagnosisPenyakitModel> listPasienDiagnosisPenyakit;

    @OneToMany(mappedBy = "pasien", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PasienAsuransiModel> listPasienAsuransi;

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final SecureRandom RANDOM = new SecureRandom();

    //random string
    public static String randomString(int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; ++i) {
            sb.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return sb.toString();
    }

    public void buatKode(PasienModel pasien) {
        String pattern = "ddMMyyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        String tanggal = dateFormat.format(pasien.getTglLahir());
        Integer jenisKelamin = pasien.getJenisKelamin();
        String kode = "2024" + tanggal + jenisKelamin + randomString(2);
        pasien.setKodePasien(kode);
    }

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

    public String getNikPasien() {
        return nikPasien;
    }

    public void setNikPasien(String nikPasien) {
        this.nikPasien = nikPasien;
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

    public Integer getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(Integer jenisKelamin) {
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
