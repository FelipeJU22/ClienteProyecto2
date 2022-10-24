package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Vista.Ventana;
import modelo.Modelo;
import modelo.Palabra;
import modelo.EDD.ArbolBB;
import modelo.EDD.Cola;
import modelo.EDD.Pila;

public class Controlador extends Thread implements Runnable{
	private Modelo modelo;
	private Ventana ventana;
	Thread hilo1;
	
	public Controlador() {
		this.modelo = new Modelo();
		this.ventana = new Ventana();
		hilo1 = new Thread(this);
		setListeners();
	}
	public void setListeners(){
		this.ventana.getBtnBuscar().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//codigo q se ejecutara cuando se presione el boton buscar
				hilo1.start();
			}
		});
		this.ventana.getBtnLimpiar().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				limpiar(e);
			}
		});
	}
	public void limpiar(ActionEvent e){
		ventana.getTextArea().setText(null);
		ventana.getTextPane().setText(null);
		ventana.getTextPane_1().setText(null);
		ventana.getTxtPalabra().setText(null);
		hilo1.stop();
		hilo1=new Thread(this);
	}
	
	public void insertarTextoModelo() {
		String text = this.ventana.getTextArea().getText();
		this.modelo.getTexto().setCadenaTexto(text);
		this.modelo.getTexto().generarArboles();
		this.ventana.getTextPane().setEnabled(true);
		this.ventana.getTextPane_1().setEnabled(true);
		
	}
	public void run() {
		String CadenaPalabra = this.ventana.getTxtPalabra().getText();
		Palabra palabraBuscada = new Palabra(CadenaPalabra);
		insertarTextoModelo();
		busquedaBidireccional(palabraBuscada);
	}
	
	public void busquedaBidireccional(Palabra palabra){
		boolean encontro = false;
		Cola <ArbolBB> q = new Cola <ArbolBB>();
		Pila <ArbolBB> p = new Pila <ArbolBB>();
		ArbolBB a1 = null;
		ArbolBB a2 = null;
		q.insert(this.modelo.getTexto().getTextoA());
		p.push(this.modelo.getTexto().getTextoB());
		while(!q.empty() && !p.empty() && !encontro){
			a1 = q.remove();
			a2 = p.pop();
			this.ventana.getTextPane().setText(a1.getRaiz().getCadenaRed());
			try {
				sleep(900);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(a1.getRaiz().comparar(palabra)){
				String mensaje = "<font face=\"Tahoma\" size=\"8\">Encontro</font><br>";
				this.ventana.getTextPane().setText(a1.getRaiz().getCadenaGreen()+mensaje);
				this.ventana.getTextPane_1().setText(null);
				encontro = true;
				hilo1.stop();
				break;
			}
			this.ventana.getTextPane_1().setText(a2.getRaiz().getCadenaBlue());
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(a2.getRaiz().comparar(palabra)){
				String mensaje = "<font face=\"Tahoma\" size=\"8\">Encontro</font><br>";
				this.ventana.getTextPane_1().setText(a2.getRaiz().getCadenaGreen()+mensaje);
				this.ventana.getTextPane().setText(null);
				encontro = true;
				hilo1.stop();
				break;
			}
			if(!a1.getIzq().vacio()){
				q.insert(a1.getIzq());
			}
			if(!a1.getDer().vacio()){
				q.insert(a1.getDer());
			}
			if(!a2.getDer().vacio()){
				p.push(a2.getDer());
			}
			if(!a2.getIzq().vacio()){
				p.push(a2.getIzq());
			}
		}
	}
	public Ventana getVentana() {
		return ventana;
	}
}
