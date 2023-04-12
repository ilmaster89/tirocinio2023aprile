package f2.tirocinio.ecommerce.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import f2.tirocinio.ecommerce.models.Acquisto;

public interface AcquistoDao extends JpaRepository<Acquisto, Integer> {

}
