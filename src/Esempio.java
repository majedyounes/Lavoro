import lavoro.*;
import java.util.*;

public class Esempio {

	public static void main(String[] args) throws Exception {

		/* ### OFFERTE DI LAVORO E CURRICULUM ### */

		System.out.println("### OFFERTE DI LAVORO E CURRICULUM ###\n"); 
		
		Portale portale = new Portale();
		
		int numInserzione = portale.aggiungiOffertaDiLavoro("Auto & figli", "Venditore", "Milano", "20130201", "20130228", 2);
		System.out.println("Aggiunta offerta di lavoro numero "+numInserzione);
		OffertaDiLavoro offerta = (OffertaDiLavoro)portale.getInserzione(numInserzione); 
		
		System.out.println(" Datore di lavoro: "+offerta.getDatoreDiLavoro());
		System.out.println(" Figura professionale: "+offerta.getFigura());
		System.out.println(" Sede: "+offerta.getSede());
		System.out.println(" Validita': dal "+offerta.getDataInizioValidita()+" al "+offerta.getDataFineValidita());
		System.out.println(" Posizioni aperte: "+offerta.getNumPosizioniAperte());
		
		System.out.println("\nEstensione data fine validita offerta "+numInserzione);
		offerta.setDataFineValidita("20130315");
		System.out.println(" Validita': dal "+offerta.getDataInizioValidita()+" al "+offerta.getDataFineValidita());
		
		    numInserzione = portale.aggiungiOffertaDiLavoro("Muri S.N.C.", "Muratore", "Bergamo", "20130110", "20130430", 5);
		    numInserzione = portale.aggiungiOffertaDiLavoro("Speedy auto", "Contabile", "Venezia", "20121124", "20131231", 1);
		    numInserzione = portale.aggiungiOffertaDiLavoro("Sartoria & Co.", "Sarto", "Napoli", "20130110", "20140630", 1);
		
		System.out.println("\nElenco offerte di lavoro:");    
		List<OffertaDiLavoro> lofferte = new LinkedList<OffertaDiLavoro>(portale.elencoOfferteDiLavoro());
		for(int i=0;i<lofferte.size();i++){
			OffertaDiLavoro o = lofferte.get(i);
			System.out.println(" "+o.getCodice()+"\t"+o.getDatoreDiLavoro()+"\tcerca "+o.getFigura()+"\t a "+o.getSede()+"\tvalida dal "+o.getDataInizioValidita()+" al "+o.getDataFineValidita());
		}
	
		System.out.println("\nRimuovo inserzione 10001");
		
		portale.rimuoviInserzione(10001);
		
		System.out.println("\nElenco offerte di lavoro (dopo la rimozione di una di esse):");    
		                      lofferte = new LinkedList<OffertaDiLavoro>(portale.elencoOfferteDiLavoro());
		for(int i=0;i<lofferte.size();i++){
			OffertaDiLavoro o = lofferte.get(i);
			System.out.println(" "+o.getCodice()+"\t"+o.getDatoreDiLavoro()+"\tcerca "+o.getFigura()+"\t a "+o.getSede()+"\tvalida dal "+o.getDataInizioValidita()+" al "+o.getDataFineValidita());
		}

		
		    numInserzione = portale.aggiungiCurriculum("RSSMRO75P11T565E", "Mario", "Rossi", "19750623", "Roma");
		System.out.println("\nAggiunto curriculum numero "+numInserzione);
		Curriculum curriculum = (Curriculum)portale.getInserzione(numInserzione); 
		    
		System.out.println(" Codice fiscale: "+curriculum.getCodiceFiscale());
		System.out.println(" Nome: "+curriculum.getNome());
		System.out.println(" Cognome: "+curriculum.getCognome());
		System.out.println(" Data di nascita: "+curriculum.getDataNascita());
		System.out.println(" Residenza: "+curriculum.getResidenza());

     	    numInserzione = portale.aggiungiCurriculum("BLUMRC46T06B678G", "Marco", "Blu", "19460106", "Palermo");
		
		System.out.println("\nElenco curriculum:");    
		List<Curriculum> lcurriculum = new LinkedList<Curriculum>(portale.elencoCurriculum());
		for(int i=0;i<lcurriculum.size();i++){
			Curriculum c = lcurriculum.get(i);
			System.out.println(" "+c.getCodiceFiscale()+" "+c.getCognome()+"\t"+c.getNome()+"\t nato il "+c.getDataNascita()+" residente a "+c.getResidenza());
		}
		
		/* ### COMPETENZE ### */

		System.out.println("\n### COMPETENZE ###\n"); 
		
		System.out.println("Selezionata offerta di lavoro numero 10000");
	        offerta = (OffertaDiLavoro)portale.getInserzione(10000);

     	    offerta.aggiungiCompetenzaRichiesta("Saper vendere i prodotti auto", 2);
		
	    System.out.println("\n Competenze richieste da "+offerta.getDatoreDiLavoro()+" per figura di "+offerta.getFigura());
		String[] competenze = offerta.elencoCompetenze();
		for(int i=0;i<competenze.length;i++)
			System.out.println("  - "+competenze[i]);
		
		
		System.out.println("\nSelezionato curriculum per codice fiscale RSSMRO75P11T565E");
		    curriculum = portale.getCurriculum("RSSMRO75P11T565E");
		    curriculum.aggiungiCompetenzaPosseduta("Saper vendere i prodotti auto", 3);
	        curriculum.aggiungiCompetenzaPosseduta("Saper gestire una immatricolazione", 5);

		System.out.println(" \nCompetenze possedute da "+curriculum.getNome()+" "+curriculum.getCognome()+" :");    
		    competenze = curriculum.elencoCompetenze();
		for(int i=0;i<competenze.length;i++)
			System.out.println("  - "+competenze[i]);
		

		/* ### INCONTRO DOMANDA E OFFERTA ### */

		System.out.println("\n### INCONTRO DOMANDA E OFFERTA ###"); 

		    offerta = (OffertaDiLavoro)portale.getInserzione(10003);
		offerta.aggiungiCompetenzaRichiesta("Saper cucire a mano", 1);
     	    offerta = (OffertaDiLavoro)portale.getInserzione(10002);
	    offerta.aggiungiCompetenzaRichiesta("Saper gestire la contabilita' di una concessionaria", 5);
		
		System.out.println("\nSelezionato curriculum per codice fiscale RSSMRO75P11T565E");
	        curriculum = portale.getCurriculum("RSSMRO75P11T565E");
	    
	    System.out.println(" Offerte di lavoro disponibili:");    
	        
	    LinkedList<Inserzione> inserzioni = new LinkedList<Inserzione>(portale.cerca(curriculum.getCodice()));
		for(int i=0;i<inserzioni.size();i++){
			OffertaDiLavoro o = (OffertaDiLavoro)inserzioni.get(i);
			System.out.println("  * "+o.getDatoreDiLavoro());
		}

    
		System.out.println("\nSelezionata offerta di lavoro numero 10000");
        offerta = (OffertaDiLavoro)portale.getInserzione(10000);
    
        System.out.println(" Candidati idonei:");    
        
                               inserzioni = new LinkedList<Inserzione>(portale.cerca(offerta.getCodice()));
        for(int i=0;i<inserzioni.size();i++){
        	Curriculum c = (Curriculum)inserzioni.get(i);
        	System.out.println("  * "+c.getNome()+" "+c.getCognome());
        }
		
		
		
		/* ### LETTURA DA FILE ### */

		System.out.println("\n### LETTURA DA FILE ###\n"); 
		
		portale.leggiFile("input.txt");
		
		System.out.println("Elenco offerte (dopo la lettura del file):");    
		                      lofferte = new LinkedList<OffertaDiLavoro>(portale.elencoOfferteDiLavoro());
		for(int i=0;i<lofferte.size();i++){
			OffertaDiLavoro o = lofferte.get(i);
			System.out.println(" "+o.getCodice()+"\t"+o.getDatoreDiLavoro()+"\tcerca "+o.getFigura()+"\t a "+o.getSede()+"\tvalida dal "+o.getDataInizioValidita()+" al "+o.getDataFineValidita());
		}
		
		System.out.println("\nElenco curriculum (dopo la lettura del file):");    
		                 lcurriculum = new LinkedList<Curriculum>(portale.elencoCurriculum());
		for(int i=0;i<lcurriculum.size();i++){
			Curriculum c = lcurriculum.get(i);
			System.out.println(" "+c.getCodiceFiscale()+" "+c.getCognome()+"\t"+c.getNome()+"\t nato il "+c.getDataNascita()+" residente a "+c.getResidenza());
		}
		
		
		
		
		
		
		
			
	}

}
