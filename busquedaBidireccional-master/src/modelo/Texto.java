package modelo;

import modelo.EDD.ArbolBB;
import modelo.EDD.Pila;

public class Texto {
	private ArbolBB textoA;//arbol ordenado de nodoInicio a NodoFin
	private ArbolBB textoB;//arbol ordenado de nodoFin a nodoInicio 
	private String cadenaTexto;
	public Texto() {
		this.textoA = new ArbolBB();
		this.textoB = new ArbolBB();
		this.cadenaTexto = null;
	}
	
	public void generarArboles(){
		Pila<Palabra> pila = new Pila<Palabra>();
		String t = this.cadenaTexto;
		String[] listaPalabras = t.split("[ \n\t\r,.;:!?(){}]");
		for (int i = 0; i < listaPalabras.length; i++) {
			Palabra palabra = new Palabra( listaPalabras[i] );
			pila.push(palabra);
			this.textoA.InsertarAmplitud(palabra);
		}
		for (int i = 0; i < listaPalabras.length; i++) {
			this.textoB.InsertarAmplitud(pila.pop());
		}
	}
	public ArbolBB getTextoA() {
		return textoA;
	}
	public ArbolBB getTextoB() {
		return textoB;
	}
	public void setCadenaTexto(String cadenaTexto) {
		this.cadenaTexto = cadenaTexto;
	}	
}
