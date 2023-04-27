package f2.tirocinio.transaction_schedules.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import f2.tirocinio.transaction_schedules.models.Studente;

@Transactional(rollbackFor = Exception.class)
public interface StudenteDao extends JpaRepository<Studente, Integer> {

	@Modifying
	@Query(value = "update studenti set attivo = 0 where id in :list", nativeQuery = true)
	void ritiraStudenti(List<Integer> list);

	@Query(value = "select * from studenti where attivo = 1", nativeQuery = true)
	List<Studente> studentiAttivi();
}
