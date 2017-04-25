import java.util.PriorityQueue;
import java.util.Stack;

public class Tablero{
	private final int dim;
	private final int[][] tablero;

	public Tablero(int[][] data){
		dim = data.length;
		tablero = new int[dim][dim];
		for (int i = 0; i < dim ; i++ ) {
			for (int j = 0; j < dim ; j++ ) {
				tablero[i][j] = data[i][j];
			}
		}
	}

	private int[][] tableroFinal(){
		int [][] fin = new int[dim][dim];
		for (int i = 0; i < dim ; i++ ) {
			for (int j = 0; j < dim ; j++ ) {
				fin[i][j] = valorFinal(i,j);
			}
		}
		return fin;
	}

	public int dimension(){
		return dim;
	}

	public int hamming(){
		int sum = 0;
		for (int i = 0; i < dim ; i++ ) {
			for (int j = 0; j < dim ; j++ ) {
				if (tablero[i][j] != valorFinal(i,j) && !casillaFin(i,j) ) {
					sum++;
				}
			}
		}
		return sum;
	}

	private int valorFinal(int i, int j) {
        if (casillaFin(i, j)) {
            return 0;
        }
        return 1 + i * dim + j;
    }

    public boolean casillaFin(int i, int j){
    	if (i==dim-1 && j==dim-1) {
    		return true;
    	}else{
    		return false;
    	}
    }

    public int manhattan() {
        int sum = 0;
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                int valor = tablero[i][j];
                if (valor != 0 && valor != valorFinal(i, j)) {
                    int iX = (valor - 1) / dim;
                    int iY = valor - 1 - iX * dim;
                    int distancia = Math.abs(i - iX)
                            + Math.abs(j - iY);
                    sum += distancia;
                }
            }
        }
        return sum;
    }

    public boolean meta(){
    	int auxFin[][] = tableroFinal();
    	for (int i = 0; i < dim ; i++ ) {
			for (int j = 0; j < dim ; j++ ) {
				if (tablero[i][j] != auxFin[i][j]) {
					return false;
				}
			}
		}
		return true;
    }

    public Tablero espejo(){
    	Tablero tab = new Tablero(tablero);
    	for (int i = 0; i < dim ; i++ ) {
			for (int j = 0; j < dim ; j++ ) {
				if (tablero[i][j] !=0 && tablero[i][j + 1] != 0) {
					tab.intercambiar(i, j, i, j+1);
					return tab;
				}
			}
		}
		return tab;
    }

    private boolean intercambiar(int i, int j, int iseg, int jseg) {
        if (iseg < 0 || iseg >= dim || jseg < 0 || jseg >= dim) {
            return false;
        }
        int temp = tablero[i][j];
        tablero[i][j] = tablero[iseg][jseg];
        tablero[iseg][jseg] = temp;
        return true;
    } 

     public Iterable<Tablero> vecinos() {
        int ii = 0, jj = 0;
        boolean encontrado = false;
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (tablero[i][j] == 0) {
                    ii = i;
                    jj = j;
                    encontrado = true;
                    break;
                }
            }
            if (encontrado) {
                break;
            }
        }

        Stack<Tablero> tableros = new Stack<Tablero>();
        Tablero tab = new Tablero(tablero);
        boolean esVecino = tab.intercambiar(ii, jj, ii - 1, jj);
        if (esVecino) {
            tableros.push(tab);
        }
        tab = new Tablero(tablero);
        esVecino = tab.intercambiar(ii, jj, ii, jj - 1);
        if (esVecino) {
            tableros.push(tab);
        }
        tab = new Tablero(tablero);
        esVecino = tab.intercambiar(ii, jj, ii + 1, jj);
        if (esVecino) {
            tableros.push(tab);
        }
        tab = new Tablero(tablero);
        esVecino = tab.intercambiar(ii, jj, ii, jj + 1);
        if (esVecino) {
            tableros.push(tab);
        }

        return tableros;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(dim + "\n");
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                s.append(String.format("%2d ", tablero[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

}