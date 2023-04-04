package f2.tirocinio.database.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import f2.tirocinio.database.models.Studente;

public interface StudenteDao extends JpaRepository<Studente, Integer> {

}
