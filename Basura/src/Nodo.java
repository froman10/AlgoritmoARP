/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Skorp
 */
public class Nodo {
    
    double demanda;
    int id;
    String name;
    boolean seleccionado;
    
    public Nodo(int id, String name){
        this.seleccionado = false;
        this.id = id;
        this.name = name;
        //this.printNodo();
    }

    public boolean isSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }
    
    
    
    public void printNodo(){
        System.out.println("Id: "+id+" nombre: "+name+" demanda: "+demanda);
    }

    public double getDemanda() {
        return demanda;
    }

    public void setDemanda(double demanda) {
        this.demanda = demanda;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
}
