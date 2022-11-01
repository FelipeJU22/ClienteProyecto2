package ArbolBB;

import java.util.ArrayList;
/**
 * Clase Nodo, el cual recibe los resultados de parsear el documento, y los guarda en el árbol binario
 */
public class Nodo {

    //atributos
    public String palabra;
    public String documento;
    public String posGlobal;
    public String posLocal;
    public Nodo left;
    public Nodo right;

    //metodos
    /**
     * Método constructor
     */
    public Nodo(String palabra, String documento, String posGlobal, String posLocal) {
        this.palabra = palabra;
        this.documento = documento;
        this.posGlobal = posGlobal;
        this.posLocal = posLocal;
        this.left = null;
        this.right = null;
    }
}