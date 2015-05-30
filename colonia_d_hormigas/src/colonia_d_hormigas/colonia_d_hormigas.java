package colonia_d_hormigas;

import java.util.*;

public class  colonia_d_hormigas {
	public static Scanner leer;
		public static void main(String[] args) {
			
			leer = new Scanner(System.in);
			int[][] Red={{ 0, 4, 6, 3,-1,-1,-1,-1,-1,-1,-1},
					     { 4, 0, 5,-1, 3,-1,-1,-1,-1,-1,-1},
					     { 6, 5, 0, 4, 2, 2, 5,-1,-1,-1,-1},
					     { 3,-1, 4, 0,-1,-1, 6,-1,-1,-1,-1},
					     {-1, 3, 2,-1, 0, 2,-1, 4,-1,-1,-1},
					     {-1,-1, 2,-1, 2, 0, 1, 2, 5,-1,-1},
					     {-1,-1, 5, 6,-1, 1, 0,-1, 2, 5,-1},
					     {-1,-1,-1,-1, 4, 2,-1, 0, 2,-1, 7},
					     {-1,-1,-1,-1,-1, 5, 2, 2, 0, 3, 8},
					     {-1,-1,-1,-1,-1,-1, 5,-1, 3, 0, 4},
					     {-1,-1,-1,-1,-1,-1,-1, 7, 8, 4, 0}};// Red de transporte
			int Num_hormigas; // Número de hormigas que se van a pasar por la red para determina el costo minimo
			int nodoFinal;
			
			Mostrar_red(Red); 
			
			System.out.println("Definir el número de hormigas");
			Num_hormigas = leer.nextInt();	
			System.out.println("Escribir Nodo final");
			nodoFinal = leer.nextInt();
			
			
			//for (int i = 0; i < Num_hormigas; i++) {
		    //System.out.println("Hormiga "+(i+1));
				recorrer(Red,nodoFinal);
				//System.out.println();
			//}
				
	}
	
public static void Mostrar_red(int Red[][]){
	System.out.println("Red de transporte");
	
	for (int i = 0; i < Red.length; i++) {  // Muestra la Red de transporte 
	
		for (int j = 0; j < Red.length; j++) {
			System.out.print(Red[i][j]+"\t");
		}
		System.out.println();
	}
	
	System.out.println();
}
	
public static void recorrer(int Red[][], int nodoFinal){

	int nodo=0;       // nodo de la red 
	int columna=0;    // columna de la red
	int evaluacion=0;
	double [][] feromona = new double[Red.length][Red.length];
	int [][] red_tenporal = new int[Red.length][Red.length];// red temporal para recorrido de las hormigas
	    
	   
	   	   red_tenporal=Red.clone(); //Genera copia temporal
	
	  
	   		for (int i = 0; i < feromona.length; i++) {
	   			for (int j = 0; j < feromona.length; j++) {
	   				feromona[i][j]=1;
	   			}
	   		}
	   	
	    
	   while(nodo!=nodoFinal){
	    	
	    	elegir_camino(red_tenporal,nodo,feromona);
	    	
	    	System.out.println(nodo);
	    	
			//Se escoge un camino de los viables
			for (int i = 0; i < red_tenporal[nodo].length; i++) {  
				red_tenporal[i][nodo]=-1;
			}
			
			for (int j = 0; j < red_tenporal.length; j++) { // mostrar red tenporal
				for (int j2 = 0; j2 < red_tenporal.length; j2++) {
					System.out.print(red_tenporal[j][j2]+"\t");
				}
				System.out.println();
			}
		  
			System.out.println("nodo "+nodo + "\tnodo final " + nodoFinal);  
		} //End while
	}

public static int elegir_camino(int red_tenporal[][], int nodo,double feromona[][]){
	
	double evaluacion=0;
	double random;    
	Random r = new Random(); 
	ArrayList<Integer> NodosBuenos = new ArrayList<Integer>();
	double feromonas=0;
	
	//Obtener nodos viables
	for (int j = 0; j < red_tenporal.length; j++) {
		if (red_tenporal[nodo][j]>0) {
			NodosBuenos.add(j); // agrega la posición del nodo bueno  
		}	
	}
	
	for (int i = 0; i < NodosBuenos.size(); i++) {
		System.out.print(NodosBuenos.get(i)+"\t");
	}
	
	for (int i = 0; i < NodosBuenos.size(); i++) {
			evaluacion+=feromona[nodo][NodosBuenos.get(i)];
		}
		
		random=r.nextDouble();
		
		for (int i = 0; i < NodosBuenos.size(); i++) {
			if (feromona[nodo][NodosBuenos.get(i)]/evaluacion<random) {
			feromonas+=feromona[nodo][NodosBuenos.get(i)]/evaluacion;
			feromona[nodo][NodosBuenos.get(i)]=feromonas;
			}
			else {
				nodo=NodosBuenos.get(i);
				break;
			}	
		}
		NodosBuenos.clear();
	return nodo;
}


}