package ListaDoblementeEnlazadaCircular4k;


import ClientMain.Client;

import java.awt.event.ActionEvent;

/**
 * Clase de la lista doblemente enlazada circular, la cual es utilizada para almacenar los nodos y poder avanzar entre
 * ellos libremente. Una vez al final de esta, nos envía al inicio nuevamente.
 */
public class LinkedList{
    public Nodos head;
    public Nodos tail;
    public Nodos current;
    public Integer size;
    private Client client;


    /**
     * Método constructor
     */

    public LinkedList() {

        this.head = null;
        this.tail = null;
        this.current = null;
        this.size = 0;
    }
    /**
     * Método para obtener el tamaño de la lista
     */


    public Nodos getHead() {
        return head;
    }

    public void setHead(Nodos head) {
        this.head = head;
    }

    public Nodos getTail() {
        return tail;
    }

    public void setTail(Nodos tail) {
        this.tail = tail;
    }

    public Nodos getCurrent() {
        return current;
    }

    public void setCurrent(Nodos current) {
        this.current = current;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }


    /**
     * Método para añadir elementos a la lista
     */
    public void añadir(String palabra, String path, String posGlobal, String posLocal, int pos) {
        Nodos nuevaPal = new Nodos(palabra, path, posGlobal, posLocal, pos);
        if (head == null) {
            head = nuevaPal;
            nuevaPal.next = head;
            nuevaPal.prev = head;
            tail = nuevaPal;
            current = head;
            this.size++;
        } else {
            Nodos ultimo = head.prev;
            tail.next = nuevaPal;
            nuevaPal.next = head;
            tail = nuevaPal;
            head.prev = tail;
            tail.prev = ultimo;
            this.size++;
        }
    }
    /**
     * Método para borrar el último elemento de la lista
     */
    public void deleteLast(){
    }
    /**
     * Método para mostrar los elementos de la lista
     */

    /**
     * Método para mover al inicio de la lista el Nodo Current
     */
    public void moveToStartCurrent(){current = head;}
    /**
     * Método para mover al final de la lista el Nodo Current
     */
    public void moveToEndCurrent(){current = tail;}
    /**
     * Método para mover para adelante en la lista el Nodo Current
     */
    public void adelanteCurrent(){current = current.next;}
    /**
     * Método para mover para atrás en la lista el Nodo Current
     */
    public void atrasCurrent(){current = current.prev;}

}

