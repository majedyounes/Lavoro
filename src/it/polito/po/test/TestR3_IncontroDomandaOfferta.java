package it.polito.po.test;

import lavoro.*;

import junit.framework.TestCase;
import java.util.*;

public class TestR3_IncontroDomandaOfferta  extends TestCase {

	Portale portale;
	
	public void setUp(){
		portale = new Portale();
    }
	
	public void testCercaOfferteDiLavoroDatoCurricum() throws InserzioneInesistenteExcepion, CompetenzaAssenteException {
	
		System.out.println("\n*** testCercaOfferteDiLavoroDatoCurricum() ***\n");

		portale.aggiungiOffertaDiLavoro("Auto & figli", "Venditore", "Milano", "20130201", "20130228", 2);		
	    portale.aggiungiOffertaDiLavoro("Buy S.P.A.", "Marketing", "Firenze", "20130105", "20130422", 4);
		portale.aggiungiOffertaDiLavoro("Speedy auto", "Contabile", "Venezia", "20121124", "20131231", 1);
		portale.aggiungiOffertaDiLavoro("Sartoria & Co.", "Sarto", "Napoli", "20130110", "20140630", 1);

		portale.aggiungiCurriculum("RSSMRO75P11T565E", "Mario", "Rossi", "19750623", "Roma");
 	    portale.aggiungiCurriculum("BLUMRC46T06B678G", "Marco", "Blu", "19460106", "Palermo");

		System.out.println("Elenco offerte di lavoro:");    
		List<OffertaDiLavoro> lofferte = new LinkedList<OffertaDiLavoro>(portale.elencoOfferteDiLavoro());
		for(int i=0;i<lofferte.size();i++){
			OffertaDiLavoro o = lofferte.get(i);
			System.out.println(" "+o.getCodice()+"\t"+o.getDatoreDiLavoro()+"\tcerca "+o.getFigura()+"\t a "+o.getSede()+"\tvalida dal "+o.getDataInizioValidita()+" al "+o.getDataFineValidita());
		}

        OffertaDiLavoro offerta = (OffertaDiLavoro)portale.getInserzione(10000);

        offerta.aggiungiCompetenzaRichiesta("Saper vendere i prodotti auto", 2);

	    offerta = (OffertaDiLavoro)portale.getInserzione(10002);
 	    offerta.aggiungiCompetenzaRichiesta("Saper gestire la contabilita' di una concessionaria", 5);

	    offerta = (OffertaDiLavoro)portale.getInserzione(10003);
	    offerta.aggiungiCompetenzaRichiesta("Saper cucire a mano", 1);

 	    for(int i=10000;i<=10003;i++){
 	    	offerta = (OffertaDiLavoro)portale.getInserzione(i);
 	 	    System.out.println("\n Competenze richieste da "+offerta.getDatoreDiLavoro()+" per figura di "+offerta.getFigura());
 	 	    String[] competenze = offerta.elencoCompetenze();
 	 	    for(int c=0;c<competenze.length;c++)
 	 	    	System.out.println("  - "+competenze[c]+" con livello "+offerta.getLivelloCompetenza(competenze[c]));
 	    }

 	    System.out.println("\nElenco curriculum:");    
 	    List<Curriculum> lcurriculum = new LinkedList<Curriculum>(portale.elencoCurriculum());
 	    for(int i=0;i<lcurriculum.size();i++){
 	    	Curriculum c = lcurriculum.get(i);
 	    	System.out.println(" "+c.getCodiceFiscale()+" "+c.getCognome()+"\t"+c.getNome()+"\t nato il "+c.getDataNascita()+" residente a "+c.getResidenza());
 	    }

 	    Curriculum curriculum = portale.getCurriculum("RSSMRO75P11T565E");
 	    curriculum.aggiungiCompetenzaPosseduta("Saper vendere i prodotti auto", 3);
 	    curriculum.aggiungiCompetenzaPosseduta("Saper gestire una immatricolazione", 5);

 	    System.out.println(" \nCompetenze possedute da "+curriculum.getNome()+" "+curriculum.getCognome()+" :");    
 	    String[] competenze = curriculum.elencoCompetenze();
 	    for(int i=0;i<competenze.length;i++)
 	    	System.out.println("  - "+competenze[i]+" con livello "+curriculum.getLivelloCompetenza(competenze[i]));

 	    curriculum = portale.getCurriculum("BLUMRC46T06B678G");
 	    curriculum.aggiungiCompetenzaPosseduta("Saper vendere i prodotti auto", 1);
 	    curriculum.aggiungiCompetenzaPosseduta("Saper contrattare con le case produttrici", 1);
  	   
 	    System.out.println(" \nCompetenze possedute da "+curriculum.getNome()+" "+curriculum.getCognome()+" :");    
 	    competenze = curriculum.elencoCompetenze();
 	    for(int i=0;i<competenze.length;i++)
 	    	System.out.println("  - "+competenze[i]+" con livello "+curriculum.getLivelloCompetenza(competenze[i]));
 	    
 	    System.out.println("\nOfferte di lavoro disponibili per curriculum RSSMRO75P11T565E:");    
        curriculum = portale.getCurriculum("RSSMRO75P11T565E");
    
        LinkedList<Inserzione> inserzioni = new LinkedList<Inserzione>(portale.cerca(curriculum.getCodice()));

        assertNotNull("Metodo cerca() non implementato correttamente.", inserzioni);

        for(int i=0;i<inserzioni.size();i++){
        	OffertaDiLavoro o = (OffertaDiLavoro)inserzioni.get(i);
        	System.out.println("  * "+o.getDatoreDiLavoro());
        }
        
        assertEquals("Numero di offerte di lavoro trovate non corretto.", 2,inserzioni.size());
        
        boolean trovate=false;
        if((inserzioni.get(0).getCodice()==10000 && inserzioni.get(1).getCodice()==10001) || (inserzioni.get(1).getCodice()==10000 && inserzioni.get(0).getCodice()==10001)) {
        	trovate=true;
        	System.out.println("\nOfferte trovate corrette");
        }
        else
        	System.out.println("\nOfferte trovate non corrette");
        
        assertEquals("Offerte di lavoro trovate non corrette.", true,trovate);
		
	}
	
	
	public void testCercaCurriculumDataOffertaDiLavoro() throws InserzioneInesistenteExcepion, CompetenzaAssenteException {
		
		System.out.println("\n*** testCercaCurriculumDataOffertaDiLavoro() ***\n");
		portale.aggiungiOffertaDiLavoro("Auto & figli", "Venditore", "Milano", "20130201", "20130228", 2);		
	    portale.aggiungiOffertaDiLavoro("Buy S.P.A.", "Marketing", "Firenze", "20130105", "20130422", 4);
		portale.aggiungiOffertaDiLavoro("Speedy auto", "Contabile", "Venezia", "20121124", "20131231", 1);
		portale.aggiungiOffertaDiLavoro("Sartoria & Co.", "Sarto", "Napoli", "20130110", "20140630", 1);

		portale.aggiungiCurriculum("RSSMRO75P11T565E", "Mario", "Rossi", "19750623", "Roma");
 	    portale.aggiungiCurriculum("BLUMRC46T06B678G", "Marco", "Blu", "19460106", "Palermo");

		System.out.println("Elenco offerte di lavoro:");    
		List<OffertaDiLavoro> lofferte = new LinkedList<OffertaDiLavoro>(portale.elencoOfferteDiLavoro());
		for(int i=0;i<lofferte.size();i++){
			OffertaDiLavoro o = lofferte.get(i);
			System.out.println(" "+o.getCodice()+"\t"+o.getDatoreDiLavoro()+"\tcerca "+o.getFigura()+"\t a "+o.getSede()+"\tvalida dal "+o.getDataInizioValidita()+" al "+o.getDataFineValidita());
		}

        OffertaDiLavoro offerta = (OffertaDiLavoro)portale.getInserzione(10000);

        offerta.aggiungiCompetenzaRichiesta("Saper vendere i prodotti auto", 2);

	    offerta = (OffertaDiLavoro)portale.getInserzione(10002);
 	    offerta.aggiungiCompetenzaRichiesta("Saper gestire la contabilita' di una concessionaria", 5);

	    offerta = (OffertaDiLavoro)portale.getInserzione(10003);
	    offerta.aggiungiCompetenzaRichiesta("Saper cucire a mano", 1);

 	    for(int i=10000;i<=10003;i++){
 	    	offerta = (OffertaDiLavoro)portale.getInserzione(i);
 	 	    System.out.println("\n Competenze richieste da "+offerta.getDatoreDiLavoro()+" per figura di "+offerta.getFigura());
 	 	    String[] competenze = offerta.elencoCompetenze();
 	 	    for(int c=0;c<competenze.length;c++)
 	 	    	System.out.println("  - "+competenze[c]+" con livello "+offerta.getLivelloCompetenza(competenze[c]));
 	    }

 	    System.out.println("\nElenco curriculum:");    
 	    List<Curriculum> lcurriculum = new LinkedList<Curriculum>(portale.elencoCurriculum());
 	    for(int i=0;i<lcurriculum.size();i++){
 	    	Curriculum c = lcurriculum.get(i);
 	    	System.out.println(" "+c.getCodiceFiscale()+" "+c.getCognome()+"\t"+c.getNome()+"\t nato il "+c.getDataNascita()+" residente a "+c.getResidenza());
 	    }

 	    Curriculum curriculum = portale.getCurriculum("RSSMRO75P11T565E");
 	    curriculum.aggiungiCompetenzaPosseduta("Saper vendere i prodotti auto", 3);
 	    curriculum.aggiungiCompetenzaPosseduta("Saper gestire una immatricolazione", 5);

 	    System.out.println(" \nCompetenze possedute da "+curriculum.getNome()+" "+curriculum.getCognome()+" :");    
 	    String[] competenze = curriculum.elencoCompetenze();
 	    for(int i=0;i<competenze.length;i++)
 	    	System.out.println("  - "+competenze[i]+" con livello "+curriculum.getLivelloCompetenza(competenze[i]));

 	    curriculum = portale.getCurriculum("BLUMRC46T06B678G");
 	    curriculum.aggiungiCompetenzaPosseduta("Saper vendere i prodotti auto", 1);
 	    curriculum.aggiungiCompetenzaPosseduta("Saper contrattare con le case produttrici", 1);
  	   
 	    System.out.println(" \nCompetenze possedute da "+curriculum.getNome()+" "+curriculum.getCognome()+" :");    
 	    competenze = curriculum.elencoCompetenze();
 	    for(int i=0;i<competenze.length;i++)
 	    	System.out.println("  - "+competenze[i]+" con livello "+curriculum.getLivelloCompetenza(competenze[i]));
 	    
  	    offerta = (OffertaDiLavoro)portale.getInserzione(10000);
        System.out.println("\nCandidati idonei per inserzione 10000:");    
        
        LinkedList<Inserzione> inserzioni = new LinkedList<Inserzione>(portale.cerca(offerta.getCodice()));
        for(int i=0;i<inserzioni.size();i++){
        	Curriculum c = (Curriculum)inserzioni.get(i);
        	System.out.println("  * "+c.getNome()+" "+c.getCognome());
        }

        assertNotNull("Metodo cerca() non implementato correttamente.", inserzioni);
        
        assertEquals("Numero di curriculum trovati non corretto.", 1,inserzioni.size());
        
        boolean trovati=false;
        if(inserzioni.get(0).getCodice()==10004){
        	trovati=true;
        	System.out.println("\nCurriculum trovati corretti");
        }
        else
        	System.out.println("\nCurriculum trovati non corretti");
        
        assertEquals("Curriculum trovati non corretti.", true,trovati);
		
	}
}
