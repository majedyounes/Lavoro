package it.polito.po.test;

import java.io.*;
import java.util.*;

import junit.framework.TestCase;
import lavoro.*;

public class TestR4_CaricamentoDaFile extends TestCase {

	Portale portale;
	
	public void setUp(){
		portale = new Portale();
    }
	  
	  /**
	   * Create a new temporary file and write the content
	   * @param content
	   * @return the path of the new file.
	   * @throws IOException
	   */
	  private static String writeFile(String content) throws IOException {          
	          File f = File.createTempFile("off","txt");
	          FileOutputStream fos = new FileOutputStream(f);
	          fos.write(content.getBytes());
	          fos.close();
	          return f.getAbsolutePath();
	    }

	  
	  public void testCaricamentoRigaSingola() throws IOException, InserzioneInesistenteExcepion {
		  	
			System.out.println("\n*** testCaricamentoRigaSingola() ***\n");
	  
			String normale = "C;VRDANN58R24N754S;Anna;Verdi;19580624;Torino";
		  	
		  	String file = writeFile(normale);

		  	portale.leggiFile(file);

		  	Curriculum curriculum = (Curriculum)portale.getInserzione(10000);
		  	
		  	assertNotNull("Numero di inserzioni lette non corretto.", curriculum);

		  	if(curriculum!=null){
		  		System.out.println("Letto curriculum "+curriculum.getCodice());
		  		System.out.println(" Codice fiscale: "+curriculum.getCodiceFiscale());
				System.out.println(" Nome: "+curriculum.getNome());
				System.out.println(" Cognome: "+curriculum.getCognome());
				System.out.println(" Data di nascita: "+curriculum.getDataNascita());
				System.out.println(" Residenza: "+curriculum.getResidenza());
		  	}
		  	else
		  		System.out.println("Errore nella lettura dal file");
		  		
			boolean corretto = false;
		  	if(curriculum.getCodiceFiscale().equals("VRDANN58R24N754S") && curriculum.getNome().equals("Anna") && curriculum.getCognome().equals("Verdi") && curriculum.getDataNascita().equals("19580624") && curriculum.getResidenza().equals("Torino")){
		  		corretto=true;
		  		System.out.println("\nInformazioni lette correttamente");
		  	}
		  	else
		  		System.out.println("\nErrore nella lettura delle informzioni relative al curriculum");
		  	
		  	assertEquals("Metodo leggiFile() non implementato correttamente.",  true,corretto);
	  }
	  
	  public void testCaricamentoRigheMultiple() throws IOException {
	  	
		System.out.println("\n*** testCaricamentoRigheMultiple() ***\n");
  
		String normale = "C;VRDGLO66Y12T567W;Giulio;Verdi;19660212;Milano\n" +
						 "O;Maglie al Sole;Sarto;Savona;20120814;20120905;2\n" + 
						 "C;VRDANN58R24N754S;Anna;Verdi;19580624;Torino";
	  	
	  	String file = writeFile(normale);

	  	portale.leggiFile(file);

	  	List<Inserzione> inserzioni = new LinkedList<Inserzione>(portale.elencoInserzioni());
	  	
	  	assertNotNull("Numero di inserzioni lette non corretto.", inserzioni);
	  	
	  	int n = inserzioni.size();
	  	
	  	System.out.println("Lette "+n+" inserzioni");
	  	
	  	assertEquals("Numero di inserzioni lette non corretto.", 3,n);
	  	
		System.out.println("\nInserzioni lette:");    
		for(int i=0;i<inserzioni.size();i++){
			if(inserzioni.get(i) instanceof OffertaDiLavoro){
				OffertaDiLavoro o = (OffertaDiLavoro)inserzioni.get(i);
				System.out.println(" "+o.getCodice()+" "+o.getDatoreDiLavoro()+" cerca "+o.getFigura()+" a "+o.getSede()+" valida dal "+o.getDataInizioValidita()+" al "+o.getDataFineValidita());
			}
			else if(inserzioni.get(i) instanceof Curriculum){
				Curriculum c = (Curriculum)inserzioni.get(i);
				System.out.println(" "+c.getCodiceFiscale()+" "+c.getCognome()+" "+c.getNome()+" nato il "+c.getDataNascita()+" residente a "+c.getResidenza());
			}
		}
	  	
	  	boolean corretto = false;
	  	
	  	if(((Curriculum)inserzioni.get(0)).getCodiceFiscale().equals("VRDGLO66Y12T567W") && ((OffertaDiLavoro)inserzioni.get(1)).getFigura().equals("Sarto") && ((Curriculum)inserzioni.get(2)).getResidenza().equals("Torino")){
	  		corretto=true;
	  		System.out.println("\nInformazioni lette correttamente");
	  	}
	  	else
	  		System.out.println("\nErrore nella lettura delle informzioni relative alle inserzioni");

	  	
	  	assertEquals("Metodo leggiFile() non implementato correttamente.",  true,corretto);
	  }
	  
	  public void testInizialiESpazi() throws IOException {

			System.out.println("\n*** testInizialiESpazi() ***\n");

		    String normale = "C;   VRDGLO66Y12T567W;Giulio;Verdi;19660212;Milano\n" +
							 "o;Maglie al Sole;Sarto;Savona;20120814;20120905;2\n" + 
							 "c;VRDANN58R24N754S;Anna;Verdi;19580624;   Torino  ";
		  	
		  	String file = writeFile(normale);

		  	portale.leggiFile(file);

		  	List<Inserzione> inserzioni = new LinkedList<Inserzione>(portale.elencoInserzioni());
		  	
		  	assertEquals("Numero di inserzioni lette non corretto.",3, inserzioni.size());

			System.out.println("Inserzioni lette (gestendo 'O', 'o', 'C', 'c' e spazi):");    
			for(int i=0;i<inserzioni.size();i++){
				if(inserzioni.get(i) instanceof OffertaDiLavoro){
					OffertaDiLavoro o = (OffertaDiLavoro)inserzioni.get(i);
					System.out.println(" "+o.getCodice()+" "+o.getDatoreDiLavoro()+" cerca "+o.getFigura()+" a "+o.getSede()+" valida dal "+o.getDataInizioValidita()+" al "+o.getDataFineValidita());
				}
				else if(inserzioni.get(i) instanceof Curriculum){
					Curriculum c = (Curriculum)inserzioni.get(i);
					System.out.println(" "+c.getCodiceFiscale()+" "+c.getCognome()+" "+c.getNome()+" nato il "+c.getDataNascita()+" residente a "+c.getResidenza());
				}
			}

		  	boolean corretto = false;
		  	
		  	if(((Curriculum)inserzioni.get(0)).getCodiceFiscale().equals("VRDGLO66Y12T567W") && ((OffertaDiLavoro)inserzioni.get(1)).getFigura().equals("Sarto") && ((Curriculum)inserzioni.get(2)).getResidenza().equals("Torino")){
		  		corretto=true;
		  		System.out.println("\nInformazioni lette correttamente");
		  	}
		  	else
		  		System.out.println("\nErrore nella lettura delle informzioni relative alle inserzioni");
		  		
		  	assertEquals("Metodo leggiFile() non implementato correttamente.", true, corretto);
		  }
	  
	  public void testFormatoRigheErrato() throws IOException {

			System.out.println("\n*** testFormatoRigheErrato() ***\n");

		    String normale = "C;   VRDGLO66Y12T567W;Giulio;Verdi;19660212;Milano\n" +
        					 "O;Zuppe al vapore;Assaggiatore;20120423;\n" + 
		      				 "o;Maglie al Sole;Sarto;Savona;20120814;20120905;2\n" + 
							 "\n" + 
							 "c;VRDANN58R24N754S;Verdi;19580624;   Torino  ";
		  	
		  	String file = writeFile(normale);

		  	portale.leggiFile(file);

		  	List<Inserzione> inserzioni = new LinkedList<Inserzione>(portale.elencoInserzioni());
		  	
		  	assertEquals("Numero di inserzioni lette non corretto.", 2,inserzioni.size());
		  	
			System.out.println("Inserzioni lette (ignorando le due righe nel formato errato):");    
			for(int i=0;i<inserzioni.size();i++){
				if(inserzioni.get(i) instanceof OffertaDiLavoro){
					OffertaDiLavoro o = (OffertaDiLavoro)inserzioni.get(i);
					System.out.println(" "+o.getCodice()+" "+o.getDatoreDiLavoro()+" cerca "+o.getFigura()+" a "+o.getSede()+" valida dal "+o.getDataInizioValidita()+" al "+o.getDataFineValidita());
				}
				else if(inserzioni.get(i) instanceof Curriculum){
					Curriculum c = (Curriculum)inserzioni.get(i);
					System.out.println(" "+c.getCodiceFiscale()+" "+c.getCognome()+" "+c.getNome()+" nato il "+c.getDataNascita()+" residente a "+c.getResidenza());
				}
			}

		  	boolean corretto = false;
		  	
		  	if(((Curriculum)inserzioni.get(0)).getCodiceFiscale().equals("VRDGLO66Y12T567W") && ((OffertaDiLavoro)inserzioni.get(1)).getFigura().equals("Sarto")){
		  		if(((OffertaDiLavoro)inserzioni.get(1)).getDatoreDiLavoro().equals("Maglie al Sole") && ((OffertaDiLavoro)inserzioni.get(1)).getFigura().equals("Sarto") && ((OffertaDiLavoro)inserzioni.get(1)).getNumPosizioniAperte() == 2){
    		  		corretto=true;
		  		}
		  	}

		  	if(corretto)
		  		System.out.println("\nInformazioni lette correttamente");
		  	else
		  		System.out.println("\nErrore nella lettura delle informzioni relative alle inserzioni");

		  	assertEquals("Metodo leggiFile() non implementato correttamente.", true,corretto);
		  }
	  


}

