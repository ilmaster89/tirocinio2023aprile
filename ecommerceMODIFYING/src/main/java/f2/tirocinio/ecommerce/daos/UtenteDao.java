package f2.tirocinio.ecommerce.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import f2.tirocinio.ecommerce.models.Utente;

public interface UtenteDao extends JpaRepository<Utente, Integer> {

	@Query(value = "select * from utenti where username = :username and password = :password", nativeQuery = true)
	Utente userLogin(String username, String password);

}
