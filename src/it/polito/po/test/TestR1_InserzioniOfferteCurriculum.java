package it.polito.po.test;

import lavoro.*;

import junit.framework.TestCase;
import java.util.*;

public class TestR1_InserzioniOfferteCurriculum extends TestCase {

	Portale portale;
	
	public void setUp(){
		portale = new Portale();
    }
	
	public void testAggiungiOffertaDiLavoro() {

		System.out.println("\n*** testAggiungiOffertaDiLavoro() ***\n");

		System.out.println("Aggiungo offerta di lavoro");
		int numInserzione = portale.aggiungiOffertaDiLavoro("Auto & figli", "Venditore", "Milano", "20130201", "20130228", 2);

		System.out.println("Aggiunta offerta di lavoro numero "+numInserzione);

		if(numInserzione==10000)
			System.out.println("Numero inserzione corretto");
		else
			System.out.println("Numero inserzione errato");
			
		assertEquals("Codice numerico assegnato all'offerta aggiunta non corretto.",10000,numInserzione);
	}
	
	public void testGetInserzioneOffertaDiLavoro() throws InserzioneInesistenteExcepion {

		System.out.println("\n*** testGetInserzioneOffertaDiLavoro() ***\n");

		System.out.println("Aggiungo offerta di lavoro");
		int numInserzione = portale.aggiungiOffertaDiLavoro("Auto & figli", "Venditore", "Milano", "20130201", "20130228", 2);
		System.out.println("Aggiunta offerta di lavoro numero "+numInserzione);

		System.out.println("Recupero inserzione numero "+numInserzione);
		Inserzione inserzione = portale.getInserzione(numInserzione);

        assertNotNull("Metodo getInserzione() non implementato correttamente (null).",inserzione);

        OffertaDiLavoro offerta = null;

        boolean eccezione=false;
        
        try{
        	offerta = (OffertaDiLavoro) inserzione;
        	offerta.getClass();
        	System.out.println("Offerta di lavoro correttamente recuperata");
        }
        catch(Exception e)
        {
        	eccezione=true;
        	System.out.println("Errore nel recuperare l'offerta di lavoro");
        }
        assertEquals("Metodo getInserzione() non implementato correttamente (cast a OffertaDiLavoro).",false,eccezione);
	}
	
	public void testDettagliOffertaDiLavoro() throws InserzioneInesistenteExcepion {

		System.out.println("\n*** testDettagliOffertaDiLavoro() ***\n");

		System.out.println("Aggiungo offerta di lavoro");
		int numInserzione = portale.aggiungiOffertaDiLavoro("Auto & figli", "Venditore", "Milano", "20130201", "20130228", 2);
		System.out.println("Aggiunta offerta di lavoro numero "+numInserzione);

        OffertaDiLavoro offerta = (OffertaDiLavoro) portale.getInserzione(numInserzione);

		assertEquals("Metodo getCodice() non implementato correttamente.",10000,offerta.getCodice());
		assertEquals("Metodo getDatoreDiLavoro() non implementato correttamente.","Auto & figli",offerta.getDatoreDiLavoro());
		assertEquals("Metodo getFigura() non implementato correttamente.","Venditore",offerta.getFigura());
		assertEquals("Metodo getSede() non implementato correttamente.","Milano",offerta.getSede());
		assertEquals("Metodo getDataInizioValidita() non implementato correttamente.","20130201",offerta.getDataInizioValidita());
		assertEquals("Metodo getDataFineValidita() non implementato correttamente.","20130228",offerta.getDataFineValidita());
		assertEquals("Metodo getNumPosizioniAperte() non implementato correttamente.",2,offerta.getNumPosizioniAperte());

		System.out.println(" Datore di lavoro: "+offerta.getDatoreDiLavoro());
		System.out.println(" Figura professionale: "+offerta.getFigura());
		System.out.println(" Sede: "+offerta.getSede());
		System.out.println(" Validita': dal "+offerta.getDataInizioValidita()+" al "+offerta.getDataFineValidita());
		System.out.println(" Posizioni aperte: "+offerta.getNumPosizioniAperte());
	
	}
	
	public void testGetInserzioneInesistente() {

		System.out.println("\n*** testGetInserzioneInesistente() ***\n");
		
		System.out.println("Aggiungo offerta di lavoro");
		int numInserzione = portale.aggiungiOffertaDiLavoro("Auto & figli", "Venditore", "Milano", "20130201", "20130228", 2);
		System.out.println("Aggiunta offerta di lavoro numero "+numInserzione);

		System.out.println("Recupero inserzione numero "+15000+" (inesistente)");

		boolean eccezione=false;
        try {
		    portale.getInserzione(15000);
		} 
        catch (InserzioneInesistenteExcepion e) {
        	eccezione=true;
		}
        
        if(eccezione)
        	System.out.println("Inserzione 15000 inesistente (corretto)"); 
        else
        	System.out.println("Errore nella gestione di un'inserzione inesistente"); 
        
		assertEquals("Eccezione InserzioneInesistenteExcepion non gestita correttamente.",true,eccezione);
	}
	
	public void testSetDataFineValidita() throws InserzioneInesistenteExcepion {

		System.out.println("\n*** testSetDataFineValidita() ***\n");

		System.out.println("Aggiungo offerta di lavoro");
		int numInserzione = portale.aggiungiOffertaDiLavoro("Auto & figli", "Venditore", "Milano", "20130201", "20130228", 2);
		System.out.println("Aggiunta offerta di lavoro numero "+numInserzione);
		
        OffertaDiLavoro offerta = (OffertaDiLavoro) portale.getInserzione(numInserzione);

		System.out.println(" Validita': dal "+offerta.getDataInizioValidita()+" al "+offerta.getDataFineValidita());

		System.out.println("\nEstensione data fine validita al 20130227");
		offerta.setDataFineValidita("20130227");
		System.out.println(" Validita': dal "+offerta.getDataInizioValidita()+" al "+offerta.getDataFineValidita());
		assertEquals("Metodo setDataFineValidita() non implementato correttamente.","20130228",offerta.getDataFineValidita());

		System.out.println("\nEstensione data fine validita al 20130315 (non valida)");
		offerta.setDataFineValidita("20130315");
		System.out.println(" Validita': dal "+offerta.getDataInizioValidita()+" al "+offerta.getDataFineValidita());
		assertEquals("Metodo setDataFineValidita() non implementato correttamente.","20130315",offerta.getDataFineValidita());

	}

	public void testGetInserzioneCurriculum() throws InserzioneInesistenteExcepion {

		System.out.println("\n*** testGetInserzioneCurriculum() ***\n");

		System.out.println("Aggiungo curriculum");
		int numInserzione = portale.aggiungiCurriculum("RSSMRO75P11T565E", "Mario", "Rossi", "19750623", "Roma");
		System.out.println("Aggiunto curriculum numero "+numInserzione);

		Inserzione inserzione = portale.getInserzione(numInserzione);
		System.out.println("Recupero inserzione numero "+numInserzione);

        Curriculum curriculum = null;
        try{
        	curriculum = (Curriculum) inserzione;
        	System.out.println("Curriculum correttamente recuperato");
        }
        catch(Exception e)
        {
        	System.out.println("Errore nel recuperare il curriculum");
        }
        
        assertNotNull("Metodo getInserzione() non implementato correttamente (cast a Curriculum).",curriculum);
	}

	public void testDettagliCurriculum() throws InserzioneInesistenteExcepion {

		System.out.println("\n*** testDettagliCurriculum() ***\n");

		System.out.println("Aggiungo curriculum");
		int numInserzione = portale.aggiungiCurriculum("RSSMRO75P11T565E", "Mario", "Rossi", "19750623", "Roma");
		System.out.println("Aggiunto curriculum numero "+numInserzione);

        Curriculum curriculum = (Curriculum) portale.getInserzione(numInserzione);

		assertEquals("Metodo getCodiceFiscale() non implementato correttamente.","RSSMRO75P11T565E",curriculum.getCodiceFiscale());
		assertEquals("Metodo getNome() non implementato correttamente.","Mario",curriculum.getNome());
		assertEquals("Metodo getCognome() non implementato correttamente.","Rossi",curriculum.getCognome());
		assertEquals("Metodo getDataNascita() non implementato correttamente.","19750623",curriculum.getDataNascita());
		assertEquals("Metodo getResidenza() non implementato correttamente.","Roma",curriculum.getResidenza());

		System.out.println(" Codice fiscale: "+curriculum.getCodiceFiscale());
		System.out.println(" Nome: "+curriculum.getNome());
		System.out.println(" Cognome: "+curriculum.getCognome());
		System.out.println(" Data di nascita: "+curriculum.getDataNascita());
		System.out.println(" Residenza: "+curriculum.getResidenza());
	
	}

	public void testGetCurriculum() {

		System.out.println("\n*** testGetCurriculum() ***\n");
		
		System.out.println("Aggiungo curriculum");
		portale.aggiungiCurriculum("RSSMRO75P11T565E", "Mario", "Rossi", "19750623", "Roma");
		System.out.println("Aggiunto curriculum per codice fiscale RSSMRO75P11T565E");

		System.out.println("Recupero curriculum per codice fiscale RSSMRO75P11T565E");

		boolean eccezione=false;
        try {
		    portale.getCurriculum("RSSMRO75P11T565E");
		} 
        catch (InserzioneInesistenteExcepion e) {
        	eccezione=true;
        	System.out.println("Curriculum per codice fiscale RSSMRO75P11T565E inesistente"); 
		}
		assertEquals("Eccezione InserzioneInesistenteExcepion non gestita correttamente.",false,eccezione);

		System.out.println("Recupero curriculum per codice fiscale BNCALB66P23S256T");

	    eccezione=false;
        try {
		    portale.getCurriculum("BNCALB66P23S256T");
		} 
        catch (InserzioneInesistenteExcepion e) {
        	eccezione=true;
        	System.out.println("Curriculum per codice fiscale BNCALB66P23S256T inesistente"); 
		}
		assertEquals("Eccezione InserzioneInesistenteExcepion non gestita correttamente.",true,eccezione);
	
	}

	public void testAggiungiPiuInserzioni() {

		System.out.println("\n*** testAggiungiPiuInserzioni() ***\n");

		System.out.println("Aggiungo piu' inserzioni");
		
		int numInserzione = portale.aggiungiOffertaDiLavoro("Auto & figli", "Venditore", "Milano", "20130201", "20130228", 2);
		System.out.println("Aggiunta inserzione numero "+numInserzione);

			numInserzione = portale.aggiungiCurriculum("RSSMRO75P11T565E", "Mario", "Rossi", "19750623", "Roma");
		System.out.println("Aggiunta inserzione numero "+numInserzione);

		assertEquals("Codice numerico assegnato alla seconda inserzione aggiunta non corretto.",10001,numInserzione);

			numInserzione = portale.aggiungiOffertaDiLavoro("Muri S.N.C.", "Muratore", "Bergamo", "20130110", "20130430", 5);
		System.out.println("Aggiunta inserzione numero "+numInserzione);

		assertEquals("Codice numerico assegnato alla terza inserzione aggiunta non corretto.",10002,numInserzione);
	}

	public void testDecrementaPosizioniAperte() throws InserzioneInesistenteExcepion {

		System.out.println("\n*** testDecrementaPosizioniAperte() ***\n");

		System.out.println("Aggiungo offerta di lavoro");
		int numInserzione = portale.aggiungiOffertaDiLavoro("Auto & figli", "Venditore", "Milano", "20130201", "20130228", 2);
		System.out.println("Aggiunta inserzione numero "+numInserzione);

		OffertaDiLavoro offerta = (OffertaDiLavoro) portale.getInserzione(numInserzione);

		System.out.println("Posizioni aperte: "+offerta.getNumPosizioniAperte());

		System.out.println("\nDecremento posizioni aperte");
		
		offerta.decrementaPosizioniAperte();

		if(offerta.getNumPosizioniAperte()==1)
			System.out.println("Posizioni aperte: "+offerta.getNumPosizioniAperte()+" (corretto)");
		else
			System.out.println("Posizioni aperte: "+offerta.getNumPosizioniAperte()+" (errato)");
			
		assertEquals("Metodo testDecrementaPosizioniAperte() non implementato correttamente.",1,offerta.getNumPosizioniAperte());

		System.out.println("\nDecremento posizioni aperte");
		offerta.decrementaPosizioniAperte();

		if(offerta.getNumPosizioniAperte()==0)
			System.out.println("Posizioni aperte: "+offerta.getNumPosizioniAperte()+" (corretto)");
		else
			System.out.println("Posizioni aperte: "+offerta.getNumPosizioniAperte()+" (errato)");
		
		assertEquals("Metodo testDecrementaPosizioniAperte() non implementato correttamente.",0,offerta.getNumPosizioniAperte());

		System.out.println("\nDecremento posizioni aperte");
		offerta.decrementaPosizioniAperte();

		if(offerta.getNumPosizioniAperte()==0)
			System.out.println("Posizioni aperte: "+offerta.getNumPosizioniAperte()+" (corretto)");
		else
			System.out.println("Posizioni aperte: "+offerta.getNumPosizioniAperte()+" (errato)");
		
		assertEquals("Metodo testDecrementaPosizioniAperte() non implementato correttamente.",0,offerta.getNumPosizioniAperte());

	}
	
	public void testElencoInserzioni() {
		System.out.println("\n*** testElencoInserzioni() ***\n");

		System.out.println("Aggiungo piu' inserzioni");
		portale.aggiungiOffertaDiLavoro("Auto & figli", "Venditore", "Milano", "20130201", "20130228", 2);
		portale.aggiungiOffertaDiLavoro("Muri S.N.C.", "Muratore", "Bergamo", "20130110", "20130430", 5);
		portale.aggiungiCurriculum("RSSMRO75P11T565E", "Mario", "Rossi", "19750623", "Roma");
		portale.aggiungiOffertaDiLavoro("Speedy auto", "Contabile", "Venezia", "20121124", "20131231", 1);
		portale.aggiungiOffertaDiLavoro("Sartoria & Co.", "Sarto", "Napoli", "20130110", "20140630", 1);
     	portale.aggiungiCurriculum("BLUMRC46T06B678G", "Marco", "Blu", "19460106", "Palermo");

     	List<Inserzione> inserzioni = new LinkedList<Inserzione>(portale.elencoInserzioni());    
		
		assertNotNull("Metodo testElencoInserzioni() non implementato correttamente (null).",inserzioni);

		int n = inserzioni.size();
		
		System.out.println("Aggiunte "+n+" inserzioni");
		
		assertEquals("Numero di inserzioni aggiunte non corretto.",6,n);
     	
		Inserzione prima = inserzioni.get(0);
		Inserzione seconda = inserzioni.get(1);
		Inserzione ultima = inserzioni.get(n-1);

		for(int i=0;i<n;i++)
			System.out.println(" "+inserzioni.get(i).getCodice());
		
		assertEquals("Ordine inserzioni non corretto.",10000,prima.getCodice());
		assertEquals("Ordine inserzioni non corretto.",10001,seconda.getCodice());
		assertEquals("Ordine inserzioni non corretto.",10005,ultima.getCodice());
		
	}

	public void testElencoOfferteDiLavoro() {
		System.out.println("\n*** testElencoOfferteDiLavoro() ***\n");

		System.out.println("Aggiungo piu' inserzioni");
		portale.aggiungiOffertaDiLavoro("Auto & figli", "Venditore", "Milano", "20130201", "20130228", 2);
		portale.aggiungiOffertaDiLavoro("Muri S.N.C.", "Muratore", "Bergamo", "20130110", "20130430", 5);
		portale.aggiungiCurriculum("RSSMRO75P11T565E", "Mario", "Rossi", "19750623", "Roma");
		portale.aggiungiOffertaDiLavoro("Speedy auto", "Contabile", "Venezia", "20121124", "20131231", 1);
		portale.aggiungiOffertaDiLavoro("Sartoria & Co.", "Sarto", "Napoli", "20130110", "20140630", 1);
     	portale.aggiungiCurriculum("BLUMRC46T06B678G", "Marco", "Blu", "19460106", "Palermo");

     	List<Inserzione> inserzioni = new LinkedList<Inserzione>(portale.elencoInserzioni());    
		
		int n = inserzioni.size();
		
		System.out.println("Aggiunte "+n+" inserzioni");
     	
     	List<OffertaDiLavoro> offerte = new LinkedList<OffertaDiLavoro>(portale.elencoOfferteDiLavoro());    
		
		assertNotNull("Metodo testElencoOfferteDiLavoro() non implementato correttamente (null).",offerte);

	    n = offerte.size();
		
		System.out.println("Di queste, "+n+" sono offerte di lavoro");
		
		assertEquals("Numero di offerte di lavoro aggiunte non corretto.",n,4);
     	
		OffertaDiLavoro prima = offerte.get(0);
		OffertaDiLavoro seconda = offerte.get(1);
		OffertaDiLavoro ultima = offerte.get(n-1);

		for(int i=0;i<n;i++)
			System.out.println(" "+offerte.get(i).getCodice()+" valida dal "+offerte.get(i).getDataInizioValidita()+" al "+offerte.get(i).getDataFineValidita());
		
		assertEquals("Ordine offerte di lavoro non corretto.",10003,prima.getCodice());
		assertEquals("Ordine offerte di lavoro non corretto.",10001,seconda.getCodice());
		assertEquals("Ordine offerte di lavoro non corretto.",10000,ultima.getCodice());
		
	}
	
	public void testElencoCurriculum() {
		System.out.println("\n*** testElencoCurriculum() ***\n");

		System.out.println("Aggiungo piu' inserzioni");
     	portale.aggiungiCurriculum("VRDANN58R24N754S", "Anna", "Verdi", "19580624", "Torino");
		portale.aggiungiOffertaDiLavoro("Auto & figli", "Venditore", "Milano", "20130201", "20130228", 2);
		portale.aggiungiOffertaDiLavoro("Muri S.N.C.", "Muratore", "Bergamo", "20130110", "20130430", 5);
		portale.aggiungiCurriculum("RSSMRO75P11T565E", "Mario", "Rossi", "19750623", "Roma");
		portale.aggiungiOffertaDiLavoro("Speedy auto", "Contabile", "Venezia", "20121124", "20131231", 1);
     	portale.aggiungiCurriculum("VRDGLO66Y12T567W", "Giulio", "Verdi", "19660212", "Milano");
		portale.aggiungiOffertaDiLavoro("Sartoria & Co.", "Sarto", "Napoli", "20130110", "20140630", 1);
     	portale.aggiungiCurriculum("BLUMRC46T06B678G", "Marco", "Blu", "19460106", "Palermo");
     	portale.aggiungiCurriculum("VRDGLO77B24T123A", "Giulio", "Verdi", "19770324", "Alessandria");

     	List<Inserzione> inserzioni = new LinkedList<Inserzione>(portale.elencoInserzioni());    
		
		int n = inserzioni.size();
		
		System.out.println("Aggiunte "+n+" inserzioni");
     	
     	List<Curriculum> cvs = new LinkedList<Curriculum>(portale.elencoCurriculum());    
		
		assertNotNull("Metodo testElencoCurriculum() non implementato correttamente (null).",cvs);

	    n = cvs.size();
		
		System.out.println("Di queste, "+n+" sono curriculum");
		
		assertEquals("Numero di curriculum aggiunti non corretto.",n,5);
     	
		Curriculum prima = cvs.get(0);
		Curriculum seconda = cvs.get(1);
		Curriculum ultima = cvs.get(n-1);

		for(int i=0;i<n;i++)
			System.out.println(" "+cvs.get(i).getCodice()+" "+cvs.get(i).getCodiceFiscale()+" "+cvs.get(i).getCognome()+" "+cvs.get(i).getNome()+" "+cvs.get(i).getResidenza());
		
		assertEquals("Ordine curriculum non corretto.",10007,prima.getCodice());
		assertEquals("Ordine curriculum non corretto.",10003,seconda.getCodice());
		assertEquals("Ordine curriculum non corretto.",10005,ultima.getCodice());
	}
	
	
	public void testRimuoviInserzione() throws InserzioneInesistenteExcepion {
		System.out.println("\n*** testRimuoviInserzione() ***\n");

		System.out.println("Aggiungo piu' inserzioni");
		portale.aggiungiOffertaDiLavoro("Auto & figli", "Venditore", "Milano", "20130201", "20130228", 2);
		portale.aggiungiOffertaDiLavoro("Muri S.N.C.", "Muratore", "Bergamo", "20130110", "20130430", 5);
		portale.aggiungiCurriculum("RSSMRO75P11T565E", "Mario", "Rossi", "19750623", "Roma");
		portale.aggiungiOffertaDiLavoro("Speedy auto", "Contabile", "Venezia", "20121124", "20131231", 1);
		portale.aggiungiOffertaDiLavoro("Sartoria & Co.", "Sarto", "Napoli", "20130110", "20140630", 1);
     	portale.aggiungiCurriculum("BLUMRC46T06B678G", "Marco", "Blu", "19460106", "Palermo");

     	List<Inserzione> inserzioni = new LinkedList<Inserzione>(portale.elencoInserzioni());    

		int n = inserzioni.size();
		
		System.out.println("Aggiunte "+n+" inserzioni");
     	
		for(int i=0;i<n;i++)
			System.out.println(" "+inserzioni.get(i).getCodice());

		System.out.println("\nRimuovo inserzione 10002");
		System.out.println("Rimuovo inserzione 10004");
		
		portale.rimuoviInserzione(10002);
		portale.rimuoviInserzione(10004);
		
     	inserzioni = new LinkedList<Inserzione>(portale.elencoInserzioni());    

     	n = inserzioni.size();
		
		System.out.println("\nRimaste "+n+" inserzioni");

		for(int i=0;i<n;i++)
			System.out.println(" "+inserzioni.get(i).getCodice());
		
		assertEquals("Numero di inserzioni rimaste non corretto.",4,n);

		int rimaste=0;
		for(int i=0;i<n;i++)
			if(inserzioni.get(i).getCodice()==10000 || inserzioni.get(i).getCodice() == 10001 || inserzioni.get(i).getCodice() == 10003 || inserzioni.get(i).getCodice() == 10005 )
				rimaste++;

		assertEquals("Inserzioni rimaste non corrette.",4,rimaste);
		
		
	}
	
}
