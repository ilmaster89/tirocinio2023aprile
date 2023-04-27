package f2.tirocinio.transaction_schedules.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import f2.tirocinio.transaction_schedules.models.Utente;

public interface UtenteDao extends JpaRepository<Utente, Integer> {

}
