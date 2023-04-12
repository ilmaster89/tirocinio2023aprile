package f2.tirocinio.ecommerce.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import f2.tirocinio.ecommerce.models.Prodotto;

@Transactional
public interface ProdottoDao extends JpaRepository<Prodotto, Integer> {

	@Modifying
	@Query(value = "update prodotti set disponibile = 0 where id in :lista", nativeQuery = true)
	Integer updateDisponibilita(List<Integer> lista);
}
