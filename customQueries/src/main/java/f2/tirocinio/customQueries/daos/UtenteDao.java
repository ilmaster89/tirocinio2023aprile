package f2.tirocinio.customQueries.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import f2.tirocinio.customQueries.models.Utente;

public interface UtenteDao extends JpaRepository<Utente, Integer> {

	@Query(value = "select * from utenti where username = :username and password = :password and attivo = 1", nativeQuery = true)
	Utente userLogin(String username, @Param("password") String myPassword);
}
