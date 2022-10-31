package ArbolBB;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;

public class Arbol{
    //atributos
    public Nodo root;


    //metodos
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
//    public Nodo findNodeAux(Nodo comp, String palabra){
//        if (comp == null){
//            return null;
//        }
//        if (Objects.equals(comp.ocurrencias.get(comp.ocurrencias.size()-1), palabra)){
//            return comp;
//        }
//        Nodo res1 = findNodeAux(comp.left, palabra);
//        if (Objects.equals(res1.ocurrencias.get(res1.ocurrencias.size()-1), palabra)){
//            return res1;
//        }
//        Nodo res2 = findNodeAux(comp.right, palabra);
//        return res2;
//    }
    public void findNode(String palabra) {
        Nodo comp = root;
       // System.out.println(findNodeAux(comp, palabra));
    }
    public void inOrder(Nodo node){
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.printf("%s ", node.palabra);
        inOrder(node.right); }
    public void showRice(){
        inOrder(root);
    }
}