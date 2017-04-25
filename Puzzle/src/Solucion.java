import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;

public class Solucion{
	
	private final Stack<Tablero> puz;
	private int movimientos;
	private boolean esResoluble;
	
	private class Estado implements Comparable<Estado>{
		
		private Tablero tab;
		private int movimientos;
		private Estado previo;
		private int prio = -1;
		
		public Estado(Tablero t, Estado previo){
			this.tab=t;
			
			this.previo=previo;
		}
		
		private int prioridad(){
			if (prio == -1) {
				prio= movimientos() + tab.manhattan();
			}
			return prio;
		}
		
		private int movimientos(){
			return movimientos;
		}
		private Estado previo(){
			return previo;
		}
		public Tablero tablero(){
			return tab;
		}
		
		@Override
		public int compareTo(Estado o) {
			if (this.prioridad() < o.prioridad()) {
				return -1;
			}
			if (this.prioridad() > o.prioridad()) {
				return 1;
			}
			return 0;
		}
		
	}

	public Solucion (Tablero inicial){
		puz= new Stack<Tablero>();
		if (inicial.meta()) {
			esResoluble=true;
			puz.push(inicial);
			return;
		}
		if (inicial.espejo().meta()) {
			esResoluble=false;
			return;
		}
		PriorityQueue<Estado> min = new PriorityQueue<Estado>();
		PriorityQueue<Estado> minEspejo = new PriorityQueue<Estado>();
		movimientos=0;
		Tablero t =inicial;
		Tablero tEspejo = inicial.espejo();
		Estado e = new Estado(t, null);
		Estado eEspejo = new Estado(tEspejo, null);
		min.add(e);
		minEspejo.add(eEspejo);
		while(movimientos<100){
			e = min.poll();
			eEspejo = minEspejo.poll();
			t = e.tablero();
			tEspejo = eEspejo.tablero();
			if (tEspejo.meta()) {
				esResoluble=false;
				return;
			}
			if (t.meta()) {
				esResoluble=true;
				puz.push(t);
				while(e.previo() != null){
					e = e.previo();
					puz.push(e.tablero());
				}
				return;
			}
			e.movimientos++;
			eEspejo.movimientos++;
			Iterable<Tablero> vecinos = t.vecinos();
			for(Tablero v : vecinos){
				if (e.previo()!=null && v.equals(e.previo().tablero())){
					continue;
				}
				Estado nuevo= new Estado(v, e);
				nuevo.movimientos = e.movimientos;
				min.add(nuevo);
			}
			Iterable<Tablero> vecinosE = t.vecinos();
			for(Tablero v : vecinosE){
				if (eEspejo.previo()!=null && v.equals(eEspejo.previo().tablero())){
					continue;
				}
				Estado nuevo= new Estado(v, eEspejo);
				nuevo.movimientos = eEspejo.movimientos;
				minEspejo.add(nuevo);
			}
		}		
	}
	
	public boolean esResoluble(){
		return esResoluble;
	}
	
	public int movimientos(){
		if(esResoluble){
			return puz.size() -1 ;
		}else{
			return -1;
		}
	}
	
	public Iterable<Tablero> solucion(){
		if(esResoluble){
			return puz;
		}else{
			return null;
		}
	}
	
	public static void main(String[] args){
		// create initial board from file
		String in = new String(args[0]);
		try{
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(new File(in));
			int c,f;
			int dimension = scanner.nextInt();
			int [][] bloque = new int[dimension][dimension];
			c = 0;
			f = 0;
			while (scanner.hasNextInt()){
				bloque[c][f] = scanner.nextInt();
				c++;
				if (c>= dimension){
					c = 0;
					f++;
				}
			}
			Solucion s = new Solucion(new Tablero(bloque));
			// Comprobamos si el problema se puede resolver
			if (!s.esResoluble())
			System.out.println("No es posible resolverlo");
			else{
			// en caso de que lo resolvamos, imprimimos la solución.
				System.out.println("Minimo numero de movimientos = " +
				s.movimientos() );
				for (Tablero t: s.solucion()){
					System.out.println(t);
				}
			}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	
}