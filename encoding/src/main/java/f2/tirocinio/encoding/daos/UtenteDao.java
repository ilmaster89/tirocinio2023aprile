package f2.tirocinio.encoding.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import f2.tirocinio.encoding.models.Utente;

public interface UtenteDao extends JpaRepository<Utente, Integer> {

	@Query(value = "select * from utenti where username = :username", nativeQuery = true)
	Utente userLogin(String username);
}
