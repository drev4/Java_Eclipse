package material;
/**
 * Clase para ordenar una secuencia de objetos de tipo T usando ordenacion por insercion
 * @author Jose Luis Montaña, Cruz E. Borges, Ines Gonzalez
 * @version curso 2009-2010
 * @param <T extends Comparable<T>>
 */
import java.util.Comparator;
import java.util.Random;

public class InsertionSort<T> implements Sort<T> {
    
	private Comparator<T> comp = null;
    
    /**
     * Constructor por defecto
     */
    public InsertionSort(){
    	comp=null;
    }
    
    /**
     * Constructor con un comparador distinto al predefinido para el tipo T
     * (para ordenar según distintos criterios)
     * @param c el comparador de objetos de tipo T
     */
    public InsertionSort( Comparator<T> c ){
    	comp = c; 
    }
    
    /**
     * método para ordenar una secuencia de principio a fin por inserción
     * @param d la secuencia de objetos de tipo T
     */
    public int sort( T[] d ){
    	return sort( d, 0, d.length - 1, 0);
    	}
    
    /**
     * método para ordenar una secuencia entre dos posiciones dadas usando inserción
     * @param d la secuencia de objetos de tipo T
     * @param start la posición inicial
     * @param end la posición final
     */
    public int sort( T[] d, int start, int end, int cont) {
        T key;
        int i, j;
        cont+=3;//3 asignaciones.
        
        // j indexa el elemento que se va a insertar
        // i indexa las posibles posiciones en las que se podría insertar el elemento j
        for ( j = start + 1; j <= end; j++ ){
            key = d[ j ];
            cont+=2;//1 instruccion for y 1 instruccion de asignacion
            for ( i = j - 1; i >= 0 && compare( key, d[ i ] ) < 0; i-- ){
                d[ i + 1 ] = d[ i ];
                cont+=2;//1 instruccion for y 1 instruccion de asignacion
            }
            d[ i + 1] = key;
            cont+=1;
        }
        return cont;
    }
    
    /**
     * método para comparar objetos de tipo T usando el comparador adecuado
     * @param a un objeto
     * @param b otro objeto
     */
    private int compare( T a, T b ) {
        if ( comp == null ){
            return ((Comparable<T>)a).compareTo( b );
        }
        else {
            return comp.compare( a, b );
        }
    }
    public int tMedSort( int n, int num ){
    	int estimacion = 0;
    	Integer vector[] = new Integer[n];
    	for ( int j=1; j<=num; j++ ){
    		int a[] = new int[n];
    		Random rnd = new Random();
    	// genera aleatoriamente vector de tamanio n
    		for( int i=0; i<a.length; i++ ){
    				a[i] = rnd.nextInt();
    				vector[i] = new Integer(a[i]);
    		}
    	// ordena el vector generado
    		Comparator<Integer> c = new EnterosComparador();
	    	Sort<Integer> in = new InsertionSort<Integer>(c);
		   	int estimacionParcial = in.sort(vector);
		   	estimacion += estimacionParcial;
		   	}
    	return (estimacion/num);
    }
}