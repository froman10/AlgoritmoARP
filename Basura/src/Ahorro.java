
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
    
    Nodo nodo1;
    Nodo nodo2;
    Nodo nodoIda;
    ArrayList<Nodo> nodosAdicionales;
    double demandaTotal;
    double ahorro;
    boolean enlazada;

    public Ahorro(Nodo nodo1, Nodo nodo2, double ahorro) {
        this.nodosAdicionales = new ArrayList<>();
        this.nodo1 = nodo1;
        this.nodo2 = nodo2;
        this.demandaTotal = nodo1.getDemanda()+nodo2.getDemanda();
        this.ahorro = ahorro;
        this.enlazada = false;
    }
    public void addNodoAdicional(Nodo nodo){
        this.nodosAdicionales.add(nodo);
    }
    public void printAhorro(){
        if(this.nodoIda != null){
            System.out.print("nodo "+nodoIda.getName());
        }
        if(this.nodosAdicionales.size() > 0){
            System.out.print(" nodo "+nodo1.getName()+ " nodo "+nodo2.getName());
            for(int i = 0; i < this.nodosAdicionales.size(); i++){
                System.out.print(" nodo "+this.nodosAdicionales.get(i).getName());
            }
            System.out.println(" ahorro: "+ahorro+" demanda total: "+demandaTotal);
        }
        else{
            System.out.println(" nodo "+nodo1.getName()+ " nodo "+nodo2.getName()+" ahorro: "+ahorro+" demanda total: "+demandaTotal);
        }
        
    }

    public boolean isEnlazada() {
        return enlazada;
    }

    public void setEnlazada(boolean enlazada) {
        this.enlazada = enlazada;
    }

    
    public Nodo getNodo1() {
        return nodo1;
    }

    public void setNodo1(Nodo nodo1) {
        this.nodo1 = nodo1;
    }

    public Nodo getNodo2() {
        return nodo2;
    }

    public void setNodo2(Nodo nodo2) {
        this.nodo2 = nodo2;
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

    public Nodo getNodoIda() {
        return nodoIda;
    }

    public void setNodoIda(Nodo nodoIda) {
        this.nodoIda = nodoIda;
    }
    
    public boolean insertarNodoPrimeroOUltimo(Nodo nodoCandidato){
        if(this.nodo1.getName().equals(nodoCandidato.getName())){
            this.nodoIda = nodoCandidato;
            this.demandaTotal += nodoCandidato.getDemanda();
            return true;
        }
        if(this.nodosAdicionales.size() > 0){
            
            if(this.nodosAdicionales.get(this.nodosAdicionales.size()-1).getName().equals(nodoCandidato.getName())){
                this.nodosAdicionales.add(nodoCandidato);
                this.demandaTotal += nodoCandidato.getDemanda();
                return true;
            }
        }
        else if(this.nodo2.getName().equals(nodoCandidato.getName())){
           this.nodosAdicionales.add(nodoCandidato);
           this.demandaTotal += nodoCandidato.getDemanda();
           return true;
        }
        return false;
    }
    
    
    
    
    
    
    
}
