package apap.tugas.sipas.repository;

import apap.tugas.sipas.model.PasienModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface PasienDb extends JpaRepository<PasienModel, BigInteger> {
}
