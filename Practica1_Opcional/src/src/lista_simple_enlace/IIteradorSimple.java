package src.lista_simple_enlace;

import java.util.NoSuchElementException;

/**
 * Iterador simple.
 * 
 * @param <E> tipo de los elementos sobre los que itera
 * 
 * @author Estructuras de Datos (UC)
 * @version nov-2016
 */
public interface IIteradorSimple<E> {
	
	/**
	 * Indica si hay más elementos(todavía no se ha
	 * llegado al final de la lista)
	 * @return verdadero si hay mas elementos que recorrer y falso en caso
	 * contrario
	 */
	public boolean haySiguiente();
	
	/**
	 * Retorna el siguiente elemento y avanza el iterador
	 * @return siguiente elemento
	 * @throws NoSuchElementException si se invoca este metodo cuando el
	 * iterador se encuentra al final de la secuencia
	 */
	public E siguiente() throws NoSuchElementException;
	
	/**
	 * Inserta un elemento en la posición del iterador
	 * @param e elemento a insertar
	 */
	public void anhade(E e);
	
	/**
	 * Reemplaza el último elemento retornado por el iterador
	 * @param e elemento utilizado para reemplazar
	 * @throws NoSuchElementException si el iterador no ha retornado ningún elemento previamente
	 */
	public void asigna (E e);
	
	/**
	 * Elimina el último elemento retornado por el iterador
	 * (o metido con anhade...)
	 * @throws NoSuchElementException si el iterador no ha retornado ningún elemento previamente
	 */
	public void elimina ();
	
	
}
