
package algoritmoNoExpulsivo1;


public class Lista {
    
    Nodo inicio;
    Nodo fin;
    
    public Lista(){
        inicio=null;
        fin=null;
    }
    
    public void insertarNodo(int rafaga){
        Nodo nuevo=new Nodo(rafaga);
        if (inicio==fin && inicio==null) {
            inicio=nuevo;
            fin=nuevo;
        } else {
            fin.siguiente=nuevo;
            fin=nuevo;
        }
    }

    public Lista(Nodo inicio, Nodo fin) {
        this.inicio = inicio;
        this.fin = fin;
    }
    public void mostrarLista(){
        Nodo recorrido=inicio;
        while(recorrido!=null){
            System.out.println("Turno: "+recorrido.getTurno()+"/ Tiempo llegada: "+recorrido.gettLlegada()+"/ Rafaga: "+recorrido.getRafaga());
            recorrido=recorrido.siguiente;
        }
    }
    
}
