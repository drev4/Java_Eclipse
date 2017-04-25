package main;

import java.util.HashMap;

public class Tenista {
	
	private String nombre;
	private HashMap<String, Enfrentamiento> resultados = new HashMap<>();
	
	public Tenista (String nom){
		this.nombre=nom;
	}
	
	public void actualiza(int res, String cont){
		if (resultados.get(cont)==null){
			resultados.put(cont, new Enfrentamiento());
		}
		resultados.get(cont).actBalance(res);
	}
	public Enfrentamiento enf(String nom){
		if (resultados.get(nom)==null) {
			return null;
		}else{
			return resultados.get(nom);
		}
	}
}
