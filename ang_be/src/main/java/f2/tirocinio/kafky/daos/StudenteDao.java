package f2.tirocinio.kafky.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import f2.tirocinio.kafky.models.Studente;

public interface StudenteDao extends JpaRepository<Studente, Integer> {

}
