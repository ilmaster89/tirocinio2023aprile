package f2.tirocinio.relationships.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import f2.tirocinio.relationships.models.Corso;

public interface CorsoDao extends JpaRepository<Corso, Integer> {

}
