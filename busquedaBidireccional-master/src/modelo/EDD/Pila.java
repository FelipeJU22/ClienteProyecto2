package modelo.EDD;

public class Pila<E> {
    Nodo<E> tope;
    
    public Pila()
    {
        tope = null;
    }
    public boolean empty()
    {
        return tope == null;
    }
    public void push(E x)
    {
        Nodo<E> nuevo = new Nodo<E>(x);
        if(empty())
        {
            tope = nuevo;
        }
        else
        {
            nuevo.setSig(tope);
            tope = nuevo;
        }
    }
    public E pop()
    {
        E res = null;
        if(!empty())
        {
            res = tope.getDato();
            tope = tope.getSig();
        }
        return res;
    }
}

