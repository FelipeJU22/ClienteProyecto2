package ArbolBB;

import java.util.ArrayList;

public class Nodo {

    //atributos
    public String palabra;
    public String documento;
    public String posGlobal;
    public String posLocal;
    public Nodo left;
    public Nodo right;

    //metodos
    public Nodo(String palabra, String documento, String posGlobal, String posLocal) {
        this.palabra = palabra;
        this.documento = documento;
        this.posGlobal = posGlobal;
        this.posLocal = posLocal;
        this.left = null;
        this.right = null;
    }
}