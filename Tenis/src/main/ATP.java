package main;

import java.util.HashMap;
import java.util.Queue;

public class ATP {
	private HashMap<String, Tenista> tenistas;
	public ATP (){
		tenistas  = new HashMap<String, Tenista>();
		
	}
	
	public void anhade(String nom){
		if (tenistas.get(nom)!=null){
			System.out.println("Ya existe un tenista con ese nombre.");
			return;
		}
		tenistas.put(nom, new Tenista(nom));
	}
	
	public void anhadeResul (String ganador, String perdedor){
		if(tenistas.get(ganador)==null || tenistas.get(perdedor)==null){
			System.out.println("El nombre de los tenistas no es correcto.");
		}
		tenistas.get(ganador).actualiza(1, perdedor);
		tenistas.get(perdedor).actualiza(-1, ganador);
	}
	public int obtenerRes(String ten1, String ten2){
		if(tenistas.get(ten1)==null || tenistas.get(ten2)==null){
			System.out.println("El nombre de los tenistas no es correcto.");
		}
	
		return tenistas.get(ten1).enf(ten2).getBalance();
	}
}
