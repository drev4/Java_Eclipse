package pract13.lista_con_elementos_comunes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Implementa el algoritmo que permite dejar en una lista los
 * productos que tambien se encuentran en la otra.
 *  
 * @author Estructuras de Datos (UC) y <TODO: nombre alumno>
 * @version dic-2016
 */
public class ProductosComunes {
	private static Set<Producto> l2=new HashSet<Producto>();
	/**
	 * Deja en la lista1 unicamente aquellos productos que tambien estan
	 * en la lista2.
	 * @param lista1 lista en la que se dejan unicamente aquellos productos
	 * que tambien estan en la lista2.
	 * @param lista2 lista en la que esta los productos a conservar.
	 */
	public static void dejaElementosComunes(ArrayList<Producto> lista1,
			ArrayList<Producto> lista2) {
		l2.addAll(lista2);
		int i=0;
		while (i<lista1.size()) {
			if (!estaEnLista(l2, lista1.get(i))) {
				lista1.remove(i);
			} else {
				i++;
			}
		}
	}
	
	/**
	 * Indica si un producto esta en la lista.
	 * @param l22 lista en la que buscar el producto.
	 * @param producto producto buscado
	 * @return verdadero si el producto esta en la lista.
	 */
	private static boolean estaEnLista(Collection<Producto> l22, Producto producto) {
		if (l22.contains(producto)){
			return true;
		}
		return false;
	}
}
