package f2.tirocinio.ecommerce.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import f2.tirocinio.ecommerce.models.Prodotto;

public interface ProdottoDao extends JpaRepository<Prodotto, Integer> {

}
