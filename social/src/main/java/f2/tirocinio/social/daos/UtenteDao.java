package f2.tirocinio.social.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import f2.tirocinio.social.models.Utente;

@Transactional
public interface UtenteDao extends JpaRepository<Utente, Integer> {

	@Query(value = "select * from utenti where username = :username", nativeQuery = true)
	Utente userLogin(String username);

	@Modifying
	@Query(value = "update utenti set attivo = 1, fine_ban = null where fine_ban is not null and fine_ban < :now", nativeQuery = true)
	void reactivateUsers(Long now);
}
