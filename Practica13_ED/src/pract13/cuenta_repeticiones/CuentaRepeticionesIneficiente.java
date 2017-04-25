package pract13.cuenta_repeticiones;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Contabiliza las veces que se repite cada una de las palabras existentes
 * en un fichero de texto.
 * Utiliza una implementacion ineficiente basada en una lista.
 * 
 * @author Estructuras de Datos (UC)
 * @version dic-2016
 */
public class CuentaRepeticionesIneficiente {

	private ArrayList<RepeticionesPalabra> cuentaRepeticiones = new ArrayList<>();

	@SuppressWarnings("serial")
	public static class PosicionIncorrecta extends RuntimeException { }

	/**
	 * Contabiliza las veces que se repite cada una de las palabras existentes
	 * en un fichero de texto y lo almacena en una estructura de datos interna para
	 * su posterior consulta con el metodo repeticiones.
	 * @param nomFich nombre del fichero en el que contabilizar las palabras.
	 * @throws FileNotFoundException si no existe el fichero.
	 */
	public CuentaRepeticionesIneficiente(String nomFich) throws FileNotFoundException {
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
					int pos = cuentaRepeticiones.indexOf(new RepeticionesPalabra(str)); 
					if (pos == -1) {
						cuentaRepeticiones.add(new RepeticionesPalabra(str));
					} else {
						cuentaRepeticiones.get(pos).sumaRepeticion();
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
		int pos = cuentaRepeticiones.indexOf(new RepeticionesPalabra(palabra)); 
		if (pos == -1) {
			return 0;
		} else {
			return cuentaRepeticiones.get(pos).repeticiones();
		}
	}
}
