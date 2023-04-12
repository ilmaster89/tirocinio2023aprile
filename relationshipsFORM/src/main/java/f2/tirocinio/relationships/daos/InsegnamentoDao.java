package f2.tirocinio.relationships.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import f2.tirocinio.relationships.models.Insegnamento;

public interface InsegnamentoDao extends JpaRepository<Insegnamento, Integer> {

}
