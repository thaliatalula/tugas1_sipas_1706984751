package apap.tugas.sipas.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Table(name = "emergencyContact")
public class EmergencyContactModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmergencyContact;

    @NotNull
    @Column(name = "NIKEmergencyContact", nullable = false)
    private String NIKEmergencyContact;

    @NotNull
    @Column(name = "namaEmergencyContact", nullable = false)
    private String namaEmergencyContact;

    @NotNull
    @Column(name = "noTelp", nullable = false)
    private String noTelp;

    @OneToOne(mappedBy = "emergencyContact", cascade = CascadeType.ALL)
    private PasienModel pasien;

    public Long getIdEmergencyContact() {
        return idEmergencyContact;
    }

    public void setIdEmergencyContact(Long idEmergencyContact) {
        this.idEmergencyContact = idEmergencyContact;
    }

    public String getNIKEmergencyContact() {
        return NIKEmergencyContact;
    }

    public void setNIKEmergencyContact(String NIKEmergencyContact) {
        this.NIKEmergencyContact = NIKEmergencyContact;
    }

    public String getNamaEmergencyContact() {
        return namaEmergencyContact;
    }

    public void setNamaEmergencyContact(String namaEmergencyContact) {
        this.namaEmergencyContact = namaEmergencyContact;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public PasienModel getPasien() {
        return pasien;
    }

    public void setPasien(PasienModel pasien) {
        this.pasien = pasien;
    }
}
