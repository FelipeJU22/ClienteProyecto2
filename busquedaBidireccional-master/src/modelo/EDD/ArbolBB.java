package modelo.EDD;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import modelo.Palabra;

public class ArbolBB {
	private Palabra raiz;
    private ArbolBB izq;
    private ArbolBB der;
    private ArrayList<Palabra> nodosNivel;
    
    public ArbolBB(){
        raiz = null;
        izq = null;
        der = null;
    }
    public boolean vacio(){
        return raiz == null && izq == null && der == null;
    }
    public void insertar(Palabra x){
    	if(vacio()){
            raiz = x;
            System.out.println("--Nodo--[-"+x.getCadena()+"-]");
            izq = new ArbolBB();
            der = new ArbolBB();
        }
        else{
            if(x.getCadena().compareTo(raiz.getCadena()) < 0){
            	//comparacion de palabras y las ordena alfabeticamente
                //System.out.println("comparando: "+x.getCadena()+" con "+raiz.getCadena()+" = "+x.getCadena().compareTo(raiz.getCadena()));
                System.out.println("ruta: Izquierda ---> ");
            	izq.insertar(x);
            }
            else{
            	//System.out.println("comparando: "+x.getCadena()+" con "+raiz.getCadena()+" = "+x.getCadena().compareTo(raiz.getCadena()));
            	System.out.println("ruta: Derecha ---> ");
            	der.insertar(x);
            }
        }
    }
    public void InsertarAmplitud(Palabra x){
    	boolean inserto = false;
        Cola <ArbolBB> q = new Cola <ArbolBB>();
        ArbolBB a1;
        q.insert(this);
        while(!q.empty() && !inserto){
        	a1 = q.remove();
        	if(a1.vacio()){
        		inserto = true;
        		a1.raiz = x;
        		a1.izq = new ArbolBB();
        		a1.der = new ArbolBB();
        	}
        	else{
        		q.insert(a1.izq);
        		q.insert(a1.der);
        	}
        }
    }    
    public ArrayList<Palabra> getNodosNivel(int nivel){
    	nodosNivel = null;
    	
    	if (izq.vacio()  && der.vacio()) {
			
    		nodosNivel.add(raiz);
    		
		} else {
			izq.nodosNivel( nivel - 1 );
			der.nodosNivel( nivel - 1 );
		}
    	return nodosNivel;
    }
    
    public void nodosNivel( int nivel ){
    	
    }
    public Palabra getRaiz() {
		return raiz;
	}
    public ArbolBB getIzq(){
    	return izq;
    }
    
    public ArbolBB getDer(){
    	return der;
    }
    
    /*
     * Recorrido por niveles utilizando una cola
     */
    public void recorridoAmplitud(){
        Cola <ArbolBB> q = new Cola <ArbolBB>();
        ArbolBB a1;
        if (!vacio()){
            System.out.println(raiz.getCadena());
                if(!izq.vacio())
                    q.insert(izq);
                if (!der.vacio())
                    q.insert(der);
            while (!q.empty()){
                a1=q.remove();
                System.out.println(a1.raiz.getCadena());
                if(!a1.izq.vacio())
                    q.insert(a1.izq);
                if (!a1.der.vacio())
                    q.insert(a1.der);
            }
        }
    }
    /*
     * Recorrido preOrden de un arbol normal
     */
    public void recorridoProfundidad(){
    	Pila <ArbolBB> p = new Pila <ArbolBB>();
        ArbolBB a1;
        p.push(this);
        while (!p.empty()){
            a1=p.pop();
            System.out.println(a1.raiz.getCadena());
            if(!a1.der.vacio())
                p.push(a1.der);
            if (!a1.izq.vacio())
            	p.push(a1.izq);
        }
    }    
}
