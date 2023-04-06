package f2.tirocinio.relationships.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import f2.tirocinio.relationships.models.Studente;

public interface StudenteDao extends JpaRepository<Studente, Integer> {

}
