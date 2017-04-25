package pract13.cuenta_repeticiones;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;



/**
 * Contabiliza las veces que se repite cada una de las palabras existentes
 * en un fichero de texto.
 * 
 * @author Estructuras de Datos (UC) y <Diego Revuelta Hoz>
 * @version dic-2016
 */
public class CuentaRepeticiones {

	// TODO: elegir un TAD que permita una implementacion mas eficiente
	private HashMap<String, RepeticionesPalabra> rep= new HashMap<String, RepeticionesPalabra>();
	private ArrayList<RepeticionesPalabra> pal= new ArrayList<RepeticionesPalabra>();
	private Object[] arr=null;
	
	@SuppressWarnings("serial")
	public static class PosicionIncorrecta extends RuntimeException { }

	/**
	 * Contabiliza las veces que se repite cada una de las palabras existentes
	 * en un fichero de texto y lo almacena en una estructura de datos interna para
	 * su posterior consulta con el metodo repeticiones.
	 * @param nomFich nombre del fichero en el que contabilizar las palabras.
	 * @throws FileNotFoundException si no existe el fichero.
	 */
	public CuentaRepeticiones(String nomFich) throws FileNotFoundException {
		String str = "";
		Scanner in = null;
		try {
			// abre el fichero
			in = new Scanner(new FileReader(nomFich));

			while (in.hasNext()) {
				str = in.next();

				// convierte a minusculas
				str = str.toLowerCase();

				// elimina los caracteres que no sean letras
				str = str.replaceAll("[^a-zñáéíóú]", "");

				// acumula ocurrencia
				if (!str.equals("")) {
					// TODO: implementar de forma mas eficiente
					if (rep.containsKey(str)){
						rep.get(str).sumaRepeticion();
					}else{
						RepeticionesPalabra r = new RepeticionesPalabra(str);
						rep.put(str, r);
						pal.add(r);
					}
					
					
				}
			}
		} finally {
			if (in != null){
				in.close(); // cierra el fichero
			}
		}
	}

	/**
	 * Retorna las veces que se repite la palabra en el fichero que fue
	 * pasada como parámetro al constructor.
	 * @param palabra palabra de la que se quiere saber cuantas veces se
	 * encuentra repetida en el fichero.
	 * @return numero de repeticiones de la palabra en el fichero.
	 */
	public int repeticiones(String palabra) {
		// TODO: implementar de forma mas eficiente
		if (rep.containsKey(palabra)){
			return rep.get(palabra).repeticiones();
		}else{
			return 0;
		}
	}
	
	/**
	 * Retorna la palabra que ocupa la posicion indicada en el ranking de palabras
	 * mas repetidas.
	 * La posicion 1 corresponde a la palabra mas repetida.
	 * @param pos posicion en el ranking.
	 * @return palabra que ocupa la posicion indicada en el ranking.
	 * @throws PosicionIncorrecta si la posicion es menor o igual que 0
	 * o mayor que el numero de palabras en el fichero.
	 */
	public RepeticionesPalabra palabraEnPos(int pos) throws PosicionIncorrecta {
		// TODO
		if (arr==null) {
			arr=pal.toArray();
		}
		Arrays.sort(arr);
		return (RepeticionesPalabra) arr[pos-1];
	}
}
