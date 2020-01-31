package lavoro;

public class Curriculum extends Inserzione implements Comparable<Curriculum>{

	private String codiceFiscale;
	private String nome;
	private String cognome;
	private String dataNascita;
	private String residenza;
	
	public Curriculum(String codiceFiscale, String nome, String cognome, String dataNascita,String residenza) {
		this.codiceFiscale=codiceFiscale;
		this.nome=nome;
		this.cognome=cognome;
		this.dataNascita=dataNascita;
		this.residenza=residenza;
	}
	
	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getDataNascita() {
		return dataNascita;
	}

	public String getResidenza() {
		return residenza;
	}
	
	public void aggiungiCompetenzaPosseduta(String competenza, int livello){
		super.aggiungiCompetenza(competenza, livello);
	}

	public int compareTo(Curriculum altro) {

		String questo=""+this.getCognome()+""+this.getNome()+""+this.residenza;
		return questo.compareTo(""+altro.getCognome()+""+altro.getNome()+""+altro.getResidenza());
	}

}
