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
    @NotNull
    @Size(max = 20)
    private BigInteger idEmergencyContact;

    @NotNull
    @Column(name = "NIKEmergencyContact", nullable = false)
    private String NIKEmergencyContact;

    @NotNull
    @Column(name = "namaEmergencyContact", nullable = false)
    private String namaEmergencyContact;

    public BigInteger getIdEmergencyContact() {
        return idEmergencyContact;
    }

    public void setIdEmergencyContact(BigInteger idEmergencyContact) {
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

    @NotNull
    @Column(name = "noTelp", nullable = false)
    private String noTelp;
}
