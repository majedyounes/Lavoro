package it.polito.po.test;

import lavoro.*;

import junit.framework.TestCase;

public class TestR2_Competenze extends TestCase {

	Portale portale;
	
	public void setUp(){
		portale = new Portale();
    }
	
	public void testAggiungiCompetenzaEdElencoCompetenze() throws InserzioneInesistenteExcepion {

		System.out.println("\n*** testAggiungiCompetenzaEdElencoCompetenze() ***\n");

		System.out.println("Aggiungo offerta di lavoro");
		int numInserzione = portale.aggiungiOffertaDiLavoro("Auto & figli", "Venditore", "Milano", "20130201", "20130228", 2);

 	    OffertaDiLavoro offerta = (OffertaDiLavoro)portale.getInserzione(numInserzione);

 	    System.out.println("Aggiungo competenza Saper vendere i prodotti auto");
 	    offerta.aggiungiCompetenzaRichiesta("Saper vendere i prodotti auto", 2);
		
 	    System.out.println("\n Competenze richieste dall'offerta aggiunta:");

 	    String[] competenze = offerta.elencoCompetenze();
 	    
        assertNotNull("Metodo elencoCompetenze() non implementato correttamente (null).",competenze);

        int n = competenze.length;
        
        assertEquals("Numero competenze richieste aggiunte non corretto.",1,n);

 	    System.out.println("  - "+competenze[0]);

		assertEquals("Competenza richiesta aggiunta non corretta.", "Saper vendere i prodotti auto",competenze[0]);

        System.out.println("\nCompetenze aggiunte "+n);
	}
	
	public void testGetLivelloCompetenzaEdEccezione() throws InserzioneInesistenteExcepion {

		System.out.println("\n*** testGetLivelloCompetenzaEdEccezione() ***\n");

		System.out.println("Aggiungo curriculum");
	    int numInserzione = portale.aggiungiCurriculum("RSSMRO75P11T565E", "Mario", "Rossi", "19750623", "Roma");

 	    Curriculum curriculum = (Curriculum)portale.getInserzione(numInserzione);
 	    
 	    System.out.println("Aggiungo competenza Saper gestire una immatricolazione con livello 5");
        curriculum.aggiungiCompetenzaPosseduta("Saper gestire una immatricolazione", 5);
		
 	    System.out.println("\n Competenze possedute:");

 	    String[] competenze = curriculum.elencoCompetenze();

 	    System.out.println("  - "+competenze[0]);

 	    boolean eccezione=false;
 	    
 	    int livello=-1;
 	    try {
 	    	System.out.println("\nCerco livello per competenza Saper gestire una immatricolazione");
 	        livello = curriculum.getLivelloCompetenza("Saper gestire una immatricolazione");
 	    	System.out.println("Livello "+livello);
 	    } catch (CompetenzaAssenteException e) {
			System.out.println("Competenza assente");
			eccezione=true;
		}
 	    
		assertEquals("Eccezione CompetenzaAssenteException non gestita correttamente.",false,eccezione);

		if(livello==5)
			System.out.println("Livello corretto");
		else
			System.out.println("Livello errato");

		assertEquals("Metodo getLivelloCompetenza() non implementato correttamente.",5,livello);
		
 	    eccezione=false;
 	    
 	    try {
 	    	System.out.println("\nCerco livello per competenza Saper vendere i prodotti auto");
		    livello = curriculum.getLivelloCompetenza("Saper vendere i prodotti auto");
 	    	System.out.println("Livello "+livello);
 	    } catch (CompetenzaAssenteException e) {
			System.out.println("Competenza assente");
			eccezione=true;
		}
 	    
		assertEquals("Eccezione CompetenzaAssenteException non gestita correttamente.",true,eccezione);

	}

	public void testCompetenzaPresente() throws InserzioneInesistenteExcepion, CompetenzaAssenteException {
		System.out.println("\n*** testCompetenzaPresente() ***\n");

		System.out.println("Aggiungo curriculum");
	    int numInserzione = portale.aggiungiCurriculum("RSSMRO75P11T565E", "Mario", "Rossi", "19750623", "Roma");

 	    Curriculum curriculum = (Curriculum)portale.getInserzione(numInserzione);

 	    System.out.println("Aggiungo competenza Saper gestire una immatricolazione con livello 5");
        curriculum.aggiungiCompetenzaPosseduta("Saper gestire una immatricolazione", 5);
		
 	    System.out.println("\nVerifico presenza competenza Saper gestire una immatricolazione");
 	    boolean presente = curriculum.competenzaPresente("Saper gestire una immatricolazione");
 	    if(presente)
 	    	System.out.println("Competenza trovata (corretto)");
 	    else
 	    	System.out.println("Competenza non trovata (errore)");
 	    	
		assertEquals("Metodo competenzaPresente() non implementato correttamente.",true,presente);
		
 	    System.out.println("\nVerifico presenza competenza Saper vendere i prodotti auto");
 	    presente = curriculum.competenzaPresente("Saper vendere i prodotti auto");
 	    if(presente)
 	    	System.out.println("Competenza trovata (errore)");
 	    else
 	    	System.out.println("Competenza non trovata (corretto)");
 	    
		assertEquals("Metodo competenzaPresente() non implementato correttamente.",false,presente);
		
	}

	
	public void testAggiungiCompetenzaDuplicata() throws InserzioneInesistenteExcepion, CompetenzaAssenteException {

		System.out.println("\n*** testAggiungiCompetenzaDuplicata() ***\n");
		

		System.out.println("Aggiungo curriculum");
		int numInserzione = portale.aggiungiCurriculum("RSSMRO75P11T565E", "Mario", "Rossi", "19750623", "Roma");

 	    Curriculum curriculum = (Curriculum)portale.getInserzione(numInserzione);
 	    
 	    System.out.println("Aggiungo competenza Saper gestire una immatricolazione con livello 5");
        curriculum.aggiungiCompetenzaPosseduta("Saper gestire una immatricolazione", 5);
		
 	    System.out.println("\n Competenze possedute:");

 	    String[] competenze = curriculum.elencoCompetenze();

 	    System.out.println("  - "+competenze[0]);

 	    System.out.println("\nCerco livello per competenza Saper gestire una immatricolazione");
 	    int livello = curriculum.getLivelloCompetenza("Saper gestire una immatricolazione");
 	    System.out.println("Livello "+livello);

 	    System.out.println("Aggiungo competenza (duplicata) Saper gestire una immatricolazione con livello 4");
        curriculum.aggiungiCompetenzaPosseduta("Saper gestire una immatricolazione", 4);
 	    System.out.println("\nCerco livello per competenza Saper gestire una immatricolazione");
 	    livello = curriculum.getLivelloCompetenza("Saper gestire una immatricolazione");
 	    System.out.println("Livello "+livello);
 	    if(livello==4)
 	    	System.out.println("Livello corretto");
 	    else
 	    	System.out.println("Livello errato");
 	    
 	    
		assertEquals("Errore nella gestione di competenze diplicate.",4,livello);

 	    System.out.println("\nAggiungo competenza Saper gestire una immatricolazione con livello 8 (fuori intervallo)");
        curriculum.aggiungiCompetenzaPosseduta("Saper gestire una immatricolazione", 8);
 	    System.out.println("\nCerco livello per competenza Saper gestire una immatricolazione");
 	    livello = curriculum.getLivelloCompetenza("Saper gestire una immatricolazione");
 	    System.out.println("Livello "+livello);
 	    if(livello==4)
 	    	System.out.println("Livello corretto");
 	    else
 	    	System.out.println("Livello errato");
		
		assertEquals("Errore nella gestione di competenze diplicate.",4,livello);
		
		
	}
	
	public void testElencoCompetenzeEGetElencoCompetenzeDiPortale() throws InserzioneInesistenteExcepion, CompetenzaAssenteException {

		System.out.println("\n*** testElencoCompetenzeEGetElencoCompetenzeDiPortale() ***\n");
	
	    int numInserzione = portale.aggiungiCurriculum("RSSMRO75P11T565E", "Mario", "Rossi", "19750623", "Roma");
		System.out.println("Aggiungo curriculum");
 	    Curriculum curriculum = (Curriculum)portale.getInserzione(numInserzione);
 	    
 	    System.out.println("Aggiungo competenza Saper gestire una immatricolazione con livello 5");
        curriculum.aggiungiCompetenzaPosseduta("Saper gestire una immatricolazione", 5);

 	    System.out.println("Aggiungo competenza Saper vendere il prodotto auto con livello 2");
        curriculum.aggiungiCompetenzaPosseduta("Saper vendere il prodotto auto", 2);

        System.out.println("Aggiungo competenza Saper gestire la contabilita' di una concessionaria con livello 3");
        curriculum.aggiungiCompetenzaPosseduta("Saper gestire la contabilita' di una concessionaria", 3);

        System.out.println("\nCompetenze possedute:");
 	    String[] competenze = portale.elencoCompetenze(numInserzione);

		assertNotNull("Metodo elencoCompetenze() di Portale non implementato correttamente.",competenze);

 	    for(int i=0;i<competenze.length;i++)
 	 	    System.out.println("  - "+competenze[i]);

		int n = competenze.length;
		
		System.out.println("\nAggiunte "+n+" competenze");
		
		assertEquals("Metodo elencoCompetenze() di Portale non implementato correttamente.",3,n);

 	    System.out.println("\nCerco livello per competenza Saper vendere il prodotto auto");
		int livello = portale.getLivelloCompetenza(numInserzione, "Saper vendere il prodotto auto");
 	    System.out.println("Livello "+livello);
 	    if(livello==2)
 	    	System.out.println("Livello corretto");
 	    else
 	    	System.out.println("Livello errato");

		assertEquals("Metodo getLivelloCompetenza() di Portale non implementato correttamente.",2,livello);

		livello = portale.getLivelloCompetenza(numInserzione, "Saper gestire la contabilita' di una concessionaria");

	 	System.out.println("\nCerco livello per competenza Saper gestire la contabilita' di una concessionaria");
	 	System.out.println("Livello "+livello);
	 	if(livello==3)
	 	    System.out.println("Livello corretto");
	 	else
	 	    System.out.println("Livello errato");
	 	    
	 	assertEquals("Metodo getLivelloCompetenza() di Portale non implementato correttamente.",3,livello);
        
	}

	

}
