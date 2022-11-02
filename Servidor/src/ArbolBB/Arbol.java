package ArbolBB;

import ServerMain.Server;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;
/**
 * Clase del árbol binario de busqueda.
 */
public class Arbol{
    //atributos
    public Nodo root;
    public String[] listaRec = new String[0];
    public Server server;
    public String mensaje = "";
    public static int cont;


    //metodos
    /**
     * Método que añade nodos al árbol binario de busqeuda
     */
    public void addNode(String palabra, String documento, String posGlobal, String posLocal){
        Nodo newNode = new Nodo(palabra, documento, posGlobal, posLocal);
        if (root == null){
            root = newNode;
        } else {
            Nodo padre = root;
            Nodo parent;
            while(true){
                parent = padre;
                if (Integer.parseInt(posGlobal) < Integer.parseInt(padre.posGlobal)){
                    padre = padre.left;
                    if (padre == null){
                        parent.left = newNode;
                        return;
                    }

                }
                else{
                    padre = padre.right;
                    if(padre == null){
                        parent.right = newNode;
                        return;
                    }
                }
            }
        }
    }
    /**
     * Método auxiliar que nos busca un nodo en especifico, comparandolo con la palabra almacenada en este.
     */
    public String findNodeAux(Nodo comp, String palabra){
        mensaje = "";
        if (comp == null) {
            return "nop";
        }
        if (Objects.equals(comp.palabra, palabra)){
            if(Integer.parseInt(comp.posLocal)==1){
                System.out.print(comp.palabra + listaRec[Integer.parseInt(comp.posGlobal)] + listaRec[Integer.parseInt(comp.posGlobal)+1]);
                findNodeAux(comp.right, palabra);
                return mensaje += comp.palabra + listaRec[Integer.parseInt(comp.posGlobal)] + listaRec[Integer.parseInt(comp.posGlobal)+1]+
                        " @@@ " +comp.documento + " @@@ " + comp.posGlobal + " @@@ " +comp.posLocal +" Yacasi ";
            }
            if(Integer.parseInt(comp.posGlobal) == listaRec.length){
                System.out.print(listaRec[Integer.parseInt(comp.posGlobal)-3] + " "  + listaRec[Integer.parseInt(comp.posGlobal)-2] + " " + comp.palabra);
                findNodeAux(comp.right, palabra);
                return mensaje += listaRec[Integer.parseInt(comp.posGlobal)-3] + " "  + listaRec[Integer.parseInt(comp.posGlobal)-2] + " " + comp.palabra+
                        " @@@" +comp.documento + " @@@ " + comp.posGlobal + " @@@ " +comp.posLocal +" Yacasi ";
            }
            else{
                System.out.print(listaRec[Integer.parseInt(comp.posGlobal)-2] + " " + comp.palabra + listaRec[Integer.parseInt(comp.posGlobal)]);
                findNodeAux(comp.right, palabra);
                return mensaje += listaRec[Integer.parseInt(comp.posGlobal)-2] + " " + comp.palabra + listaRec[Integer.parseInt(comp.posGlobal)]+
                        " @@@ " +comp.documento + " @@@ " + comp.posGlobal + " @@@ " +comp.posLocal +" Yacasi ";
            }
        }
        cont++;
        findNodeAux(comp.right, palabra);

        return "was";
    }
    /**
     * Método que llama al método auxiliar de busqueda
     */
    public String findNode(String palabra) {
        Nodo comp = root;
        findNodeAux(comp, palabra);
       // System.out.println(findNodeAux(comp, palabra));
        return mensaje + "PPP" + cont;
    }
    /**
     * Método que busca en el árbol binario de la forma inorden
     */
    public void inOrder(Nodo node){
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.printf("%s ", node.palabra);
        inOrder(node.right); }
    /**
     * Método que muestra un nodo con sus caracteristicas.
     */
    public void showRice(){
        inOrder(root);
    }
    public String[] recibirLista(String lista){
        String lista1 = lista.replace("[","").replace("]","");
        listaRec = lista1.split(",");
        return listaRec;
    }


}