package modelo.EDD;

public class Nodo<E> {
	
	    E dato;
	    Nodo<E> sig;
	    
	    public Nodo(E vd)
	    {
	        dato=vd;
	        sig=null;
	    }
	    
	    public E getDato()
	    {
	        return dato;
	    }
	    
	    public Nodo<E> getSig()
	    {
	        return sig;
	    }
	    
	    public void setDato(E x)
	    {
	        dato=x;
	    }
	    
	    public void setSig(Nodo<E> y)
	    {
	        sig=y;
	    }
	
}
