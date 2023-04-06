package f2.tirocinio.relationships.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "professori")
public class Professore {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;

	String nome, cognome;

	@OneToMany(mappedBy = "professore")
	List<Insegnamento> insegnamenti;

	public Professore() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public List<Insegnamento> getInsegnamenti() {
		return insegnamenti;
	}

	public void setInsegnamenti(List<Insegnamento> insegnamenti) {
		this.insegnamenti = insegnamenti;
	}

}
