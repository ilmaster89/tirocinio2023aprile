package f2.tirocinio.relationships.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "corsi")
public class Corso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;

	@Column(name = "nome_corso")
	String nomeCorso;

	@OneToMany(mappedBy = "corso")
	List<Studente> studenti;

	@OneToMany(mappedBy = "corso")
	List<Insegnamento> insegnamenti;

	public Corso() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeCorso() {
		return nomeCorso;
	}

	public void setNomeCorso(String nomeCorso) {
		this.nomeCorso = nomeCorso;
	}

	public List<Studente> getStudenti() {
		return studenti;
	}

	public void setStudenti(List<Studente> studenti) {
		this.studenti = studenti;
	}

	public List<Insegnamento> getInsegnamenti() {
		return insegnamenti;
	}

	public void setInsegnamenti(List<Insegnamento> insegnamenti) {
		this.insegnamenti = insegnamenti;
	}

}
