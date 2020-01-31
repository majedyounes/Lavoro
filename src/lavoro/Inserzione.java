package lavoro;

import java.util.*;

public class Inserzione {
	
	private int codice;
	
	private Map<String, Integer> competenze = new HashMap<String,Integer>();
	
	public void setCodice(int codice){
		this.codice=codice;
	}
	
	public int getCodice(){
		return codice;
	}

	protected void aggiungiCompetenza(String competenza, int livello){
		if(livello>0 && livello<=5)
			competenze.put(competenza, livello);
	}

	public String[] elencoCompetenze(){
		Object[] oarray = competenze.keySet().toArray();
		String[] sarray = new String[oarray.length];
		for(int i=0;i<oarray.length;i++)
			sarray[i]=(String)oarray[i];
		return sarray;
	}
	
	public boolean competenzaPresente(String competenza){
		if(competenze.get(competenza)!=null)
			return true;
		else
			return false;		
	}
	
	public int getLivelloCompetenza(String competenza) throws CompetenzaAssenteException{
		if(competenze.get(competenza)!=null)
			return competenze.get(competenza);
		else
			throw new CompetenzaAssenteException();
	}
}
