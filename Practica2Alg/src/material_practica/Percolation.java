package material_practica;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Percolation {

	private  int panel[][];
	private WeightedQuickUnionUF pa;
	private int tam;
	private int inicio, fin;
	/*
	 * Constructor de la clase Percolation
	 * Complejidad O(1)
	 */
	public Percolation(int n) {
		
		if (n<=0) {
			throw new IllegalArgumentException();
		}
		this.tam=n;
		this.inicio=0;
		this.fin=(n*n)-1;
		panel = new int [n][n];
		pa = new WeightedQuickUnionUF(n*n);
	}
	/*
	 * Asigna 1 a la casilla seleccionada y se une con las casillas que se pueda.
	 * Complejidad O(1)
	 */
	public void abrir(int i, int j) {
		
		if (i==0){
			pa.union((j+tam*i), inicio);
		}
		if (i==(tam-1)) {
			pa.union((j+tam*i), fin);
		}
		
		panel[i][j] = 1;
		
		if (i<(tam-1)) {
			if (panel[i+1][j] == 1){
				pa.union((j+tam*i), (j+tam*(i+1)));
			}
		}
		
		if (j<(tam-1)) {
			if (panel[i][j+1] == 1){
				pa.union((j+tam*i), ((j+1)+tam*i));
			}
		}
		
		if (i>0) {
			if (panel[i-1][j] == 1){
				pa.union((j+tam*i), (j+tam*(i-1)));
			}
		}
		if (j>0) {
			if (panel[i][j-1] == 1){
				pa.union((j+tam*i), ((j-1)+tam*i));
			}
		}
		
		
	}
	/*
	 * Comprueba si nuestro cuadro percola.
	 * O(1)
	 */
	public boolean percolates() {
		return pa.connected(inicio, fin);
	}
	
	/*
	 * Compruba si la casilla esta abierta.
	 * O(1)
	 */
	public boolean estaAbierta(int row, int col) {
		 
		return panel[row][col] == 1;
	}
	/*
	 * Compruba si la casilla esta llena.
	 * O(1)
	 */
	public boolean llena(int row, int col) {
		
		return pa.connected((col+tam*row), inicio);
	}
	/*
	 * Devuelve el numero de casillas abiertas. 
	 *O(1)
	 */
	public int numAbiertas(){
		return pa.count();
	}
	/*
	 * Cacula el umbral del cuadro.
	 * O(1)
	 */
	public double umbral(){
		return numAbiertas()/(tam*tam);
	}
	
/*	public static void main(String[] args) {
		int n = StdIn.readInt();
       Percolation per = new Percolation(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (per.percolates()) break;
            per.abrir(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(per.numAbiertas() + " components");
   

	}
	*/
}
