package f2.tirocinio.nuovo_arrivato.classes;

public class Libro {

	String titolo, autore;
	Integer pagine;
	Double prezzo;

	public Libro() {
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getAutore() {
		return autore;
	}

	public void setAutore(String autore) {
		this.autore = autore;
	}

	public Integer getPagine() {
		return pagine;
	}

	public void setPagine(Integer pagine) {
		this.pagine = pagine;
	}

	public Double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}

	@Override
	public String toString() {
		return this.titolo + " di " + this.autore + ", " + this.pagine + " pagine, prezzo €" + this.prezzo;
	}
}
