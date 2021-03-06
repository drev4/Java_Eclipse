import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class ColaAleatoria<Item> implements Iterable<Item>{

	private int tam;
	public Item[] elementos;
	
	/*
	 * Constructor de una cola vacia
	 */
	public ColaAleatoria() {
		tam=0;
		elementos = (Item[]) new Object[1];
		//ArrayList<Item> a = new ArrayList<Item>();
	}
	
	public boolean estaVacia(){
		return tam==0;
	}
	
	public int tamanho(){
		return tam;
	}
	
	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new IteradorAleatorio();
	}
	
	private void redimensionar(int c){
		Item[] copia = (Item[]) new Object[c];
		for(int i = 0; i< tam; i++){
			copia[i] = elementos[i];
		}
		
		elementos = copia;
	}
	
	public void encolar(Item i){
		if(i == null){
			throw new NullPointerException();
		}
		
		elementos[tam++]  = i;
		if (tam == elementos.length){
			redimensionar(2*elementos.length);
		}
	}
	
	public Item desncolar(){
		int i = StdRandom.uniform(tam);
		Item sal = elementos[i];
		
		tam--;
		if(i < tam){
			elementos[i] = elementos[tam];
		}
		elementos[tam] = null;
		
		if(tam > 0 && tam < elementos.length / 2){ //< o <=
			redimensionar(elementos.length/2);
		}
		
		return sal;
	}
	
	public Item muestrear(){
		int i = StdRandom.uniform(tam);
		return elementos[i];
	}
	

	private class IteradorAleatorio implements Iterator<Item>{
		private Item[] salida;
		private int con = 0;
		
		private IteradorAleatorio(){
			salida = (Item[]) new Object[tam];
			for (int i = 0; i< tam; i++){
				salida[i] = elementos[i];
			}
			StdRandom.shuffle(salida);
		}
		@Override
		public boolean hasNext() {
			
			return con<tam;
		}

		@Override
		public Item next() {
			if(!hasNext()) throw new NoSuchElementException();
			return salida[con++];
		}
		
	}
	
}
