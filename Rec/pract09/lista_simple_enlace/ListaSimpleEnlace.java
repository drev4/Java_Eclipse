package pract09.lista_simple_enlace;

import java.util.NoSuchElementException;

import pract09.lista_simple_enlace.IIteradorSimple;
import pract09.lista_simple_enlace.ILista;

/**
 * Implementación de una lista generica utilizando celdas simplemente enlazadas
 * con celdas de cabecera y puntero a fin.
 * No implementa el iterador.
 * 
 * @param <E> tipo de los elementos almacenados en la lista
 * 
 * @author Estructuras de Datos (UC)
 * @version nov-2016
 */
public class ListaSimpleEnlace<E> implements ILista<E> {

	// clase privada que define la celda
	private static class Celda<E> {
		public E contenido;
		public Celda<E> siguiente = null;

		public Celda(E cont) {
			contenido=cont;    
		}
	}

	// referencia a la primera celda de la lista (celda de cabecera)
	private Celda<E> principio;
	
	// referencia a la ultima celda de la lista
	private Celda<E> fin;
	
	// numero de elementos en la lista
	private int numEle;

	/**
	 * Construye una lista vacia
	 */
	// Complejidad temporal: O(1) 
	public ListaSimpleEnlace() {
		principio = new Celda<E>(null); // crea la celda de cabecera
		
		// la lista esta vacia, luego la celda de cabecera es, a la vez, la ultima
		fin = principio;
		
		// la lista comienza con 0 elementos
		numEle = 0;
	}

	/**
	 * Inserta el elemento en la posición indicada.
	 * El elemento en la posición de inserción (si
	 * existe) y sucesivos se desplazan a la derecha
	 * (su posición se incrementa en 1).
	 * @param pos posicion en la que insertar el elemento
	 * @param e elemento a insertar
	 * @throws IndexOutOfBoundsException la posicion esta fuera del rango
	 * valido (pos < 0 o pos > tamanho)
	 */
	// Complejidad temporal: O(numEle) 
	// Caso especial: anhadir el ultimo es O(1)
	@Override
	public void anhade(int pos, E e) throws IndexOutOfBoundsException {
		if (pos < 0 || pos > numEle) {
			throw new IndexOutOfBoundsException(); // posicion incorrecta
		}

		Celda<E> nuevaCelda = new Celda<E>(e);
		
		if (pos == numEle) {
			// caso especial: anhadir en la ultima posicion de la lista
			fin.siguiente = nuevaCelda;
			fin = nuevaCelda;
			
		} else {
			// caso normal: anhadir una posicion de la lista que no es la ultima
			Celda<E> posAnt = buscaPos(pos-1);
			nuevaCelda.siguiente = posAnt.siguiente;
			posAnt.siguiente = nuevaCelda;
		}
		
		numEle++;
	}

	/**
	 * Retorna una referencia a la celda en la posicion pos
	 * {Pre: pos >= -1 y pos<numEle}
	 * @param pos posicion de la celda buscada
	 * @return referencia a la celda en la posicion pos
	 */
	// Complejidad temporal: O(numEle) 
	private Celda<E> buscaPos(int pos) {
		Celda<E> aux = principio;
		for(int i=0; i<=pos; i++) {
			aux = aux.siguiente;
		}
		return aux;
	}

	/**
	 * Elimina y retorna el elemento en la posición
	 * indicada. El elemento siguiente al eliminado
	 * (si existe) y sucesivos se desplazan a la
	 * izquierda (su posición se decrementa en 1).
	 * @param pos posicion del elemento a eliminar
	 * @return el elemento eliminado
	 * @throws IndexOutOfBoundsException la posicion esta fuera del rango
	 * valido (pos < 0 o pos >= tamanho)
	 */
	// Complejidad temporal: O(numEle)
	@Override
	public E elimina(int pos) throws IndexOutOfBoundsException {
		if (pos < 0 || pos >= numEle) {
			throw new IndexOutOfBoundsException(); // posicion incorrecta
		}

		Celda<E> posAnt = buscaPos(pos-1);
		E tmp = posAnt.siguiente.contenido;
		posAnt.siguiente = posAnt.siguiente.siguiente;
		
		if (pos == numEle-1) {
			// si se ha eliminado el ultimo hay que cambiar fin
			fin = posAnt;
		}
		
		numEle--;
		
		return tmp;
	}

	/**
	 * Retorna el elemento que ocupa la posicion
	 * indicada
	 * @param pos posicion del elemento a retornar
	 * @throws IndexOutOfBoundsException la posicion esta fuera del rango
	 * valido (pos < 0 o pos >= tamanho)
	 */
	// Complejidad temporal: O(numEle)
	@Override
	public E obtenElemento(int pos) throws IndexOutOfBoundsException {
		if (pos < 0 || pos >= tamanho()) {
			throw new IndexOutOfBoundsException(); // posicion incorrecta
		}

		return buscaPos(pos).contenido;
	}

	/**
	 * Retorna la posicion de la primera ocurrencia
	 * del elemento en la lista.
	 * Utiliza el método equals del elemento.
	 * @param e elemento buscado
	 * @return posicion de la primera ocurrencia
	 * del elemento en la lista
	 */
	// Complejidad temporal: O(numEle)
	@Override
	public int busca(E e) {
		Celda<E> aux = principio.siguiente;
		int pos = 0;
		while (aux != null) {
			if (aux.contenido.equals(e)) {
				return pos;  // encontrado
			}
			aux = aux.siguiente;
			pos++;
		}
		return -1; // no encontrado
	}

	/**
	 * Vacia la lista (pasa a tener tamanho 0)
	 */
	// Complejidad temporal: O(1)
	@Override
	public void haceVacia() {
		principio.siguiente = null;
		fin = principio;
		numEle = 0;
	}

	/**
	 * Retorna el tamanho de la lista (numero de elementos)
	 * @return numero de elementos de la lista
	 */
	// Complejidad temporal: O(1)
	@Override
	public int tamanho() {
		return numEle;
	}

	/**
	 * Clase iteradora 
	 */
	private static class IteradorLista<E> implements IIteradorSimple<E> {
		private ListaSimpleEnlace<E> lista; // lista a ser iterada
		private Celda<E> prepre;       // celda adicional anterior a la anterior a la que se retornar con siguiente
		boolean valorEnCursoYaEliminado=false;

		/**
		 * Constructor del iterador. El iterador comienza al principio
		 * de la lista (justo antes del primer elemento)
		 * @param lista lista a ser iterada
		 */
		// Complejidad temporal: O(1)
		public IteradorLista(ListaSimpleEnlace<E> lista) {
			this.lista=lista;
			prepre = new Celda<E>(null); 
			prepre.siguiente=lista.principio;
			valorEnCursoYaEliminado = false;
		}

		/**
		 * Indica si hay más elementos (no se ha llegado al final de la lista)
		 * @return true si todavia no se ha llegado al final de la lista
		 */
		// Complejidad temporal: O(1)
		@Override
		public boolean haySiguiente() {
			return (prepre != lista.fin && prepre.siguiente !=lista.fin);
		}

		/**
		 * Retorna el siguiente elemento en la iteracion y avanza el iterador
		 * @return el siguiente elemento
		 * @throws NoSuchElementException si se ha llegado al final de la lista
		 */
		// Complejidad temporal: O(1)
		@Override
		public E siguiente() {
			if (!haySiguiente()) {
				throw new NoSuchElementException("No hay siguiente elemento");
			}
			
			prepre = prepre.siguiente;
			
			valorEnCursoYaEliminado = false;
			
			return prepre.siguiente.contenido;
		}

		/**
		 * Inserta un elemento en la posición del iterador
		 * @param e elemento a insertar
		 */
		// Complejidad temporal: O(numEle)
		@Override
		public void anhade(E e) {
			
			Celda<E> nuevaCelda = new Celda<E>(e);
			
			if (!haySiguiente()) {
				// caso especial: anhadir en la ultima posicion de la lista
				lista.fin.siguiente = nuevaCelda;
				lista.fin = nuevaCelda;
				
			} else {
				// caso habitual
				nuevaCelda.siguiente = prepre.siguiente.siguiente;
				prepre.siguiente.siguiente = nuevaCelda;
			}
			
			// incrementa el contador de elementos
			lista.numEle++;
			
			// una llamada a siguiente despues de anhadir debe retornar
			// el mismo elemento que habria retornado si no se hubiera
			// llamado a anhade
			prepre = prepre.siguiente; 
			valorEnCursoYaEliminado = false;
		}
		
		/**
		 * Reemplaza el último elemento retornado por el iterador
		 * @param e elemento utilizado para reemplazar
		 */
		@Override
		public void asigna(E e) {
			if (prepre == lista.fin || prepre.siguiente==lista.principio) {
				throw new NoSuchElementException("Elemento inexistente");
			}
			prepre.siguiente.contenido = e;
		}

		/**
		 * Elimina el último elemento retornado por el iterador
		 * (o metido con anhade...)
		 * @throws NoSuchElementException si el iterador no ha retornado ningún elemento previamente o este ha sido ya eliminado
		 */

		@Override
		public void elimina() {
			
			Celda<E> aQuitar = prepre.siguiente;
			
			if (prepre == lista.fin || prepre.siguiente==lista.principio || valorEnCursoYaEliminado ) {
				throw new NoSuchElementException("Elemento a eliminar inexistente");
			}
			
			valorEnCursoYaEliminado = true;

			//para saltarse la que se quita
			prepre.siguiente = aQuitar.siguiente;
			
			//ahora se usa la que se quita como celda temporal para que prepre 
			//siga a la misma distancia de la siguiente a devolver
			aQuitar.siguiente = prepre; 
			
			if (aQuitar==lista.fin) {
				lista.fin = prepre;
			}
			else {
				//Si no ha llegado al fin de la lista, una vez eliminado el último que se retornó con siguiente() 
				//hay que hacer 'retroceder' a prepre para que el siguiente a retornar siga siendo el mismo
				//por ello se usa la celda quitada como celda temporal para avanzar posteriormente
				prepre = aQuitar;
			}
			lista.numEle--;
			
		}	
	}
	
	@Override
	public IIteradorSimple<E> iterador() {
		return new IteradorLista<E>(this);
	}

	
	@Override
	public String toString() {

		String str = "[";
		
		IIteradorSimple<E> iter = this.iterador();
		if (iter.haySiguiente()){
			str+= iter.siguiente().toString();
		}
		while (iter.haySiguiente()){
			str+= ", "+iter.siguiente().toString();
		}

		return str + "]";
	}


	
}
