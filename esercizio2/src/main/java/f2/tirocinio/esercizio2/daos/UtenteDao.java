package f2.tirocinio.esercizio2.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import f2.tirocinio.esercizio2.models.Utente;

public interface UtenteDao extends JpaRepository<Utente, Integer> {

	@Query(value = "select * from utenti where attivo = 1", nativeQuery = true)
	List<Utente> utentiAttivi();
}
