
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Skorp
 */
public class Ahorro{

    ArrayList<Nodo> nodos;
    double demandaTotal;
    double ahorro;
    boolean enlazada;
    String nombreBasura;

    public Ahorro(Nodo nodo1, Nodo nodo2, double ahorro,String nombreBasura) {
        this.nodos = new ArrayList<>();
        this.nodos.add(nodo1);
        this.nodos.add(nodo2);
        this.demandaTotal = nodo1.getDemanda()+nodo2.getDemanda();
        this.ahorro = ahorro;
        this.enlazada = false;
        this.nombreBasura = nombreBasura;
    }
    public void addNodo(Nodo nodo){
        this.nodos.add(nodo);
    }
    public void printAhorro(){
        System.out.print("nodo "+nombreBasura);
        for(int i = 0; i < this.nodos.size(); i++){
            System.out.print(" nodo "+this.nodos.get(i).getName());
        }
        System.out.println(" nodo "+nombreBasura+" ahorro: "+ahorro+" demanda total: "+demandaTotal);

        
    }
    public void printRuta(){
        System.out.print("nodo "+nombreBasura);
        for(int i = 0; i < this.nodos.size(); i++){
            System.out.print(" nodo "+this.nodos.get(i).getName());
        }
        System.out.println(" nodo "+nombreBasura+" demanda total: "+demandaTotal);
    }

    public boolean isEnlazada() {
        return enlazada;
    }

    public void setEnlazada(boolean enlazada) {
        this.enlazada = enlazada;
    }

    
    public Nodo getNodo1() {
        return nodos.get(0);
    }
    public void removeNodo1(){
        nodos.remove(0);
    }

    public void setNodo1(Nodo nodo1) {
        nodos.set(0, nodo1);
    }

    public Nodo getNodo2() {
        int size = nodos.size();
        return nodos.get(size-1);
    }
    public void removeNodo2(){
        int size = nodos.size();
        nodos.remove(size-1);
    }

    public void setNodo2(Nodo nodo2) {
        int size = nodos.size();
        nodos.set(size-1, nodo2);
    }

    public double getDemandaTotal() {
        return demandaTotal;
    }

    public void setDemandaTotal(double demandaTotal) {
        this.demandaTotal = demandaTotal;
    }

    public double getAhorro() {
        return ahorro;
    }

    public void setAhorro(double ahorro) {
        this.ahorro = ahorro;
    }

    
    public boolean insertarNodoPrimeroOUltimo(Nodo nodoNoCandidato, Nodo nodoCandidato){
        if(this.getNodo1().getName().equals(nodoNoCandidato.getName())){
            this.nodos.add(0, nodoCandidato);
            this.demandaTotal += nodoCandidato.getDemanda();
            return true;
        }
        if(this.nodos.size() > 2){
            int size = this.nodos.size();
            if(this.nodos.get(size-1).getName().equals(nodoNoCandidato.getName())){
                this.nodos.add(nodoCandidato);
                this.demandaTotal += nodoCandidato.getDemanda();
                return true;
            }
        }
        else if(this.getNodo2().getName().equals(nodoNoCandidato.getName())){
           this.nodos.add(nodoCandidato);
           this.demandaTotal += nodoCandidato.getDemanda();
           return true;
        }
        return false;
    }
    public boolean defenirRutaSolitaria(){
        if(!getNodo1().isSeleccionado()){
            getNodo1().setSeleccionado(true);
            demandaTotal-=getNodo2().getDemanda();
            removeNodo2();
            return true;
        }
        else if(!getNodo2().isSeleccionado()){
            getNodo2().setSeleccionado(true);
            demandaTotal-=getNodo1().getDemanda();
            removeNodo1();
            return true;
        }
        return false;
    }
    
    
    
    
    
    
}
