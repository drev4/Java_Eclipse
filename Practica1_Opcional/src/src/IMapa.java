package src;


/**
 * TDA mapa.
 *
 * @param <K> clase de elementos usados como llaves del mapa
 * @param <V> clase de elementos usados como valores del mapa
 * 
 * @author Estructuras de Datos (UC)
 * @version nov-2015
 */
public interface IMapa<K,V> {

	/**
	 * Si NO existe la entrada para la llave, anhade la entrada (llave,valor) al mapa.
	 * Si existe la entrada para la llave, pone valor como nuevo valor asociado con la llave
	 * 
	 * @param llave llave con la que asociar el valor
	 * @param valor valor asociado a la llave
	 * @return el valor previamente asociado con la llave o null en el caso
	 * de que no hubiera ninguna entrada para esa llave
	 */
	public V anhade(K llave, V valor);

	/**
	 * Si existe la entrada para la llave, la elimina
	 * 
	 * @param llave llave de la entrada a eliminar
	 * @return valor asociado con la llave o null si no hay ninguna
	 * entrada para la llave indicada
	 */
	public V elimina(K llave);
	
	
	/**
	 * Retorna el valor asociado con la llave
	 * 
	 * @param llave llave de la entrada buscada
	 * @return valor asociado con la llave o null si no hay ninguna
	 * entrada para la llave indicada
	 */
	public V busca(K llave);
	
    /**
     * Vacia el mapa (pasa a tener 0 entradas)
     */
    public void haceVacio();
	
	/**
	 * Retorna el numero de entradas en el mapa
	 *	
	 * @return numero de entradas en el mapa
	 */
	public int tamanho(); 
    
    // XXX
    // Otras operaciones de los mapas que no se incluyen
    // para limitar la complejidad de la practica
    //
    //public List<K> llaves();
    //public List<V> valores();
    //public List<Entrada> entradas();
}
