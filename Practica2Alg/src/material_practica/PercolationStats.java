package material_practica;
import edu.princeton.cs.algs4.*;

public class PercolationStats {
	private double stats [];
	private int dimension;
	/*
	 * Construye la clase PercolationStats, crea un array con los umbrales de todos los trials. Crea un Objeto de la clase percolation 
	 * por cada trial y hace que percole.
	 * O(n^2)
	 */
	public PercolationStats(int n, int trials){
		this.dimension=n;
		for (int i = 0; i < trials; i++) {
			stats =new double[trials];
				Percolation a= new Percolation(dimension);
				boolean percola = false;
				while (!percola) {
					if (a.percolates()){
						percola=true;
					}else{
						a.abrir(StdRandom.uniform(dimension-1), StdRandom.uniform(dimension-1));
					}
				stats[i]=a.umbral();
			}
		}
	}
	/*
	 * Devuelve la media de los umbrales.
	 * O(n)
	 */
	public double media(){
		double aux = 0;
		for (int i = 0; i < stats.length; i++) {
			aux+=stats[i];
		}
		//double otraForma = StdStats.mean(stats);
		return aux/stats.length;
		
	}
	/*
	 * Devuelve el valor minimo del intervalo de confianza
	 * O(n)
	 */
	public double confianzaBa(){
		return media()-Math.sqrt((3.84*desviacion())/stats.length);
		
	}
	/*
	 * Devuelve el valor maximo del intervalo de confianza
	 * O(n)
	 */
	public double confianzaAl(){
		return media()+Math.sqrt((3.84*desviacion())/stats.length);
		
	}
	/*
	 * Devuelve la desviacion tipica de la muestra.
	 * O(n)
	 */
	public double desviacion(){
		//double otraForma = StdStats.stddev(stats);
		return Math.sqrt(varianza());
		
	}
	/*
	 * Devuelve la varianza muestral.
	 * O(n)
	 */
	public double varianza(){
		double mean = media();
		double aux=0;
		for (int i = 0; i < stats.length; i++) {
			aux+=(Math.pow((stats[i]-mean), 2));
		}
		//double otraForma = StdStats.var(stats);
		return aux/stats.length;
	}
	public static void main(String[] args) {
		int n = StdIn.readInt();
		int m = StdIn.readInt();
        PercolationStats st = new PercolationStats(n, m);
        StdOut.println("Media = " + st.media());
        StdOut.println("Desviacion = " + st.desviacion());
        StdOut.println("Intervalo de confianza = " + st.confianzaBa() + " , " + st.confianzaAl());

	}
	
}
