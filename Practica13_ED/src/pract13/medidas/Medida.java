package pract13.medidas;

/**
 * Clase sencilla que permite almacenar una medida de tiempo: tamanho del
 * ejemplar y tiempo que ha tardado en realizarse el algoritmo medido para
 * ese tamanho.
 * 
 * @author Estructuras de Datos (UC)
 * @version sep-16
 */
public class Medida {
	public final int n;   // tamanho del ejemplar
	public final double ms; // milisegundos de la medida
	
	public Medida(int n, double ms) {
		this.n = n;
		this.ms = ms;
	}
}
