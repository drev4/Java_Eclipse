package pract09;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test de un mapa basdado en tabla de dispersion.
 * 
 * @author Estructuras de Datos (UC)
 * @version nov-2016
 */
public class MapaDispersionTest {
	final int longTabla = 10;
	
	private void chkTamYMuestra(IMapa<Integer, String> mapa,
			int tamEsperado) {
		System.out.println(mapa);
		assertTrue(mapa.tamanho()==tamEsperado);	
	}
	
	@Test
	public void testConstructor() {
		System.out.println("testConstructor");
		IMapa<Integer, String> mapa = new MapaDispersionAbierta<Integer, String>(8);
		chkTamYMuestra(mapa, 0);
		assertTrue(mapa.busca(0)==null);
		assertTrue(mapa.busca(1)==null);
	}

	@Test
	public void testTamanho1() {
		System.out.println("testTamanho1");
		IMapa<Integer, String> mapa = new MapaDispersionAbierta<Integer, String>(8);
		chkTamYMuestra(mapa, 0);
		
		mapa.anhade(1, "A01");
		chkTamYMuestra(mapa, 1);
	}

	@Test
	public void testTamanho2() {
		System.out.println("testTamanho2");
		IMapa<Integer, String> mapa = new MapaDispersionAbierta<Integer, String>(8);
		chkTamYMuestra(mapa, 0);
		
		mapa.anhade(1, "A01");
		chkTamYMuestra(mapa, 1);
		
		mapa.elimina(1);
		chkTamYMuestra(mapa, 0);
	}

	@Test
	public void testTamanho3() {
		System.out.println("testTamanho3");
		IMapa<Integer, String> mapa = new MapaDispersionAbierta<Integer, String>(8);
		chkTamYMuestra(mapa, 0);
		
		mapa.anhade(1, "A01");
		chkTamYMuestra(mapa, 1);
		
		mapa.anhade(2, "A02");	
		mapa.anhade(5, "A05");	
		mapa.anhade(4, "A04");
		chkTamYMuestra(mapa, 4);
		
		mapa.anhade(2, "dos");
		chkTamYMuestra(mapa, 4);
		
		mapa.elimina(5);
		chkTamYMuestra(mapa, 3);
		
		mapa.anhade(11, "A011");
		chkTamYMuestra(mapa, 4);
		
		mapa.elimina(7);
		chkTamYMuestra(mapa, 4);
		
		mapa.elimina(1);		
		mapa.elimina(2);		
		mapa.elimina(4);		
		mapa.elimina(11);
		chkTamYMuestra(mapa, 0);	
	}
	
	@Test
	public void testRetornoAnhade() {
		System.out.println("testRetornoAnhade");
		IMapa<Integer, String> mapa = new MapaDispersionAbierta<Integer, String>(8);
		String v;
		
		v = mapa.anhade(2, "A02");
		assertTrue (v == null);
		
		v = mapa.anhade(5, "A05");
		assertTrue (v == null);	
		
		v = mapa.anhade(2, "A22");
		assertTrue (v.equals("A02"));
		
		// Comprueba que el valor se ha actualizado
		assertTrue (mapa.busca(2).equals("A22"));
		
		chkTamYMuestra(mapa, 2);
	}

	@Test
	public void testAnhadeBusca1() {
		System.out.println("testAnhadeBusca1");
		IMapa<Integer, String> mapa = new MapaDispersionAbierta<Integer, String>(8);
		chkTamYMuestra(mapa, 0);
		
		mapa.anhade(3, "A03");
		chkTamYMuestra(mapa, 1);
		assertTrue(mapa.busca(new Integer(3)).equals("A03"));
	}

	@Test
	public void testAnhadeBusca2() {
		System.out.println("testAnhadeBusca2");
		IMapa<Integer, String> mapa = new MapaDispersionAbierta<Integer, String>(8);
		chkTamYMuestra(mapa, 0);
		
		mapa.anhade(1, "A01");
		chkTamYMuestra(mapa, 1);
		assertTrue(mapa.busca(1).equals("A01"));

		assertTrue(mapa.busca(2)==null);
				
		mapa.anhade(3, "A03");				
		mapa.anhade(5, "A05");
		chkTamYMuestra(mapa, 3);
		assertTrue(mapa.busca(5).equals("A05"));	
		
		mapa.anhade(15, "A015");
		chkTamYMuestra(mapa, 4);
		assertTrue(mapa.busca(5).equals("A05"));
		assertTrue(mapa.busca(15).equals("A015"));
		
		assertTrue(mapa.busca(45)==null);		
	}

	@Test
	public void testAnhadeEliminaBusca1() {
		System.out.println("testAnhadeEliminaBusca1");
		IMapa<Integer, String> mapa = new MapaDispersionAbierta<Integer, String>(8);
		chkTamYMuestra(mapa, 0);
		
		mapa.anhade(5, "A05");
		chkTamYMuestra(mapa, 1);
		assertTrue(mapa.busca(5).equals("A05"));
		
		// elimina primero
		assertTrue(mapa.elimina(5).equals("A05"));
		chkTamYMuestra(mapa, 0);
	}

	@Test
	public void testAnhadeEliminaBusca2() {
		System.out.println("testAnhadeEliminaBusca2");
		IMapa<Integer, String> mapa = new MapaDispersionAbierta<Integer, String>(8);
		chkTamYMuestra(mapa, 0);
		
		mapa.anhade(1, "A01");
		chkTamYMuestra(mapa, 1);
		assertTrue(mapa.busca(1).equals("A01"));
		
		// elimina primero
		assertTrue(mapa.elimina(1).equals("A01"));
		chkTamYMuestra(mapa, 0);
		
		// elimina en vacío
		assertTrue(mapa.elimina(1)==null);
		chkTamYMuestra(mapa, 0);
				
		mapa.anhade(2, "A02");		
		mapa.anhade(13, "A013");
		chkTamYMuestra(mapa, 2);
		
		// elimina con más de una entrada
		assertTrue(mapa.elimina(13).equals("A013"));
		chkTamYMuestra(mapa, 1);
		
		// busca eliminado
		assertTrue(mapa.busca(13)==null);
	
		mapa.anhade(14, "A014");	
		mapa.anhade(23, "A023");	
		mapa.anhade(44, "A044");	
		mapa.anhade(54, "A054");
		
		// busca en lista con más de una entrada
		assertTrue(mapa.busca(14).equals("A014"));
		assertTrue(mapa.busca(44).equals("A044"));
		assertTrue(mapa.busca(54).equals("A054"));
		chkTamYMuestra(mapa, 5);
		
		// elimina en lista con más de una entrada
		assertTrue(mapa.elimina(14).equals("A014"));
		assertTrue(mapa.elimina(54).equals("A054"));
		chkTamYMuestra(mapa, 3);
		
		// busca después de eliminar
		assertTrue(mapa.busca(14)==null);
		assertTrue(mapa.busca(44).equals("A044"));
		assertTrue(mapa.busca(54)==null);
		chkTamYMuestra(mapa, 3);
	}
	
	// prueba llaves negativas para simular funciones
	// hash que retornan un valor negativo que hay que
	// volver a poner en positivo
	@Test
	public void testLlavesNeg() {
		System.out.println("testLlavesNeg");
		IMapa<Integer, String> mapa = new MapaDispersionAbierta<Integer, String>(8);
		chkTamYMuestra(mapa, 0);
		
		mapa.anhade(-4, "A0-4");	
		mapa.anhade(0, "A00");	
		mapa.anhade(-2, "A0-2");	
		mapa.anhade(-14, "A0-14");	
		mapa.anhade(-longTabla, "A0"+longTabla);
		chkTamYMuestra(mapa, 5);
		assertTrue(mapa.busca(-4).equals("A0-4"));	
		assertTrue(mapa.busca(-longTabla).equals("A0"+longTabla));
	}
	
	@Test
	public void testHaceVacio() {
		System.out.println("testHaceVacio");
		IMapa<Integer, String> mapa = new MapaDispersionAbierta<Integer, String>(8);
		
		mapa.anhade(4, "cuatro");
		mapa.haceVacio();
		
		assertTrue(mapa.tamanho() == 0);
		
		// Comprueba que se ha vaciado correctamente
		assertTrue(mapa.busca(4)==null);
	}

}
