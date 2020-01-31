package lavoro;

public class OffertaDiLavoro extends Inserzione implements Comparable<OffertaDiLavoro>{

	private String datoreDiLavoro;
	private String figura;
	private String sede;
	private String dataInizioValidita;
	private String dataFineValidita;
	private int numPosizioniAperte;	
	
	public OffertaDiLavoro(String datoreDiLavoro, String figura, String sede, String dataInizioValidita, String dataFineValidita, int numPosizioniAperte){
		this.datoreDiLavoro=datoreDiLavoro;
		this.figura=figura;
		this.sede=sede;
		this.dataInizioValidita=dataInizioValidita;
		this.dataFineValidita=dataFineValidita;
		this.numPosizioniAperte=numPosizioniAperte;
	}
	
	public String getDatoreDiLavoro() {
		return datoreDiLavoro;
	}
	
	public String getFigura(){
		return figura;
	}

	public String getSede(){
		return sede;
	}
	

	public String getDataInizioValidita() {
		return dataInizioValidita;
	}

	public String getDataFineValidita() {
		return dataFineValidita;
	}

	public void decrementaPosizioniAperte(){
		if(this.numPosizioniAperte>0)
			this.numPosizioniAperte--;
	}
	
	public void setDataFineValidita(String dataFineValidita) {
		if(dataFineValidita.compareTo(this.dataFineValidita)>0)
   		    this.dataFineValidita = dataFineValidita;
	}

	public int getNumPosizioniAperte() {
		return numPosizioniAperte;
	}

	public void aggiungiCompetenzaRichiesta(String competenza, int livello){
		super.aggiungiCompetenza(competenza, livello);
	}


	
	public int compareTo(OffertaDiLavoro altra) {
		if(this.dataInizioValidita.compareTo(altra.getDataInizioValidita())!=0)
				return (this.dataInizioValidita.compareTo(altra.getDataInizioValidita()));
		else
			return this.dataFineValidita.compareTo(altra.getDataFineValidita());
	}



}
