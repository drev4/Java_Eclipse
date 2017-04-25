package material;
/*
 * Diego Revuelta Hoz
 */
import fundamentos.Grafica;

public class GraficaTiempos {
	public static void main(String[] args) {
		Sort<Integer> g1 = new InsertionSort<>();
		Sort<Integer> g2 = new QuickSort<>();
		int x=g1.tMedSort(1, 25);
		int y=g2.tMedSort(1, 25);
		int x1=g1.tMedSort(1, 1000);
		int y1=g2.tMedSort(1, 1000);
		Grafica g = new Grafica();
		g.inserta(1, x);
		g.inserta(2, y);
		g.inserta(3, x1);
		g.inserta(4, y1);
		g.pinta();
		
		
	}
}
