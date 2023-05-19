package f2.tirocinio.docky.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import f2.tirocinio.docky.models.Utente;

public interface UtenteDao extends JpaRepository<Utente, Integer> {

}
