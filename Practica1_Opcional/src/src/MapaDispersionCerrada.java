package src;



public class MapaDispersionCerrada<K, V> implements IMapa<K, V>{
	// tabla con las listas de entradas
	private Entrada<K, V>[] tabla;

	// numero de entradas en el mapa
	private int numEntradas = 0;

	// entrada en el mapa
	private static class Entrada<K, V> {
		private K llave;
		private V valor;

		public Entrada(K llave, V valor) {
			this.llave = llave;
			this.valor = valor;
		}
		
		@Override
		public String toString() {
			return "(" + llave + "," + valor + ")";
		}
	}
	/**
	 * Constructor del mapa de dispersion.
	 * @param longTabla capacidad inicial de la tabla.
	 */
	@SuppressWarnings("unchecked")
	public MapaDispersionCerrada(int longTabla) {
		
		tabla = new Entrada[longTabla];
		
		for (int i = 0; i < longTabla; i++) {
			tabla[i] = null;
		}
	}
	/**
	 * Anhade una nueva entrada, en el caso de que se suepere la
	 * mitad de la capacidad se crea una nueva tabla del doble del tamaño-
	 * @param llave llave de la entrada a anhadir
	 * @param valor valor de la entrada a anhadir
	 * @return valor asociado a la llave o null si no existia.
	 */
	@Override
	public V anhade(K llave, V valor) {
		
		numEntradas++;
		
		if ((tabla.length/2)<=numEntradas) {
			duplicar();
		}
		
		int cod = hash(llave);
		int salto= salto(llave);
		
		while(tabla[cod] != null && tabla[cod].llave != null){
			cod = (cod+salto) % tabla.length;
		}
		
		V viejo=null;
		
		if (tabla[cod] == null) {
			tabla[cod] = new Entrada<K, V>(llave, valor);
		}else{
			tabla[cod].llave=llave;
			viejo = tabla[cod].valor;
			tabla[cod].valor=valor;
		}
		
		return viejo;
	}
	/**
	 * Si existe la entrada para la llave, la elimina
	 * 
	 * @param llave llave de la entrada a eliminar
	 * @return valor asociado con la llave o null si no hay ninguna
	 * entrada para la llave indicada
	 */
	@Override
	public V elimina(K llave) {
		int cod = hash(llave);
		int salto = salto(llave);
		V borrado=null;
		
		while(tabla[cod] != null && (tabla[cod].llave == null || !tabla[cod].llave.equals(llave))){
			cod = (cod + salto) % tabla.length;
		}
		
		if (tabla[cod] == null) {
			return null;
		}else{
			borrado = tabla[cod].valor;
			tabla[cod].llave = null;
			numEntradas--;
		}
		return borrado;
	}
	/**
	 * Retorna el valor asociado con la llave
	 * 
	 * @param llave llave de la entrada buscada
	 * @return valor asociado con la llave o null si no hay ninguna
	 * entrada para la llave indicada
	 */
	@Override
	public V busca(K llave) {
		
		int cod = hash(llave);
		int salto = salto(llave);
		
		while(tabla[cod] != null && (tabla[cod].llave == null || !tabla[cod].llave.equals(llave))){
			cod = (cod+salto) % tabla.length;
		}
		
		if (tabla[cod] == null) {
			return null;
		}
		return tabla[cod].valor;
	}
	/**
	 * Vacia el mapa (pasa a tener 0 entradas)
	 */
	// Complejidad temporal: O(tabla.length)
	@Override
	public void haceVacio() {
		for (int i = 0; i<tabla.length; i++){
			tabla[i] = null;
		}
		numEntradas = 0;
	}
	/**
	 * Retorna el numero de entradas en el mapa
	 *	
	 * @return numero de entradas en el mapa
	 */
	// Complejidad temporal: O(1)
	@Override
	public int tamanho() {
		
		return numEntradas;
	}
	/**
	 * Retorna el codigo de dispersion correspondiente a la llave.
	 * 
	 * @param llave llave de la que calcular el codigo de dispersion
	 * @return codigo de dispersion normalizado
	 */
	// Complejidad temporal: O(1)
	private int hash(K llave) {
		int cod = llave.hashCode() % tabla.length;
		if (cod < 0)
			cod += tabla.length;
		return cod;
	}
	/**
	 * Calcula el salto de la exploracion
	 * @param llave llave de la que calcular el salto.
	 * @return salto de la exploracion
	 */
	//Complejidad temporal: O(1)
	private int salto(K llave) {
		int s= llave.hashCode()/ tabla.length;
		if (s % 2 == 0) {
			return s+1;
		}
		return s;
	}
	/**
	 * Duplica el tamaño de la tabla.
	 */
	@SuppressWarnings("unchecked")
	private void duplicar(){
		Entrada<K, V>[] ant = tabla;
		numEntradas=0;
		int tam= 2*tabla.length;
		tabla=new Entrada[tam];
		for (int i = 0; i < tam; i++) {
			tabla[i]=null;
		}
		for (int i = 0; i < ant.length; i++) {
			Entrada<K, V> ent = ant[i];
			if (ent != null && ent.llave !=null) {
				anhade(ent.llave, ent.valor);
			}
		}
		
	}
}
