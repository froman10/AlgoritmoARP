
import java.io.BufferedReader;
import java.io.FileReader;
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
public class AlgoritmoVRP {
    
    double[][] adyacencias;
    ArrayList<Nodo> nodos;
    ArrayList<Ahorro> ahorros;
    ArrayList<Integer> rutas;
    ArrayList<Integer> candidatos;
    double demandaMaxima;
    
    
    
    public AlgoritmoVRP(double demandaMaxima){
        this.demandaMaxima = demandaMaxima;
        this.nodos = new ArrayList<>();
        this.ahorros  = new ArrayList<>();
        this.rutas = new ArrayList<>();
        this.candidatos = new ArrayList<>();
        this.cargarDatos();
        this.calcularAhorros();
        this.seleccionarRutas();
        this.enlazarRutas();
        this.enlazarCandidatos();
        
        
    }
    
    public void cargarDatos(){
        try{
            int i;
            int j = 0;
            String cadena = "";
            String[] parts;
            FileReader f = new FileReader("Subbasurero.txt");
            BufferedReader b = new BufferedReader(f);
            
            cadena = b.readLine();
            parts = cadena.split("\t");
            
            this.adyacencias = new double[parts.length-2][parts.length-2];
            
            for(i = 1; i < parts.length-1; i++){
                nodos.add(new Nodo(i-1, parts[i]));
                System.out.println("");
            }
            i = 0;
             while((cadena = b.readLine())!=null) {
                parts = cadena.split("\t");
                 for(j = 1; j < parts.length-1; j++){
                    adyacencias[i][j-1] = Double.parseDouble(parts[j]);
                }
                nodos.get(i).setDemanda(Double.parseDouble(parts[j]));
                nodos.get(i).printNodo();
                i++;
            }
             b.close();
             this.printAdyacencias();
        }
        catch(Exception e){
            
        }
        
    }
    public void calcularAhorros(){
        int j = 1;
        double ahorroActual;
        for(int i = 1; i < adyacencias[0].length-1; i++){
            while( i+j < adyacencias[0].length ){
                ahorroActual = adyacencias[0][i] + adyacencias[0][i+j] - adyacencias[i][i+j];
                this.insertarAhorro(i, i+j, ahorroActual);
                
                j++;
            }
            j = 1;
        }
        this.printAhorros();
    }
    public void insertarAhorro(int idNodo1, int idNodo2, double ahorro){
        Boolean noInsertado = true;
        Nodo nodo1 = this.nodos.get(idNodo1);
        Nodo nodo2 = this.nodos.get(idNodo2);
        Ahorro objetoAhorro = new Ahorro(nodo1, nodo2, ahorro);
        //objetoAhorro.printAhorro();
        int size = new Integer(this.ahorros.size());
        for(int i = 0; i < size; i++){
            if(this.ahorros.get(i).getAhorro() <= ahorro ){
                this.ahorros.add(i, objetoAhorro);
                noInsertado = false;
                break;
            }
        }
        if(noInsertado){
            this.ahorros.add(objetoAhorro);
        }
        
    }
    public void seleccionarRutas() {
        Ahorro ruta;
        for(int i = 0; i < this.ahorros.size(); i++){
            ruta= this.ahorros.get(i);
            if(ruta.getDemandaTotal() <= this.demandaMaxima){
                if(!ruta.getNodo1().isSeleccionado() && !ruta.getNodo2().isSeleccionado()){
                    ruta.getNodo1().setSeleccionado(true);
                    ruta.getNodo2().setSeleccionado(true);
                    this.insertarRutaPorAhorro(i);
                }
                else if(!ruta.getNodo1().isSeleccionado() || !ruta.getNodo2().isSeleccionado()){
                    this.candidatos.add(i);
                }
                
            }
        }
        System.out.println("RUTAS SELECCIONADAS");
        this.printRutas();
    }
    public void enlazarRutas() {
        Ahorro ahorroI, ahorroJ;
        int indexI, indexJ;
        int i = 0, j = 0;
        
        while( i < this.rutas.size()){
            indexI = this.rutas.get(i);
            ahorroI = this.ahorros.get(indexI);
            j = (i+1);

            while( j < this.rutas.size()){
                indexJ = this.rutas.get(j);
                ahorroJ = this.ahorros.get(indexJ);
                
                if( ahorroI.getDemandaTotal()+ahorroJ.getDemandaTotal() <= this.demandaMaxima){
                    ahorroI.addNodoAdicional(ahorroJ.getNodo1());
                    ahorroI.addNodoAdicional(ahorroJ.getNodo2());
                    ahorroI.setDemandaTotal(ahorroI.getDemandaTotal()+ahorroJ.getDemandaTotal());
                    this.rutas.remove(j);
      
                }
                else{
                    j++;
                }
            }
            i++;
        }
        System.out.println("RUTAS Enlazadas");
        this.printRutas();
    }
    public void enlazarCandidatos() {
        Ahorro rutaCandidata;
        Nodo nodoCandidato;
        Nodo nodoNoCandidato;
        Ahorro rutaEnlazada;
        for(Integer candidato : this.candidatos){
            rutaCandidata = this.ahorros.get(candidato);
            if(!rutaCandidata.getNodo1().isSeleccionado()){
                nodoCandidato = rutaCandidata.getNodo1();
                nodoNoCandidato = rutaCandidata.getNodo2();
            }
            else if(!rutaCandidata.getNodo2().isSeleccionado()){
                nodoCandidato = rutaCandidata.getNodo2();
                nodoNoCandidato = rutaCandidata.getNodo1();
            }
            else{
                continue;
            }
            for(Integer idRuta : this.rutas){
                rutaEnlazada = this.ahorros.get(idRuta);
                if(rutaEnlazada.insertarNodoPrimeroOUltimo(nodoNoCandidato)){
                    
                }
            }
            
        }
    }
    public void insertarRutaPorAhorro(int index){
        Boolean noInsertado = true;
        int size = new Integer(this.rutas.size());
        for(int i = 0; i < size; i++){
            if(this.ahorros.get(i).getAhorro() <=  this.ahorros.get(index).getAhorro()){
                this.rutas.add(i, index);
                noInsertado = false;
                break;
            }
        }
        if(noInsertado){
            this.rutas.add(index);
        }
    }
    public void printAdyacencias(){
        for(int i = 0; i < adyacencias.length; i++){
            for(int j = 0; j <adyacencias.length; j++){
                System.out.print(adyacencias[i][j]+" ");
            }
            System.out.println("");
        }
    }
    public void printAhorros(){
         for(int i = 0; i < this.ahorros.size(); i++){
             this.ahorros.get(i).printAhorro();
         }
    }
    public void printRutas(){
        for(int i : this.rutas){
            this.ahorros.get(i).printAhorro();
        }
    }






    
}
