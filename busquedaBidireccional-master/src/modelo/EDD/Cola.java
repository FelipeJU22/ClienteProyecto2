package modelo.EDD;

public class Cola<E> { 
	
	private Nodo<E> frente;
    private Nodo<E> fin;
   
    public Cola()
    {
       frente=null;
       fin=null;
    }
    
    public boolean empty()
    {
        if (fin==null && frente==null)
            return true;
        else
            return false;
    }
    public void insert(E x)
    {
        Nodo<E> nuevo=new Nodo<E>(x);
        if (empty())
        {
            frente=nuevo;
            fin=nuevo;
        }
        else
        {
             fin.setSig(nuevo);
             fin=nuevo;
        }
    }
    
   
    public E remove()
    {   
        E res=null;
        if (!empty())
        {
            res=frente.getDato();
            frente=frente.getSig();
            if (frente==null)
            {
                fin=null;
            }
        }
        return res;
    }
   
    public void MostrarCola()
    {
        Nodo<E> ac=frente;
        while(ac!=null)
        {
            System.out.print(ac.getDato()+" , ");
            ac=ac.getSig();
        }
    } 
}
