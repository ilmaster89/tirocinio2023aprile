package f2.tirocinio.relationships.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import f2.tirocinio.relationships.models.Professore;

public interface ProfessoreDao extends JpaRepository<Professore, Integer> {

}
