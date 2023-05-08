package f2.tirocinio.account.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import f2.tirocinio.account.models.Utente;

public interface UtenteDao extends JpaRepository<Utente, Integer> {

	@Query(value = "select * from utenti where uuid = :uuid and attivo = 0", nativeQuery = true)
	public Utente utenteByUUID(String uuid);

}
