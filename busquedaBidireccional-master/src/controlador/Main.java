package controlador;

import Vista.Ventana;
import modelo.Palabra;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("ejecutando main: ");
		//Controlador controlador = new Controlador("prueba para ver como se genera el arbol de palabras");
		Controlador nuevo = new Controlador();
		nuevo.getVentana().setVisible(true);
	}
}
