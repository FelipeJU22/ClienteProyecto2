package ListaDoblementeEnlazadaCircular4k;

/**
 * Clase nosods, utilizada para almacenar los objetos canciones.
 */
public class Nodos{
    public String palabra;
    public String path;
    public Nodos next;
    public Nodos prev;
    public String posGlobal;
    public String posLocal;
    public int pos;


    /**
     * Método constructor
     */
    public Nodos(String palabra, String path, String posGlobal, String posLocal, int pos) {
        this.palabra = palabra;
        this.path = path;
        this.posGlobal = posGlobal;
        this.posLocal = posLocal;
        this.pos = pos;
        this.next = null;
        this.prev = null;
    }
    /**
     * Metodos setters and getter
     */
    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String nombrecan) {
        this.palabra = palabra;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPosGlobal() {return posGlobal;}

    public void setPosGlobal() {this.posGlobal = posGlobal;}

    public String getPosLocal() {return posLocal;}

    public void setPosLocal() {this.posLocal = posLocal;}

    public int getPos() {return pos;}

    public void setPos() {this.pos = pos;}

    public Nodos getNext() {
        return next;
    }

    public void setNext(Nodos next) {
        this.next = next;
    }

    public Nodos getPrev() {
        return prev;
    }

    public void setPrev(Nodos prev) {
        this.prev = prev;
    }

}


