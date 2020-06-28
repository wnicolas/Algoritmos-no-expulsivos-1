
package algoritmoNoExpulsivo1;

public class Nodo {
    Nodo siguiente;
    static int turn=0;
    static int tLleg=-1;
    private int turno=0;
    private int tLlegada=-1;
    private int rafaga;
    private int tComienzo;
    private int tFinal;
    private int tRetorno;
    private int tEspera;

    public Nodo(int rafaga){
        turn+=1;
        turno=turn;
        tLleg+=1;
        tLlegada=tLleg;
        this.rafaga=rafaga;
        siguiente=null;
        
    }

    public int getTurno() {
        return turno;
    }

    public int gettLlegada() {
        return tLlegada;
    }


    public int getRafaga() {
        return rafaga;
    }

    public void setRafaga(int rafaga) {
        this.rafaga = rafaga;
    }

    public int gettComienzo() {
        return tComienzo;
    }

    public void settComienzo(int tComienzo) {
        this.tComienzo = tComienzo;
    }

    public int gettFinal() {
        return tFinal;
    }

    public void settFinal(int tFinal) {
        this.tFinal = tFinal;
    }

    public int gettRetorno() {
        return tRetorno;
    }

    public void settRetorno(int tRetorno) {
        this.tRetorno = tRetorno;
    }

    public int gettEspera() {
        return tEspera;
    }

    public void settEspera(int tEspera) {
        this.tEspera = tEspera;
    }
    
   
    
    
}
