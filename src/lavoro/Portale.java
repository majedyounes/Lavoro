package lavoro;

import java.util.*;
import java.io.*;

public class Portale {

	private int numInserzione=10000;
	private Map<Integer,Inserzione> inserzioni = new TreeMap<Integer,Inserzione>();
	
	public int aggiungiOffertaDiLavoro(String datoreDiLavoro, String figura, String sede, String dataInizioValidita, String dataFineValidita, int numPosizioniAperte){
		
		OffertaDiLavoro otemp = new OffertaDiLavoro(datoreDiLavoro, figura, sede, dataInizioValidita, dataFineValidita, numPosizioniAperte);
		otemp.setCodice(numInserzione);
		inserzioni.put(numInserzione, otemp);
		numInserzione++;
		return numInserzione-1;
	}

	public int aggiungiCurriculum(String codiceFiscale, String nome, String cognome, String dataNascita, String residenza){
		
		Curriculum ctemp = new Curriculum(codiceFiscale, nome,cognome,dataNascita,residenza);
		ctemp.setCodice(numInserzione);
		inserzioni.put(numInserzione, ctemp);
		numInserzione++;
		return numInserzione-1;
	}


	public Inserzione getInserzione(int numInserzione) throws InserzioneInesistenteExcepion{
		if(inserzioni.get(numInserzione)!=null)
			return inserzioni.get(numInserzione);
		else
			throw new InserzioneInesistenteExcepion();
	}
	
	public Inserzione rimuoviInserzione(int numInserzione) throws InserzioneInesistenteExcepion{
		if(inserzioni.get(numInserzione)!=null){
			inserzioni.remove(numInserzione);
			return inserzioni.get(numInserzione);
		}
		else
			throw new InserzioneInesistenteExcepion();
	}

	public Curriculum getCurriculum(String codiceFiscale) throws InserzioneInesistenteExcepion{
		List<Inserzione> ltemp = new LinkedList<Inserzione>(inserzioni.values());
		Curriculum result=null;
		for(int i=0;i<ltemp.size();i++){
			Inserzione itemp = ltemp.get(i);
			if(itemp instanceof Curriculum)
				if(((Curriculum)itemp).getCodiceFiscale().equals(codiceFiscale))
					result=(Curriculum)itemp;
		}
		if(result!=null)
			return result;
		else
			throw new InserzioneInesistenteExcepion();
	}

	
	public Collection<Inserzione> elencoInserzioni(){
		Collection<Inserzione> result = new LinkedList<Inserzione>(inserzioni.values());
		return result;
	}
	
	public Collection<OffertaDiLavoro> elencoOfferteDiLavoro(){
		List<Inserzione> ltemp = new LinkedList<Inserzione>(inserzioni.values());
		List<OffertaDiLavoro> result = new LinkedList<OffertaDiLavoro>();
		for(int i=0;i<ltemp.size();i++){
			Inserzione itemp = ltemp.get(i);
			if(itemp instanceof OffertaDiLavoro)
				result.add((OffertaDiLavoro)itemp);
		}
		Collections.sort(result);
		return result;	
	}
	
	public Collection<Curriculum> elencoCurriculum(){
		List<Inserzione> ltemp = new LinkedList<Inserzione>(inserzioni.values());
		List<Curriculum> result = new LinkedList<Curriculum>();
		for(int i=0;i<ltemp.size();i++){
			Inserzione itemp = ltemp.get(i);
			if(itemp instanceof Curriculum)
				result.add((Curriculum)itemp);
		}
		Collections.sort(result);
		return result;	
	}
	
	public String[] elencoCompetenze(int numInserzione){
		Inserzione itemp = inserzioni.get(numInserzione);
		return itemp.elencoCompetenze();
	}

	public int getLivelloCompetenza(int numInserzione, String competenza) throws CompetenzaAssenteException{
		Inserzione itemp = inserzioni.get(numInserzione);
		return itemp.getLivelloCompetenza(competenza);
	}
	
	public Collection<Inserzione> cerca(int numInserzione) throws CompetenzaAssenteException{
		
		Inserzione itemp = inserzioni.get(numInserzione);
		List<Inserzione> input = new LinkedList<Inserzione>(inserzioni.values());
		Collection<Inserzione> output = new LinkedList<Inserzione>();
		
		if(itemp instanceof Curriculum)
		{
			Curriculum curriculum = (Curriculum)itemp;
			List<OffertaDiLavoro> offerte = new LinkedList<OffertaDiLavoro>();
			for(int i=0;i<input.size();i++)
				if((input.get(i)) instanceof OffertaDiLavoro)
					offerte.add((OffertaDiLavoro)input.get(i));
			
			for(int i=0;i<offerte.size();i++)
			{
				OffertaDiLavoro offerta = offerte.get(i);	
				String[] competenze = offerta.elencoCompetenze();
				int cntTrovate=0;
				for(int j=0;j<competenze.length;j++)
					if(curriculum.competenzaPresente(competenze[j]))
					{
						if(curriculum.getLivelloCompetenza(competenze[j])>=offerta.getLivelloCompetenza(competenze[j]))
     						cntTrovate++;
					}
				if(cntTrovate==competenze.length)
					output.add(offerta);
			}
			
			return output;
		}
		else if(itemp instanceof OffertaDiLavoro)
		{
			OffertaDiLavoro offerta = (OffertaDiLavoro)itemp;
			List<Curriculum> cvs = new LinkedList<Curriculum>();
			for(int i=0;i<input.size();i++)
				if((input.get(i)) instanceof Curriculum)
					cvs.add((Curriculum)input.get(i));
			
			for(int i=0;i<cvs.size();i++)
			{
				Curriculum curriculum = cvs.get(i);	
				String[] competenze = offerta.elencoCompetenze();
				int cntTrovate=0;
				for(int j=0;j<competenze.length;j++)
					if(curriculum.competenzaPresente(competenze[j])){
						if(curriculum.getLivelloCompetenza(competenze[j])>=offerta.getLivelloCompetenza(competenze[j]))
     						cntTrovate++;
					}
				if(cntTrovate==competenze.length)
					output.add(curriculum);
			}
			
			return output;
		}
		return null;
	}
	
	
	
	public void leggiFile(String nomeFile) throws IOException {
		
		BufferedReader in = new BufferedReader(new FileReader(nomeFile));
		String linea;
		
		while ((linea = in.readLine()) != null) {
			
			try {
			    StringTokenizer st = new StringTokenizer(linea, ";");
			    String iniziale = st.nextToken().trim();
			    if (iniziale.toUpperCase().equals("C")) 
			    {
				   String codiceFiscale = st.nextToken().trim();
				   String nome = st.nextToken().trim();
				   String cognome = st.nextToken().trim();
				   String dataNascita = st.nextToken().trim();
				   String residenza = st.nextToken().trim();
				   this.aggiungiCurriculum(codiceFiscale, nome, cognome, dataNascita, residenza);
				} 
			    else if (iniziale.toUpperCase().equals("O"))
			    {
			       String datoreDiLavoro = st.nextToken().trim();
			       String figura = st.nextToken().trim();
			       String sede = st.nextToken().trim();
			       String dataInizioValidita = st.nextToken().trim();
			       String dataFineValidita = st.nextToken().trim();
			       int numPosizioniAperte = Integer.parseInt(st.nextToken().trim());
			       this.aggiungiOffertaDiLavoro(datoreDiLavoro, figura, sede, dataInizioValidita, dataFineValidita, numPosizioniAperte);	
			    }
			} 
			catch (Exception e) {
				//e.printStackTrace();
			}
			
		}
		in.close();		
		
	}
	
	
	
	
}
