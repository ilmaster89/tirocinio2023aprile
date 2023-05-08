package f2.tirocinio.xlxHandling.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import f2.tirocinio.xlxHandling.models.Studente;

public interface StudenteDao extends JpaRepository<Studente, Integer> {

	@Query(value = "select * from studenti where corso_id = :corsoId", nativeQuery = true)
	List<Studente> studentiCorso(Integer corsoId);

}
