package f2.tirocinio.school.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import f2.tirocinio.school.models.Lezione;

public interface LezioneDao extends JpaRepository<Lezione, Integer> {

}
