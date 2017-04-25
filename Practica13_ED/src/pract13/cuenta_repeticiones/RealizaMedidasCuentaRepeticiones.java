package pract13.cuenta_repeticiones;

import java.io.FileNotFoundException;

import fundamentos.*;

/**
 * Programa para medir tiempos de ejecucion de las dos versiones del
 * algoritmo para contar las repeticiones de palabras en un texto.
 * 
 * @author Estructuras de Datos (UC)
 * @version dic-16
 */
public class RealizaMedidasCuentaRepeticiones {

	// Nombre del fichero (comenta uno de los dos)
	private static String NOM_FICH = 
			"src/pract13/cuenta_repeticiones/ley_de_zipf.txt";
	        // "src/pract13/cuenta_repeticiones/miguel-de-cervantes-saavedra-don-quijote-de-la-mancha.txt";	

	// palabras con las que se comprueba que los dos algoritmos obtienen la misma solucion
	private static String[] STRINGS_COMPROBACION = {"que", "de", "el", "la", "del"};

	// Numero de palabras mas repetidas que se muestran
	private static int NUM_PRIMERAS_PALABRAS = 10;

	/**
	 * Programa para medir tiempos de ejecucion
	 */
	public static void main(String[] args) {
		CuentaRepeticiones repEficiente = null;
		CuentaRepeticionesIneficiente repIneficiente = null;

		try {
			long t0, t1, t2; // tiempos (en ms)

			// descarta primera ejecucion
			repEficiente = new CuentaRepeticiones(NOM_FICH);

			// Mide el tiempo de ejecucion de las dos implementaciones

			t0 = System.currentTimeMillis(); // instante inicial

			// Mide tiempo para la implementacion eficiente 
			repEficiente = new CuentaRepeticiones(NOM_FICH);

			t1 = System.currentTimeMillis(); // instante intermedio

			// Mide tiempo para la implementacion ineficiente
			repIneficiente = new CuentaRepeticionesIneficiente(NOM_FICH);

			t2 = System.currentTimeMillis(); // instante final

			// Comprueba que los resultados coinciden
			String strMsj = "";
			for(String str: STRINGS_COMPROBACION) {
				int repEfi = repEficiente.repeticiones(str);
				int repInefi = repIneficiente.repeticiones(str);
				strMsj = strMsj + "Repeticiones de \"" + str + "\":" + repInefi + "\n";
				if (repEfi != repInefi) {
					strMsj += "ERROR! Algoritmo eficiente: " + repEfi + "\n";
				}
			}
			mensaje("Repeticiones", strMsj);

			// muestra tiempos
			strMsj = "Tiempo algoritmo eficiente:" + (t1-t0) + "ms\n";
			strMsj = strMsj + "Tiempo algoritmo ineficiente:  " + (t2-t1) + "ms\n";			
			mensaje("Tiempos", strMsj);

		} catch (FileNotFoundException e) {
			mensaje("Error!", "No encontrado fichero\n" + NOM_FICH);
		}

		// Muestra las palabras mas repetidas
		System.out.println(NUM_PRIMERAS_PALABRAS + " palabras mas repetidas en el texto:");
		for(int pos=1; pos<=NUM_PRIMERAS_PALABRAS; pos++) {
			RepeticionesPalabra rep = repEficiente.palabraEnPos(pos);
			System.out.println(pos + ". " + rep.palabra() + " -> " +
					rep.repeticiones());
		}
	}

	/**
	 * Metodo auxiliar que muestra un ventana de mensaje
	 * @param titulo titulo de la ventana
	 * @param txt texto contenido en la ventana
	 */
	private static void mensaje(String titulo, String txt) {
		Mensaje msj = new Mensaje(titulo);
		msj.escribe(txt);

	}
}
