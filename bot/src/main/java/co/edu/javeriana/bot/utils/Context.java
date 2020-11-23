package co.edu.javeriana.bot.utils;

import java.util.HashMap;

public class Context {
	private HashMap<String, Object> tabla;
	protected Context anterior = null;
	
	public Context(Context ant) {
		tabla = new HashMap<String, Object>();
		anterior  = ant;
	}
	
	public Context() {
		tabla = new HashMap<String, Object>();
	}
	
	public void put(String simbolo, Object value) {
		tabla.put(simbolo, value);
	}
	
	public Object get(String simbolo) {
		for(Context e = this; e!= null; e = e.anterior) {
			Object encontrado = (e.tabla.get(simbolo));
			if (encontrado != null) {
				return encontrado;
			}
		}
		return null;
	}
	
	public void replace(String simbolo, Object value) {
		boolean notFound = true;
		Context e = this;
		while(e != null && notFound) {
			Object encontrado = (e.tabla.get(simbolo));
			if (encontrado != null) {
				e.tabla.put(simbolo, value);
				notFound = false;
			}
			e = e.anterior;
		}
		/*
		for(Context e = this; e!= null; e = e.anterior) {
			Object encontrado = (e.tabla.get(simbolo));
			if (encontrado != null) {
				e.tabla.put(simbolo, value);
				found = true;
				break;
			}
		}*/
		if (notFound) {
			tabla.put(simbolo, value);
		}
	}
}
