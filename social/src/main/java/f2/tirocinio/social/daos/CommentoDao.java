package f2.tirocinio.social.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import f2.tirocinio.social.models.Commento;

public interface CommentoDao extends JpaRepository<Commento, Integer> {

}
