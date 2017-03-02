import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Scanner;

/**
 * @author davidtorralbo
 *
 */
public class AnysLlum {
	
	//creem una classe on guardarem l'informació de la Nau i on farem els càlculs
	public static class Nau {
		
	    double velocitat = 0;
	    String nom;
	    
	    //velocitat km/h
	    void velocitat(double entrada) {
	         velocitat = entrada;
	    }

	    //el nom de la nau
	    void nom(String entrada) {
	         nom = entrada;
	    }
	    
	    //mostrem la solució
	    void mostraSolucio(long[] ls){
	    	System.out.println("\nLa nau tardarà "+ls[0]+" anys, "+ls[1]+" dies, "+ls[2]+" hores, "+ls[3]+" minuts i "+ls[4]+" segons en arribar al seu destí.");
	    }

	    //passem els anys llum a segons
	    double passarAsegons(double anysllum) {
	    	
	    	double anysllumAkm = 9460730472580.8;
	    	double tempsH = (anysllumAkm*anysllum)/velocitat;
	    	double tempsS = tempsH*3600/1;
	    	
			return tempsS;
	    }
	    
	    
	}
	
	public static void main(String[] args) {
		
		InicialitzaPrograma();

	}
	
	public static void InicialitzaPrograma(){
		
		char seleccio = 0;
		double anysllum = 0;
		boolean seguir = false;
		
		Scanner sc = new Scanner(System.in);
		
		//Escollim nau
		do{
			try{
				System.out.print("Quina nau vols utilitzar?\nHelios 1 (A), Voyager 1 (B), Deep Space 1 (C): ");
				seleccio = sc.nextLine().charAt(0);
				
				//comprovem que l'entrada coincideixi amb una d'aquestes
				if(seleccio != 'A' && seleccio != 'a' && seleccio != 'B' && seleccio != 'b' && seleccio != 'C' && seleccio != 'c'){
					throw new Exception();
				}
				seguir = false;
				
			} catch(Exception e){
				System.out.println("\nLa selecció del menú ha de ser A, B o C.");
				seguir = true;
			}
		}while(seguir);
		
		//Escollim els anys llum
		do{
			try{
				System.out.print("\nA quants anys llum de distància es troba el teu destí?: ");
				anysllum = Double.parseDouble(sc.nextLine());
				seguir = false;
			}catch(Exception e){
				System.out.println("\nEls anys llum han de ser nombres. Si vol utilitzar decimal, si us plau, utilitzi el punt.");
				seguir = true;
			}
		}while(seguir);
		
		/*
		 * afegim una nova nau (només en necessitem una degut a que depenent del switch s'escull una nau o una altra.
		 */
        Nau nau = new Nau();
		
        //depenent de la lletra entrada, afegirem uns atributs a la nau o uns altres.
		switch(seleccio){
		
			case 'A': case 'a':
				
				nau.velocitat(274510);
		        nau.nom("Helios 1");
		        nau.mostraSolucio(FormataSegons(nau.passarAsegons(anysllum)));
				break;
				
			case 'B': case 'b':
				
				nau.velocitat(62140);
		        nau.nom("Voyager 1");
		        nau.mostraSolucio(FormataSegons(nau.passarAsegons(anysllum)));
				break;
				
			case 'C': case 'c':
				
				nau.velocitat(16200);
		        nau.nom("Deep Space 1");
		        nau.mostraSolucio(FormataSegons(nau.passarAsegons(anysllum)));
				break;
			default:  
				System.out.println("\nNo s'ha pogut realitzar el càlcul.");
				break;
		}
		   
	}
	
	 //passem els segons a anys, dies, hores, minuts i segons
    public static long[] FormataSegons(double segons){

    	/*
		 * Formatem el decimal de forma que quan hi hagi algun zero, exemple: 3.0, mostri solament el 3.
		 * També es mostrarà un punt (.) en lloc d'una coma en els decimals.
		 */
		DecimalFormat df = new DecimalFormat("###", new DecimalFormatSymbols(Locale.US));
		
	    double minuts = segons / 60;
	    double segonsRestants = segons % 60;
	    double hores = minuts / 60;
	    double minutsRestants = minuts % 60;
	    double dies = hores / 24;
	    double horesRestants = hores % 24;
	    double anys = dies / 365;
	    double diesRestants = dies % 365;
	
	    /*
	     * Retornem dins un array long el temps que tardarà la nau en recórrer els segons.
	     * Fem un cast de long degut a que volem evitar els decimals del double a l'hora d'imprimir els resultats.
	    */
	    return new long[] {(long) Double.parseDouble(df.format(anys)), (long) Double.parseDouble(df.format(diesRestants)), (long) Double.parseDouble(df.format(horesRestants)), (long) Double.parseDouble(df.format(minutsRestants)), (long) Double.parseDouble(df.format(segonsRestants))};
    
    }

}
